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

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	protected boolean completed = false;

	@Transactional
	@Override
	public boolean updatePulse() {
		if (completed) {
			return false;
		}

		LocalDate maxEverefSaved = eveRefDayHistoryRepository.maxDate();
		List<EveRefDayHistory> toFetch = new ArrayList<>();
		// retry at most half the errors
		toFetch.addAll(eveRefDayHistoryRepository.findBySuccessFalseAndNextFetchBeforeOrderByNextFetchAsc(Instant.now(),
				Limit.of(getUpdate().getMax() / 2)));
		if (!toFetch.isEmpty()) {
			log.debug(" retryng {} errored fetches", toFetch.size());
		}
		for (LocalDate fetchDate = firstFetch(maxEverefSaved);
				toFetch.size() < getUpdate().getMax();
				fetchDate = fetchDate.plusDays(1L)) {
			toFetch.add(EveRefDayHistory.builder()
					.date(fetchDate)
					.build());
		}
		if (toFetch.isEmpty()) {
			log.info("completed everef fetching");
			completed = true;
			return false;
		}
		log.debug(" selected {} days to fetch, from {} to {}",
				toFetch.size(),
				toFetch.get(0).getDate(),
				toFetch.get(toFetch.size() - 1).getDate());

		EverefHistoryFetcher fetcher = new EverefHistoryFetcher("jcelechat");
		Map<EveRefDayHistory, CompletableFuture<List<HistoryEntry>>> results = toFetch.stream()
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
		log.trace("  saving {} everefhistories",
				newEveRefHistories.size());
		eveRefDayHistoryRepository.saveAllAndFlush(newEveRefHistories);
		log.trace("  saving {} historylines",
				newHistoryLines.size());
		typeRegionDateHistoryRepository.saveAllAndFlush(newHistoryLines);
		log.debug(" saved {} history lines for {} days",
				newHistoryLines.size(),
				newEveRefHistories.size());

		return true;
	}

	public LocalDate firstFetch(LocalDate lastDone) {
		if (lastDone == null) {
			return EverefHistoryFetcher.FIRST_DATE;
		}
		return lastDone.plusDays(1L);
	}

}
