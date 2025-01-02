package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.math.BigDecimal;
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
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * facade to propose BPO contract history and offers methods.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractFacadeBpo {

	@Lazy
	private final ContractInfoRepository contractInfoRepository;

	/**
	 * find contracts that are open and offer a given original type, with ME/TE ge
	 * requested
	 * 
	 * @param typeId
	 * @return list of open contracts that provide only given type, with
	 *           iscopy=false and me and te GE the one specified
	 */
	public List<ContractInfo> selling(int typeId, int me, int te) {
		return contractInfoRepository
		    .findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeGreaterThanEqualAndOfferedTeGreaterThanEqual(
		        List.of(typeId),
		        false, me, te);
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
	 * find contracts that sold a given type, with given ME/TE and 0 runs
	 * 
	 * @param typeId
	 * @return list of completed contracts that provide only given type, with
	 *           iscopy=false and given me and te
	 */
	public List<ContractInfo> sold(int typeId, int me, int te) {
		return contractInfoRepository
		    .findByCompletedTrueAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
		        List.of(typeId),
		        false, me, te);
	}

	public List<AggregatedHL> aggregatedSales(int typeId, int me, int te) {
		Map<Instant, List<ContractInfo>> byDay = sold(typeId, me, te)
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
				volume += ci.getOfferedQuantity();
				totalValue += ci.getPrice();
				double unitPrice = ci.getPrice() / ci.getOfferedQuantity();
				if (unitPrice > highestPrice) {
					highestPrice = unitPrice;
				}
				if (unitPrice < lowestPrice) {
					lowestPrice = unitPrice;
				}
				regionIds.add(ci.getRegion().getId());
			}
			ret.add(new AggregatedHL(date, volume,
			    new BigDecimal(totalValue),
			    new BigDecimal(highestPrice),
			    new BigDecimal(lowestPrice),
			    orderCount, regionIds.size()));
		}
		return ret;
	}

}
