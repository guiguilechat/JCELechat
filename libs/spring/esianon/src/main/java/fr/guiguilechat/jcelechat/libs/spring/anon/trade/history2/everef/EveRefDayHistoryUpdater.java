package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.everef.history.EverefHistoryFetcher;
import fr.guiguilechat.jcelechat.libs.everef.history.HistoryEntry;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionDateHistory;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionDateHistoryRepository;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef.EveRefDayHistory.AggregationStatus;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "everef.trade.history")
@Getter
@Setter
@Service
public class EveRefDayHistoryUpdater implements EntityUpdater {

	private final EveRefDayHistoryRepository eveRefDayHistoryRepository;

	private final TypeRegionDateHistoryRepository typeRegionDateHistoryRepository;

	public long targetPulseMS = 10000;

	public String firstDate = EverefHistoryFetcher.FIRST_DATE_STR;
	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final LocalDate startDate = LocalDate.parse(firstDate);

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	protected boolean completed = false;

	/** time/days fetched for the last pulse */
	protected Long lastMsPerDay = null;

	/** select the next to update on the pulse. Create them if needed */
	List<EveRefDayHistory> selectNext() {
		List<EveRefDayHistory> toFetch = new ArrayList<>();
		int maxFetch = getUpdate().getMax();
		if (lastMsPerDay != null) {
			int maxFromRate = (int) (targetPulseMS / lastMsPerDay);
			if (maxFromRate < maxFetch) {
				maxFetch = maxFromRate;
			}
			if (maxFetch < 1) {
				maxFetch = 1;
			}
		} else {
			// always start with 1 day to fetch, to have an idea of the speed we can afford
			maxFetch = 1;
		}
		// retry at most half the errors
		if (maxFetch >= 2) {
			toFetch.addAll(
					eveRefDayHistoryRepository.findBySuccessFalseAndNextFetchBeforeOrderByNextFetchAsc(
							Instant.now(),
							Limit.of(maxFetch / 2)));
		}
		if (!toFetch.isEmpty()) {
			log.debug("  fetch again {} errored days", toFetch.size());
		}
		LocalDate maxEverefSaved = eveRefDayHistoryRepository.maxDate();
		for (
				LocalDate fetchDate = firstFetch(maxEverefSaved);
				toFetch.size() < maxFetch && fetchDate.isBefore(LocalDate.now().minusDays(1));
				fetchDate = fetchDate.plusDays(1L)) {
			toFetch.add(EveRefDayHistory.builder()
					.date(fetchDate)
					.build());
		}
		return toFetch;
	}

	@Transactional
	@Override
	public boolean updatePulse() {
		if (completed) {
			return false;
		}

		long start = System.currentTimeMillis();
		List<EveRefDayHistory> toFetch = selectNext();
		if (toFetch.isEmpty()) {
			log.info(" everef fetching complete");
			completed = true;
			return false;
		}
		completed = false;
		long postSelect = System.currentTimeMillis();
		long selectTime = postSelect - start;
		log.debug(" selected {} days to fetch, from {} to {}",
				toFetch.size(),
				toFetch.get(0).getDate(),
				toFetch.get(toFetch.size() - 1).getDate());
		EverefHistoryFetcher fetcher = new EverefHistoryFetcher("jcelechat");
		Map<EveRefDayHistory, CompletableFuture<List<HistoryEntry>>> results =
				toFetch.stream()
						.collect(Collectors.toMap(
								d -> d,
								d -> fetcher.fetch(d.getDate())));

		// delete existing entries so we don't add doubles, eg from esi
		typeRegionDateHistoryRepository
				.deleteForDates(results.keySet().stream().map(EveRefDayHistory::getDate).toList());
		// second pass to create the results
		List<EveRefDayHistory> newEveRefHistories = new ArrayList<>();
		List<TypeRegionDateHistory> newHistoryLines = new ArrayList<>();
		results.entrySet().forEach(e -> {
			EveRefDayHistory fetchedDay = e.getKey();
			fetchedDay.setLastFetch(Instant.now());
			try {
				List<HistoryEntry> l = e.getValue().get();
				if (l != null) {
					fetchedDay.setAggregationStatus(AggregationStatus.AVAIL);
					fetchedDay.setSuccess(true);
					fetchedDay.setSuccessiveErrors(0);
					fetchedDay.setStoredLines(l.size());
					l.stream().map(he -> TypeRegionDateHistory
							.of(fetchedDay.getDate(), he)).forEach(newHistoryLines::add);
				} else {
					fetchedDay.setAggregationStatus(AggregationStatus.UNABLE);
					fetchedDay.setSuccess(false);
					fetchedDay.increaseSuccessiveErrors();
					fetchedDay.setNextFetch(Instant.now().plusSeconds(fetchedDay.getSuccessiveErrors() * 60 * 60));
				}
			} catch (InterruptedException | ExecutionException e1) {
				throw new RuntimeException(e1);
			}
			newEveRefHistories.add(fetchedDay);
		});
		long postFetch = System.currentTimeMillis();
		long fetchTime = postFetch - postSelect;
		log.trace("  saving {} everefhistories",
				newEveRefHistories.size());
		eveRefDayHistoryRepository.saveAllAndFlush(newEveRefHistories);
		log.trace("  saving {} historylines",
				newHistoryLines.size());
		typeRegionDateHistoryRepository.saveAllAndFlush(newHistoryLines);
		long postSave = System.currentTimeMillis();
		long saveTime = postSave - postFetch;
		log.debug(" saved {} history lines for {} days in select={}ms, fetch={}ms, save={}ms",
				newHistoryLines.size(),
				newEveRefHistories.size(),
				selectTime,
				fetchTime,
				saveTime);
		lastMsPerDay = (postSave - start) / newEveRefHistories.size();
		return true;
	}

	public LocalDate firstFetch(LocalDate lastDone) {
		if (lastDone == null) {
			return getStartDate();
		}
		return lastDone.plusDays(1L);
	}

}
