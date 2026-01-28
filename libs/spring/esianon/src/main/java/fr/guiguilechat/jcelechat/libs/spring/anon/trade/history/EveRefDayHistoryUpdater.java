package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.everef.history.EverefHistoryFetcher;
import fr.guiguilechat.jcelechat.libs.everef.history.HistoryEntry;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "everef.trade.history")
@Getter
@Setter
public class EveRefDayHistoryUpdater implements EntityUpdater {

	public static final String EXTSOURCE = "everef";

	private final HistoryLineRepository repo;

	@Lazy
	private final HistoryReqService historyReqService;

	private final EveRefDayHistoryRepository hrepo;

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	private LocalDate minESIDate = null;

	protected boolean completed = false;

	@Override
	public boolean fetch() {
		if (completed) {
			return false;
		}

		CompletableFuture<LocalDate> f_ld_max = hrepo.maxId(EXTSOURCE).thenApply(EveRefDayHistory::date);

		if (minESIDate == null) {
			CompletableFuture<Instant> f_i_min = repo.minEsiDateSaved();
			try {
				Instant minEsiSaved = f_i_min.get();
				if (minEsiSaved == null) {
					log.info("no esi data saved, waiting for data to start fetching everef");
					return false;
				} else {
					minESIDate = minEsiSaved.atOffset(ZoneOffset.UTC).toLocalDate();
					log.trace("found min esi date as {}", minESIDate);
				}
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}

		}

		LocalDate maxEverefSaved;
		try {
			maxEverefSaved = f_ld_max.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		List<LocalDate> toFetchDates = new ArrayList<>();
		for (LocalDate fetchDate = firstFetch(maxEverefSaved); toFetchDates.size() < getUpdate().getMax()
				&& fetchDate.isBefore(minESIDate); fetchDate = fetchDate.plusDays(1L)) {
			toFetchDates.add(fetchDate);
		}
		if (toFetchDates.isEmpty()) {
			log.info("completed everef fetching");
			completed = true;
			return true;
		}
		log.debug("selected {} days to fetch, from {} to {}",
				toFetchDates.size(),
				toFetchDates.get(0),
				toFetchDates.get(toFetchDates.size() - 1));

		EverefHistoryFetcher fetcher = new EverefHistoryFetcher("jcelechat");
		Map<LocalDate, CompletableFuture<List<HistoryEntry>>> results = toFetchDates.stream()
				.collect(Collectors.toMap(d -> d,
						fetcher::fetch));

		// first pass to fetch the HistoryReq ids
		Set<Long> reqIds = new HashSet<>();
		results.entrySet().forEach(e -> {
			try {
				List<HistoryEntry> l = e.getValue().get();
				if (l != null) {
					l.forEach(he -> reqIds.add(HistoryReq.makeId(he.getRegion_id(), he.getType_id())));
				}
			} catch (InterruptedException | ExecutionException e1) {
				throw new RuntimeException(e1);
			}
		});
		Map<Long, HistoryReq> historyReqById = historyReqService.getOrCreate(new ArrayList<>(reqIds));

		// second pass to create the results
		List<EveRefDayHistory> newEveRefHistories = new ArrayList<>();
		List<HistoryLine> newHistoryLines = new ArrayList<>();
		results.entrySet().forEach(e -> {
			LocalDate d = e.getKey();
			Instant instant = d.atStartOfDay().toInstant(ZoneOffset.UTC);
			EveRefDayHistory newEveRefHistory = new EveRefDayHistory(
					EveRefDayHistory.makeId(d.getYear(), d.getMonthValue(), d.getDayOfMonth()), true, 0, 0,
					Instant.now(),
					null);
			try {
				List<HistoryEntry> l = e.getValue().get();
				if (l != null) {
					newEveRefHistory.setStoredLines(l.size());
					for (HistoryEntry he : l) {
						HistoryLine hl = new HistoryLine(
								he.getAverage().doubleValue(),
								instant,
								he.getHighest().doubleValue(),
								he.getLowest().doubleValue(),
								he.getOrder_count(),
								he.getVolume(),
								EXTSOURCE);
						hl.setFetchResource(historyReqById.get(HistoryReq.makeId(he.getRegion_id(), he.getType_id())));
						newHistoryLines.add(hl);
					}
				} else {
					newEveRefHistory.setSuccess(false);
					newEveRefHistory.setSuccessiveErrors(1);
					newEveRefHistory.setNextFetch(Instant.now().plusSeconds(60 * 60 * 10));
				}
			} catch (InterruptedException | ExecutionException e1) {
				throw new RuntimeException(e1);
			}
			newEveRefHistories.add(newEveRefHistory);
		});
		log.trace("  saving {} everefhistories",
				newEveRefHistories.size());
		hrepo.saveAllAndFlush(newEveRefHistories);
		log.trace("  saving {} historylines",
				newHistoryLines.size());
		repo.saveAllAndFlush(newHistoryLines);
		log.trace("saved {} historylines from {} everefhistories",
				newHistoryLines.size(),
				newEveRefHistories.size());

		return true;
	}

	public LocalDate firstFetch(Instant lastDone) {
		if (lastDone == null) {
			return EverefHistoryFetcher.FIRST_DATE;
		}
		return lastDone.atOffset(ZoneOffset.UTC).toLocalDate().plusDays(1L);
	}

	public LocalDate firstFetch(LocalDate lastDone) {
		if (lastDone == null) {
			return EverefHistoryFetcher.FIRST_DATE;
		}
		return lastDone.plusDays(1L);
	}

}
