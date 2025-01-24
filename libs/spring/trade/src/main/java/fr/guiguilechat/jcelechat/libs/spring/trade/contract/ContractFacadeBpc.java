package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.AggregatedTypeHistory;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose BPC contract history and offers methods. For BPC, the unit
 * is not the number of items sold, but the total runs
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeBpc {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	@Lazy
	private final TypeService typeService;

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
		    .findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeGreaterThanEqualAndOfferedTeGreaterThanEqual(
		        List.of(typeId),
		        true, me, te);
	}

	public List<ContractOrder> sos(int typeId, int me, int te) {
		return selling(typeId, me, te).stream().map(ContractOrder::new).toList();
	}

	@Transactional
	public Stream<MarketOrder> streamSOs(int typeId, int me, int te) {
		return selling(typeId, me, te).stream().map(MarketOrder::of)
		    .sorted(Comparator.comparing(MarketOrder::getPrice));
	}

	/**
	 * find contracts that sold a given type, with given ME/TE and iscopy=true
	 * 
	 * @param typeId
	 * @return list of completed contracts that provide only given type, with
	 *           iscopy=true and given me and te
	 */
	public List<ContractInfo> sold(int typeId, int me, int te, Limit limit) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTeOrderByRemovedBeforeDesc(
		        List.of(typeId),
		        true, me, te, limit);
	}

	@Transactional
	public Stream<MarketOrder> streamSold(int typeId, int me, int te, Limit limit) {
		return sold(typeId, me, te, limit).stream().map(MarketOrder::of);
	}

	public List<AggregatedHL> aggregatedSales(int typeId, int me, int te) {
		Map<Instant, List<ContractInfo>> byDay = sold(typeId, me, te, Limit.unlimited())
		    .stream()
		    .filter(ci -> ci.getRemovedBefore() != null)
		    .collect(Collectors.groupingBy(ci -> ci.getRemovedBefore().truncatedTo(ChronoUnit.DAYS)));
		List<AggregatedHL> ret = new ArrayList<>();
		for (Entry<Instant, List<ContractInfo>> e : byDay.entrySet()) {
			Instant date = e.getKey();
			long volume = 0l;
			double totalValue = 0.0;
			double highestPrice = 0.0;
			double lowestPrice = Double.MAX_VALUE;
			long orderCount = 0l;
			Set<Integer> regionIds = new HashSet<>();
			for (ContractInfo ci : e.getValue()) {
				volume += ci.getOfferedRuns();
				totalValue += ci.getPrice();
				double unitPrice = ci.getPrice() / ci.getOfferedRuns();
				if (unitPrice > highestPrice) {
					highestPrice = unitPrice;
				}
				if (unitPrice < lowestPrice) {
					lowestPrice = unitPrice;
				}
				regionIds.add(ci.getRegion().getId());
			}
			ret.add(new AggregatedHL(date, volume,
			    (totalValue),
			    (highestPrice),
			    (lowestPrice),
			    orderCount, regionIds.size()));
		}
		return ret;
	}

	public List<AggregatedTypeHistory> aggregateHighestIskVolume(int days, int limit) {
		var now = Instant.now();
		var minDay = now.minus(days, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);
		long start = System.currentTimeMillis();
		List<Object[]> fetched = contractInfoRepository.aggregateBpcHighestSales(minDay, now, limit);
		Map<Integer, String> typeId2Name = typeService.findById(
		    fetched.stream().map(arr -> ((Number) arr[0]).intValue()).toList()).stream()
		    .collect(Collectors.toMap(t -> t.getId(), t -> t.getName()));
		List<AggregatedTypeHistory> ret = contractInfoRepository.aggregateBpcHighestSales(minDay, now, limit)
		    .stream()
		    .map(arr -> {
			    int typeId = ((Number) arr[0]).intValue();
			    int me = ((Number) arr[1]).intValue();
			    int te = ((Number) arr[2]).intValue();
			    double totalValue = ((Number) arr[3]).doubleValue();
			    long totalRuns = ((Number) arr[4]).longValue();
			    String typeName = typeId2Name.get(typeId);
			    if (typeName == null) {
				    typeName = "unknown " + typeId;
			    }
			    typeName += "(" + totalRuns + "r) " + me + "/" + te;
			    AggregatedTypeHistory line = new AggregatedTypeHistory(typeId, typeName, days, totalValue,
			        totalRuns);
			    line.setMe(me);
			    line.setTe(te);
			    return line;
		    })
		    .toList();
		long stop = System.currentTimeMillis();
		log.trace("fetched most sold over {} days in {} ms, returning {} records", days, (stop - start), ret.size());
		return ret;
	}

}
