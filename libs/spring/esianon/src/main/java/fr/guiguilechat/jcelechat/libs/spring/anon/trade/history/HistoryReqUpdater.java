package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.history")
@Getter
@Setter
public class HistoryReqUpdater extends
		RemoteEntityUpdater<HistoryReq, Long, R_get_markets_region_id_history[], HistoryReqRepository, HistoryReqService>
{
	@Lazy
	private final HistoryLineService historyLineService;

	/**
	 * when true, on update of history data also checks and remove duplicate (that
	 * is, multiple entries with same date) for each historyreq
	 */
	protected boolean deduplicate = false;

	@Override
	protected Requested<R_get_markets_region_id_history[]> fetchData(Long id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_markets_history(HistoryReq.getRegionId(id),
				HistoryReq.getTypeId(id), properties);
	}

	private Instant nextMissingPairs =
			//
			null;
	// Instant.now().plus(1, ChronoUnit.HOURS);

	@Override
	@Transactional
	public Instant nextUpdate(boolean remain, Instant now) {
		if (!remain || nextMissingPairs == null || nextMissingPairs.isBefore(Instant.now())) {
			addMissingPairs();
			remain = nbToUpdate() > 0;
		}
		return super.nextUpdate(remain, now);
	}

	protected void addMissingPairs() {
		log.info("adding missing history (region, typeid) pairs from market");
		List<HistoryReq> toSave = new ArrayList<>();
		long preFetch = System.currentTimeMillis();
		List<Object[]> toUpdate = repo().listRequiredEntries();
		long postFetch = System.currentTimeMillis();
		log.debug(" received {} requirements to update in {} ms", toUpdate.size(), postFetch - preFetch);
		int creations = 0, activations = 0;
		for (Object[] arr : toUpdate) {
			int typeId = ((Number) arr[0]).intValue();
			int regionId = ((Number) arr[1]).intValue();
			HistoryReq req = (HistoryReq) arr[2];
			if (req == null) {
				long newId = HistoryReq.makeId(regionId, typeId);
				toSave.add(createMinimal(newId));
				creations++;
			} else if (!req.isFetchActive()) {
				req.setFetchActive(true);
				toSave.add(req);
				activations++;
			}
		}
		long postProcess = System.currentTimeMillis();
		log.debug(" processed {} entries with {} creations and {} activations in {} ms", toUpdate.size(), creations,
				activations,
				postProcess - postFetch);
		saveAll(toSave);
		long postSave = System.currentTimeMillis();
		log.debug(" saved {} entries in {} ms", toSave.size(), postSave - postProcess);
		nextMissingPairs = Instant.now().plus(1, ChronoUnit.DAYS);
	}

	@Override
	protected void updateResponseOk(Map<HistoryReq, R_get_markets_region_id_history[]> responseOk) {
		super.updateResponseOk(responseOk);
		Map<HistoryReq, Instant> reqToLastDate = historyLineService.findLastFetched(responseOk.keySet());
		List<HistoryLine> newLines = new ArrayList<>();
		for (Entry<HistoryReq, R_get_markets_region_id_history[]> e : responseOk.entrySet()) {
			HistoryReq req = e.getKey();
			R_get_markets_region_id_history[] lines = e.getValue();
			Instant lastDate = reqToLastDate.get(req);
			for (R_get_markets_region_id_history line : lines) {
				if (lastDate == null || lastDate.isBefore(HistoryLine.dateInstant(line.date))) {
					newLines.add(HistoryLine.of(req, line));
				}
			}
		}
		log.debug("added {} new history lines for {} requirements", newLines.size(), responseOk.size());
		historyLineService.saveAll(newLines);
		if (deduplicate) {
			deduplicate(responseOk.keySet());
		}
	}

	protected void deduplicate(Collection<HistoryReq> entries) {
		if (entries == null || entries.isEmpty()) {
			return;
		}
		long start = System.currentTimeMillis();
		log.info("deduplicating lines for {} entries", entries.size());
		List<HistoryLine> toDelete = new ArrayList<>();
		List<HistoryLine> allLines = historyLineService.byReq(entries);
		Map<HistoryReq, List<HistoryLine>> byReq = allLines.stream()
				.collect(Collectors.groupingBy(
						(Function<? super HistoryLine, ? extends HistoryReq>) HistoryLine::getFetchResource));
		long postFetch = System.currentTimeMillis();
		for (Entry<HistoryReq, List<HistoryLine>> e : byReq.entrySet()) {
			Map<Instant, List<HistoryLine>> byDate = e.getValue().stream().collect(
					Collectors.groupingBy((Function<? super HistoryLine, ? extends Instant>) HistoryLine::getDate));
			int reqDeleted = 0;
			for (List<HistoryLine> lines : byDate.values()) {
				if (lines.size() > 1) {
					List<HistoryLine> sublist = lines.subList(1, lines.size());
					toDelete.addAll(sublist);
					reqDeleted += sublist.size();
				}
			}
			if (reqDeleted > 0) {
				log.debug(" {}/{} lines are duplicates for request id {}", reqDeleted, e.getValue().size(),
						e.getKey().getId());
			}
		}
		long postAnalyze = System.currentTimeMillis();
		if (toDelete.size() > 0) {
			log.info("deleting {} duplicates", toDelete.size());
			historyLineService.delete(toDelete);
		}
		long postDelete = System.currentTimeMillis();
		log.trace("deduplicated {}/{} lines for {} HistoryReq in {}ms (fetch={} analyze={} delete={})",
				toDelete.size(),
				allLines.size(),
				entries.size(),
				postDelete - start,
				postFetch - start,
				postAnalyze - postFetch,
				postDelete - postAnalyze);
	}

}
