package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Material;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Product;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.Offer;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.Requirement;
import fr.guiguilechat.jcelechat.libs.spring.trade.valuation.MaterialSourcing;
import fr.guiguilechat.jcelechat.libs.spring.trade.valuation.ProductValuator;
import fr.guiguilechat.jcelechat.libs.spring.trade2.regional.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.trade2.regional.RegionLineService;
import fr.guiguilechat.tools.FormatTools;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LPOfferEvalService {

	final private BlueprintActivityService blueprintActivityService;

	final private RegionLineService regionLineService;

	@Getter
	@Setter
	@ToString
	public static class EvalParams {

		private double bpcost = 1000000.0;
		private double brpct = 2.0;
		private long location = RegionLineService.JITAIV_ID;
		private int lp = 100000;
		private double margin = 5.0;
		private double marginPerHour = 0.5;
		private MaterialSourcing materialSourcing = MaterialSourcing.BUY_SO_MASS;
		private ProductValuator productValuator = ProductValuator.SELL_BO_MASS;
		private double taxpct = 3.6;

	}

	@Getter
	@Setter
	@Builder
	public static class LPOfferEval {

		private final LinkCorporationOffer corpOffer;
		private long offerQuantity;
		private long lpQuantity;
		private Type product;
		private Type finalProduct;
		private long productQuantity;
		private double productUnitPrice;
		private double productIncome;
		private Map<Integer, Long> materialsByTypeId;
		private double materialCost;
		private double marginCost;
		private double tediousCost;
		private double gain;
		private double iskplp;

		public Offer getOffer() {
			return corpOffer.getOffer();
		}


		public static LPOfferEval of(LinkCorporationOffer offer, long offerQuantity, Type product, Type finalProduct,
				long productQuantity, double productUnitPrice, double productIncome, Map<Integer, Long> materialsByTypeId,
				double materialCost, double marginCost, double tediousCost) {
			return builder()
			    .corpOffer(offer)
					.offerQuantity(offerQuantity)
			    .lpQuantity(offer.getOffer().getLpCost() * offerQuantity)
					.product(product)
					.finalProduct(finalProduct)
					.productQuantity(productQuantity)
					.productUnitPrice(productUnitPrice)
					.productIncome(productIncome)
					.materialsByTypeId(materialsByTypeId)
					.materialCost(materialCost)
					.marginCost(marginCost)
					.tediousCost(tediousCost)
					.gain(productIncome - materialCost - marginCost - tediousCost)
			    .iskplp(
			        (productIncome - materialCost - marginCost - tediousCost) / offer.getOffer().getLpCost() / offerQuantity)
					.build();
		}

		@Getter(lazy = true)
		private final String formatedProductUnitPrice = FormatTools.formatPrice(productUnitPrice);
		@Getter(lazy = true)
		private final String formatedProductIncome = FormatTools.formatPrice(productIncome);
		@Getter(lazy = true)
		private final String formatedMaterialCost = FormatTools.formatPrice(materialCost);
		@Getter(lazy = true)
		private final String formatedMarginCost = FormatTools.formatPrice(marginCost);
		@Getter(lazy = true)
		private final String formatedTediousCost = FormatTools.formatPrice(tediousCost);
		@Getter(lazy = true)
		private final String formatedGain = FormatTools.formatPrice(gain);
		@Getter(lazy = true)
		private final String formatedIskplp = FormatTools.formatPrice(iskplp);
	}

	/**
	 * value once we have already fetched data from DB.
	 */
	LPOfferEval value(LinkCorporationOffer lco, int maxLpAmount, MaterialSourcing materialSourcing,
			ProductValuator productValuator,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			Map<Integer, List<BlueprintActivity>> typeToActivities, Map<Integer, List<RegionLine>> bosByTypeId,
			Map<Integer, List<RegionLine>> sosByTypeId) {

		Offer offer = lco.getOffer();
		long offerQuantity = (long) Math.floor(1.0 * maxLpAmount / offer.getLpCost());
		if (offerQuantity < 1) {
			return null;
		}
		double tediousCost = 0.0;
		HashMap<Integer, Long> requiredMats = new HashMap<>();
		for (Requirement r : offer.getRequirements()) {
			long required = offerQuantity * r.getQuantity();
			requiredMats.put(r.getType().getId(), required + requiredMats.getOrDefault(r.getType().getId(), 0l));
		}
		long productQuantity = offerQuantity * offer.getQuantity();
		Type product = null;
		double timeMarginPct = 0;
		List<BlueprintActivity> manufs = typeToActivities.get(offer.getType().getId());
		if (manufs != null && manufs.size() == 1) {
			// it's a blueprint
			BlueprintActivity manuf = manufs.get(0);
			if (manuf.getProducts().size() != 1) {
				throw new RuntimeException("activity " + manuf + " has not 1 product");
			}
			Product manufProd = manuf.getProducts().get(0);
			product = manufProd.getType();
			productQuantity *= manufProd.getQuantity();
			int hours = (int) Math.ceil(1.0 * manuf.getTime() / 3600);
			timeMarginPct = hours * marginPctPerHour;

			for (Material mat : manuf.getMaterials()) {
				long required = offerQuantity * offer.getQuantity() * mat.getQuantity();
				requiredMats.put(mat.getType().getId(),
				    required + requiredMats.getOrDefault(mat.getType().getId(), 0l));
			}
			tediousCost = bpCost * offerQuantity;
		} else {
			// not a blueprint
			product = offer.getType();
		}

		double materialCost = materialSourcing.cost(requiredMats, taxPct, brokerPct, bosByTypeId, sosByTypeId)
				+ offer.getIskCost() * offerQuantity;
		double productUnitPrice = productValuator.unitPrice(productQuantity,
		    bosByTypeId.get(product.getId()), sosByTypeId.get(product.getId()));
		double productIncome = productValuator.value(productQuantity, taxPct, brokerPct,
		    bosByTypeId.get(product.getId()), sosByTypeId.get(product.getId()));
		double marginCost = productUnitPrice * productQuantity * (marginPct + timeMarginPct) / 100;

		return LPOfferEval.of(lco, offerQuantity, offer.getType(), product, productQuantity, productUnitPrice,
				productIncome, requiredMats, materialCost, marginCost, tediousCost);
	}

	/** give a value as cost, gain, isk/lp etc. to LP offers */
	@Transactional
	public List<LPOfferEval> value(List<LinkCorporationOffer> corporationOffers, int maxLpAmount,
	    MaterialSourcing materialSourcing,
			ProductValuator productValuator,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			long marketLocationId) {
		// the activities of blueprints that are a product of an offer
		long start = System.currentTimeMillis();
		Map<Integer, List<BlueprintActivity>> typeToActivities = blueprintActivityService
		    .forBPActivity(corporationOffers.stream().map(co -> co.getOffer().getType().getId()).toList(),
						List.of(ACTIVITY_TYPE.manufacturing))
		    .stream().collect(Collectors.groupingBy(ac -> ac.getType().getId()));
		long activitiesFetched = System.currentTimeMillis();
		Set<Integer> allIds = Stream.of(
				// products of offers
		    corporationOffers.stream().map(o -> o.getOffer().getType().getId()),
				// offer requirements
		    corporationOffers.stream().flatMap(offer -> offer.getOffer().getRequirements().stream())
		        .map(or -> or.getType().getId()),
				// products of bp
				typeToActivities.values().stream().flatMap(List::stream).flatMap(bpa -> bpa.getProducts().stream())
		        .map(pr -> pr.getType().getId()),
				// BP mats
				typeToActivities.values().stream().flatMap(List::stream).flatMap(bpa -> bpa.getMaterials().stream())
		        .map(mat -> mat.getType().getId()))
				.flatMap(s -> s).collect(Collectors.toSet());
		long idsGathered = System.currentTimeMillis();
		Map<Integer, List<RegionLine>> bosByTypeId = regionLineService.locationBos(marketLocationId, allIds);
		long bosFetched = System.currentTimeMillis();
		Map<Integer, List<RegionLine>> sosByTypeId = regionLineService.locationSos(marketLocationId, allIds);
		long sosFetched = System.currentTimeMillis();
		List<LPOfferEval> ret = corporationOffers.parallelStream()
		    .map(o -> value(o, maxLpAmount, materialSourcing, productValuator, brokerPct, taxPct, marginPct,
						marginPctPerHour, bpCost, typeToActivities, bosByTypeId, sosByTypeId))
				.filter(ev -> ev != null)
		    .toList();
		long evaluated = System.currentTimeMillis();
		log.debug(" evaluated " + corporationOffers.size()
				+ " offers"
				+ " activitiesFetched=" + (activitiesFetched - start) + "ms"
				+ " idsGathered=" + (idsGathered - activitiesFetched) + "ms"
				+ " bosFetched=" + (bosFetched - idsGathered) + "ms"
				+ " sosFetched=" + (sosFetched - bosFetched) + "ms"
				+ " evaluated=" + (evaluated - sosFetched) + "ms");
		return ret;
	}

}
