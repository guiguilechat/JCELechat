package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.history")
@Order(5) // depends on type, region, and market line
@Getter
@Setter
public class HistoryReqService
extends ARemoteEntityService<HistoryReq, Long, R_get_markets_region_id_history[], HistoryReqRepository> {

	@Lazy
	private final HistoryLineService historyLineService;

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	private final RegionService regionService;

	@Lazy
	private final TypeService typeService;

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

	@Override
	protected HistoryReq create(Long entityId) {
		Region region = regionService.createIfAbsent(HistoryReq.getRegionId(entityId));
		Type type = typeService.createIfAbsent(HistoryReq.getTypeId(entityId));
		return new HistoryReq(region, type);
	}

	@Override
	public Instant nextUpdate(boolean remain, Instant now) {
		if (!remain) {
			Map<Integer, List<Integer[]>> regionIdToRegionType = marketLineService.listRegionIdTypeId().stream()
					.collect(Collectors.groupingBy(arr -> arr[0]));
			for (Entry<Integer, List<Integer[]>> e : regionIdToRegionType.entrySet()) {
				int regionId = e.getKey();
				List<Long> typeIds = e.getValue().stream().map(arr -> HistoryReq.makeId(arr[0], arr[1])).toList();
				log.debug("require {} history entries to observe in region {}", typeIds.size(), regionId);
				createIfAbsent(typeIds);
			}
			remain = nbToUpdate() > 0;
		}
		return super.nextUpdate(remain, now);
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
				.collect(Collectors.groupingBy((Function<? super HistoryLine, ? extends HistoryReq>) HistoryLine::getFetchResource));
		long postFetch = System.currentTimeMillis();
		for (Entry<HistoryReq, List<HistoryLine>> e : byReq.entrySet()) {
			Map<Instant, List<HistoryLine>> byDate = e.getValue().stream().collect(Collectors.groupingBy((Function<? super HistoryLine, ? extends Instant>) HistoryLine::getDate));
			int reqDeleted = 0;
			for (List<HistoryLine> lines : byDate.values()) {
				if (lines.size() > 1) {
					List<HistoryLine> sublist = lines.subList(1, lines.size());
					toDelete.addAll(sublist);
					reqDeleted += sublist.size();
				}
			}
			if (reqDeleted > 0) {
				log.debug(" {}/{} lines are duplicates for request id {}", reqDeleted, e.getValue().size(), e.getKey().getId());
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

	@Transactional
	public void prioritizeType(int typeId) {
		prioritize(repo().findByTypeId(typeId));
	}

}
