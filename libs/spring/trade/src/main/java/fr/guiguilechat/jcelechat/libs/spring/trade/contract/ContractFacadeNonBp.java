package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.AggregatedTypeHistory;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose non-bp contract history and offers methods.
 * Actually also handles BPO 0/0 (unresearched)
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeNonBp {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	@Lazy
	private final TypeService typeService;

	/**
	 * find contracts that are open and offer a given type, with 0 ME/TE/runs
	 *
	 * @param typeId
	 * @return list of open contracts that provide only given type with (0,0,false)
	 *           for (me, te, iscopy) value
	 */
	public List<ContractInfo> selling(Collection<Integer> typeIds) {
		return contractInfoRepository
		    .findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        typeIds,
		        false, 0, 0);
	}

	public List<ContractOrder> sos(int typeId) {
		return selling(List.of(typeId)).stream().map(ContractOrder::new).toList();
	}

	@Transactional
	public Stream<MarketOrder> streamSOs(Collection<Integer> typeIds) {
		return selling(typeIds).stream().map(MarketOrder::of);
	}

	/**
	 * find contracts that sold a given type, with 0 ME/TE/runs
	 *
	 * @param typeId
	 * @return list of completed contracts that provide only given type with
	 *           (0,0,false) for (me, te, iscopy) value
	 */
	public List<ContractInfo> sold(Collection<Integer> typeIds, Limit limit) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTeOrderByRemovedBeforeDesc(
		        typeIds, false, 0, 0, limit);
	}

	/**
	 * find contracts that are open and request a given type for isk
	 *
	 * @param typeIds allowed types requested
	 * @return list of open contracts that request only one type among those given
	 *           and provide no item.
	 */
	public List<ContractInfo> buying(Collection<Integer> typeIds) {
		return contractInfoRepository
		    .findByRemovedFalseAndAsksOneTypeForIskTrueAndAskedTypeIdIn(typeIds);
	}

	@Transactional
	public Stream<MarketOrder> streamBOs(Collection<Integer> typeIds) {
		return buying(typeIds).stream().map(MarketOrder::of);
	}

	public List<AggregatedTypeHistory> aggregateHighestIskVolume(int days, int limit) {
		var now = Instant.now();
		var minDay = now.minus(days, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);
		long start = System.currentTimeMillis();
		List<Object[]> fetched = contractInfoRepository.aggregateUnresearchedHighestSales(minDay, now, limit);
		Map<Integer, String> typeId2Name = typeService.byId(
		    fetched.stream().map(arr -> ((Number) arr[0]).intValue()).toList()).stream()
		    .collect(Collectors.toMap(Type::getId, Type::getName));
		List<AggregatedTypeHistory> ret = contractInfoRepository.aggregateUnresearchedHighestSales(minDay, now, limit)
		    .stream()
		    .map(arr -> {
			    int typeId = ((Number) arr[0]).intValue();
			    double totalValue = ((Number) arr[1]).doubleValue();
			    long totalQuantity = ((Number) arr[2]).longValue();
			    String typeName = typeId2Name.get(typeId);
			    if (typeName == null) {
						typeName="unknown "+typeId;
					}
			    return new AggregatedTypeHistory(typeId, typeName, days, totalValue,
			        totalQuantity);
		    })
		    .toList();
		long stop = System.currentTimeMillis();
		log.trace("fetched most sold over {} days in {} ms, returning {} records", days, stop - start, ret.size());
		return ret;
	}

}
