package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.history")
@Order(5) // depends on type, region, and market line
public class HistoryReqService
    extends ARemoteResourceService<HistoryReq, Long, R_get_markets_region_id_history[], HistoryReqRepository> {

	@Lazy
	private final HistoryLineService historyLineService;

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	private final RegionService regionService;

	@Lazy
	private final TypeService typeService;


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
	protected void onRemainToUpdate(long remainToUpdate) {
		super.onRemainToUpdate(remainToUpdate);
		if (isMoreToUpdate()) {
			Map<Integer, List<Integer[]>> regionIdToRegionType = marketLineService.listRegionIdTypeId().stream()
			    .collect(Collectors.groupingBy(arr -> arr[0]));
			for (Entry<Integer, List<Integer[]>> e : regionIdToRegionType.entrySet()) {
				int regionId = e.getKey();
				List<Long> typeIds = e.getValue().stream().map(arr -> HistoryReq.makeId(arr[0], arr[1])).toList();
				log.debug("require {} history entries to observe in region {}", typeIds.size(), regionId);
				createIfAbsent(typeIds);
			}
			// Map<Long, HistoryReq> required = createIfAbsent(
			// marketLineService.listRegionIdTypeId().stream().map(arr ->
			// HistoryReq.makeId(arr[0], arr[1])).toList());
			// log.debug("require {} history entries to observe", required.size());

			setMoreToUpdate(nbToUpdate() > 0);
		}
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
				if (lastDate == null || !lastDate.isAfter(HistoryLine.dateInstant(line.date))) {
					newLines.add(HistoryLine.of(req, line));
				}
			}
		}
		log.debug("added {} new history lines for {} requirements", newLines.size(), responseOk.size());
		historyLineService.saveAll(newLines);
	}

}
