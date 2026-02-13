package fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate.AggregatedTypeDetails;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoRepository;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoUpdater.ContractItemsListener;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketRegionUpdater.MarketRegionListener;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose BPC contract history and offers methods. For BPC, the unit
 * is not the number of items sold, but the total runs
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeBpc implements ContractItemsListener, MarketRegionListener {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	@Lazy
	private final StationService stationService;

	@Lazy
	private final TypeService typeService;

	public boolean hasEntry(int typeId, int me, int te) {
		return contractInfoRepository
				.existsByOfferedTypeIdAndOfferedMeAndOfferedTeAndOfferedCopyAndCompletedTrue(
						typeId, me,
						te, true);
	}

	/**
	 * find contracts that are open and offer a given type as copy, with ME/TE ge
	 * requested
	 *
	 * @param typeId
	 * @return list of open contracts that provide only given type, with
	 *           iscopy=true and me and te GE the one specified
	 */
	public List<ContractInfo> selling(int typeId, int me, int te) {
		return contractInfoRepository
				.findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdAndOfferedCopyAndOfferedMeGreaterThanEqualAndOfferedTeGreaterThanEqual(
						typeId,
						true, me, te);
	}

	protected void resolveNames(List<MarketOrder> orders) {
		Map<Integer, String> id2StationName = stationService
				.resolveNames(orders.stream().filter(mo -> mo.getLocationId() < Integer.MAX_VALUE)
						.map(mo -> (int) mo.getLocationId()).toList());
		for (MarketOrder mo : orders) {
			mo.resolveLocationName(id2StationName, Map.of());
		}
	}

	@Cacheable("BpcFacadeSellOrdersForType")
	public List<MarketOrder> sos(int typeId, int me, int te) {
		List<MarketOrder> ret = selling(typeId, me, te).stream()
				.map(MarketOrder::of)
				.sorted(Comparator.comparing(MarketOrder::getPrice))
				.toList();
		resolveNames(ret);
		return ret;
	}

	/**
	 * find contracts that sold a given type, with given ME/TE and iscopy=true
	 *
	 * @param typeId
	 * @return list of completed contracts that provide only given type, with
	 *           iscopy=true and given me and te
	 */
	public List<ContractInfo> sold(int typeId, int me, int te, Limit limit) {
		log.trace("  fetching BPC sales for type {} {}/{} limit {}",
				typeId,
				me,
				te,
				limit);
		List<ContractInfo> ret = contractInfoRepository
				.findByCompletedTrueAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTeOrderByRemovedBeforeDesc(
						List.of(typeId),
						true, me, te, limit);
		log.trace("   received {} daily sales", ret.size());
		return ret;
	}

	@Transactional
	public Stream<MarketOrder> streamSold(int typeId, int me, int te, Limit limit) {
		return sold(typeId, me, te, limit).stream().map(MarketOrder::of);
	}

	public List<AggregatedHL> aggregatedSales(int typeId, Instant from, int me, int te) {
		List<AggregatedHL> ret = contractInfoRepository.aggregatedBPCSales(from, typeId, me, te);
		return ret;
	}

	public List<AggregatedTypeDetails> aggregateHighestIskVolume(int days, int limit) {
		var now = Instant.now();
		var minDay = now.minus(days, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);
		long start = System.currentTimeMillis();
		List<AggregatedTypeDetails> ret = contractInfoRepository.aggregateBpcHighestSales(minDay, now, limit);
		long stop = System.currentTimeMillis();
		log.trace("fetched most sold over {} days in {} ms, returning {} records", days, stop - start, ret.size());
		return ret;
	}

	@Getter
	private final List<String> cacheList = List.of(
			"BpcFacadeSellOrdersForType");

}
