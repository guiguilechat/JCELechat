package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.industry.manufacturing.EivService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractItem;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.model.formula.industry.Research;
import fr.guiguilechat.jcelechat.model.formula.market.Tax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractEvalService {

	private final ContractInfoService contractInfoService;

	private final EivService eivService;

	@Lazy
	private final MarketLineService marketLineService;

	@Getter
	@Setter
	@ToString
	public static class EvalParams {

		private double baseMcost = 25;

		private int category = 9;

		/**
		 * if we need contracts requesting item (true), or selling (false)
		 */
		private boolean itemrequest = false;

		private long location = MarketLineService.JITAIV_ID;

		// can be HS, LS, NS or a region id.
		private String locationFilter = "HS";

		private double margin = 5.0;

		private Integer region = null;

		private boolean structure = false;

		/**
		 * checks on contracts that are not performed by the listing of
		 * {@link ContractEvalService#stream(EvalParams)}
		 *
		 * @param contract
		 * @return
		 */
		public boolean accept(ContractInfo contract) {
			if (region != null && contract.getRegion().getId() != region) {
				return false;
			}
			if (!structure && contract.getEndLocationId() > 100000000L) {
				return false;
			}
			return contract.getItems().stream()
					.filter(ci->
						ci.isIncluded()!=itemrequest
					    && ci.getType().getGroup().getCategory().getId() == category
					).findAny().isPresent();
		}

	}

	@RequiredArgsConstructor
	@Getter
	public static class ContractEval {

		private final ContractInfo contract;

		private final double valueRequired;

		private final double valueProvided;

		private final double valueMargin;

		public double gain() {
			double gain = 0;
			if (Double.isInfinite(valueRequired)) {
				gain = Double.NEGATIVE_INFINITY;
			} else {
				gain = valueProvided - valueRequired - valueMargin;
			}
			return gain;
		}

	}

	public List<ContractEval> evaluate(EvalParams params) {
		log.trace("evaluate contracts for {}", params);
		long startList = System.currentTimeMillis();
		List<ContractInfo> list = stream(params).filter(params::accept).toList();
		log.trace("filtered list of {} contracts in {} ms", list.size(), System.currentTimeMillis() - startList);
		return evaluate(list, params);
	}

	protected Stream<ContractInfo> stream(EvalParams params) {
		long start = System.currentTimeMillis();
		List<ContractInfo> fetchedFull = (params.itemrequest ? contractInfoService.exchangesBuying()
		    : contractInfoService.exchangesSelling())
		    .toList();
		long postAllExchanges = System.currentTimeMillis();
		log.trace("retrieved the full {} exchange contracts with itemrequest={} in {} ms",
		    fetchedFull.size(), params.itemrequest, postAllExchanges - start);
		return fetchedFull.stream();
	}

	public List<ContractEval> evaluate(Collection<ContractInfo> contracts, EvalParams params) {
		log.trace("evaluate list of {} contracts", contracts.size());
		long start = System.currentTimeMillis();
		Set<Integer> bpIds = new HashSet<>();
		Set<Integer> soldIds = new HashSet<>();
		Set<Integer> boughtIds = new HashSet<>();
		contracts.stream().flatMap(c -> c.getItems().stream()).forEach(item -> {
			if (item.isIncluded() && !item.isBlueprintCopy()) {
				if (item.getType().getGroup().getCategory().getId() == 9) {
					bpIds.add(item.getType().getId());
				} else {
					soldIds.add(item.getType().getId());
				}
			}
			if (!item.isIncluded()) {
				boughtIds.add(item.getType().getId());
			}
		});
		long postGroupIds = System.currentTimeMillis();
		log.trace("grouped the types of the contracts in {} ms", postGroupIds - start);

		Map<Integer, List<MarketLine>> typeId2BuyOrders = marketLineService.streamBOsAt(soldIds, params.location)
		    .collect(Collectors.groupingBy(MarketLine::getTypeId));
		long postFetchBos = System.currentTimeMillis();
		log.trace("retrieved BOs for {} types in {} ms", typeId2BuyOrders.size(), postFetchBos - postGroupIds);

		Map<Integer, List<MarketLine>> typeId2SellOrders = marketLineService.streamSOsAt(boughtIds, params.location)
		    .collect(Collectors.groupingBy(MarketLine::getTypeId));
		long postFetchSos = System.currentTimeMillis();
		log.trace("retrieved SOs for {} types in {} ms", typeId2SellOrders.size(), postFetchSos - postFetchBos);

		Map<Integer, Long> eivs = eivService.eivs();
		List<ContractEval> ret = new ArrayList<>();
		for (ContractInfo ci : contracts) {
			ret.add(evaluate(ci, params, typeId2BuyOrders, typeId2SellOrders, eivs));
		}
		long postEval = System.currentTimeMillis();
		log.trace("evaluated {} contracts in {} ms", ret.size(), postEval - postFetchSos);
		ret.removeIf(ce -> ce.valueProvided <= 0);
		ret.sort(Comparator.comparing(ce -> -ce.gain()));
		long postProcess = System.currentTimeMillis();
		log.trace("retain {} contracts after a {} ms process", ret.size(), postProcess - postEval);
		return ret;
	}

	ContractEval evaluate(ContractInfo ci, EvalParams params, Map<Integer, List<MarketLine>> typeId2BuyOrders,
	    Map<Integer, List<MarketLine>> typeId2SellOrders,
	    Map<Integer, Long> eivs) {
		double valueRequired = params.getBaseMcost();
		double valueProvided = 0;
		double valueRigsAloneProvided = 0;
		boolean containsShip = false;
		if (ci.getPrice() > 0) {
			valueRequired += ci.getPrice();
		} else {
			valueProvided += -ci.getPrice();
		}
		for (ContractItem item : ci.getItems()) {
			if (item.isIncluded()) {
				if (item.isBlueprintCopy()) {
					// ignore
				} else if (item.getType().getGroup().getCategory().getId() == 9) {
					// need to evaluate the bp value
					double bpValue = item.getType().getBasePrice().doubleValue();
					if (item.getQuantity() == 1 && (item.getMaterialEfficiency() > 0 || item.getTimeEfficiency() > 0)) {
						long eiv = eivs.get(item.getType().getId());
						bpValue += Research.researchTax(eiv, item.getMaterialEfficiency(), item.getTimeEfficiency(), 0.06);
					} else {
						bpValue *= item.getQuantity();
					}
					valueProvided += bpValue;
				} else {
					// a non blueprint is proposed on sale
					double buyOrderValue = 0.0;
					int remain = item.getQuantity();
					List<MarketLine> buyOrders = typeId2BuyOrders.getOrDefault(item.getType().getId(), List.of());
					for (MarketLine buyOrder : buyOrders) {
						int removed = Math.min(remain, buyOrder.getVolumeRemain());
						buyOrderValue += buyOrder.getPrice() * removed;
						remain -= removed;
						if (remain <= 0) {
							break;
						}
					}
					valueProvided += buyOrderValue * (1 - Tax.MINIMUM);
					if (item.getQuantity() == 1 && item.getType().getGroup().name().startsWith("Rig ")) {
						valueRigsAloneProvided += buyOrderValue * (1 - Tax.MINIMUM);
					}
					if (item.getType().getGroup().getCategory().getId() == 6) {
						containsShip = true;
					}
				}
			} else {
				double sellOrderValue = 0.0;
				int remain = item.getQuantity();
				List<MarketLine> sellOrders = typeId2SellOrders.getOrDefault(item.getType().getId(), List.of());
				for (MarketLine sellorder : sellOrders) {
					int removed = Math.min(remain, sellorder.getVolumeRemain());
					sellOrderValue += sellorder.getPrice() * removed;
					remain -= removed;
					if (remain <= 0) {
						break;
					}
				}
				if (remain > 0) {
					sellOrderValue = Double.POSITIVE_INFINITY;
				}
				valueRequired += sellOrderValue;
			}
		}
		if (containsShip) {
			valueProvided-=valueRigsAloneProvided;
		}
		double valueMargin = valueProvided * params.margin / 100;
		ContractEval ret = new ContractEval(ci, valueRequired, valueProvided, valueMargin);
		return ret;
	}

}
