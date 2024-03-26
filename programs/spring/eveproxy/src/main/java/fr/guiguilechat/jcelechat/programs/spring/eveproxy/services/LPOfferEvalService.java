package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.strategies.MaterialSourcing;
import fr.guiguilechat.jcelechat.libs.spring.market.strategies.ProductValuator;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
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
		private double marginhour = 0.5;
		private MaterialSourcing materialSourcing = MaterialSourcing.BUY_SO_MASS;
		private ProductValuator productValuator = ProductValuator.SELL_BO_MASS;
		private double taxpct = 3.6;

	}

	@Getter
	@Setter
	@Builder
	public static class LPOfferEval {

		private final CorporationOffer offer;
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


		public static LPOfferEval of(CorporationOffer offer, long offerQuantity, Type product, Type finalProduct,
				long productQuantity, double productUnitPrice, double productIncome, Map<Integer, Long> materialsByTypeId,
				double materialCost, double marginCost, double tediousCost) {
			return builder()
					.offer(offer)
					.offerQuantity(offerQuantity)
					.lpQuantity(offer.getLpCost() * offerQuantity)
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
					.iskplp((productIncome - materialCost - marginCost - tediousCost) / offer.getLpCost() / offerQuantity)
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
	LPOfferEval value(CorporationOffer offer, int maxLpAmount, MaterialSourcing materialSourcing,
			ProductValuator productValuator,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			Map<Integer, List<BlueprintActivity>> typeToActivities, Map<Integer, List<RegionLine>> bosByTypeId,
			Map<Integer, List<RegionLine>> sosByTypeId) {

		long offerQuantity = (long) Math.floor(1.0 * maxLpAmount / offer.getLpCost());
		if (offerQuantity < 1) {
			return null;
		}
		double tediousCost = 0.0;
		HashMap<Integer, Long> requiredMats = new HashMap<>();
		for (OfferRequirement r : offer.getRequirements()) {
			long required = offerQuantity * r.getQuantity();
			requiredMats.put(r.getType().getTypeId(), required + requiredMats.getOrDefault(r.getType().getTypeId(), 0l));
		}
		long productQuantity = offerQuantity * offer.getQuantity();
		Type product = null;
		double timeMarginPct = 0;
		List<BlueprintActivity> manufs = typeToActivities.get(offer.getType().getTypeId());
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
				requiredMats.put(mat.getType().getTypeId(),
						required + requiredMats.getOrDefault(mat.getType().getTypeId(), 0l));
			}
			tediousCost = bpCost * offerQuantity;
		} else {
			// not a blueprint
			product = offer.getType();
		}

		double materialCost = materialSourcing.cost(requiredMats, taxPct, brokerPct, bosByTypeId, sosByTypeId)
				+ offer.getIskCost() * offerQuantity;
		double productUnitPrice = productValuator.unitPrice(productQuantity,
				bosByTypeId.get(product.getTypeId()), sosByTypeId.get(product.getTypeId()));
		double productIncome = productValuator.value(productQuantity, taxPct, brokerPct,
				bosByTypeId.get(product.getTypeId()), sosByTypeId.get(product.getTypeId()));
		double marginCost = materialCost * (marginPct + timeMarginPct) / 100;

		System.err.println("unit price is " + productUnitPrice);

		return LPOfferEval.of(offer, offerQuantity, offer.getType(), product, productQuantity, productUnitPrice,
				productIncome, requiredMats, materialCost, marginCost, tediousCost);
	}

	@Transactional
	public List<LPOfferEval> value(List<CorporationOffer> offers, int maxLpAmount, MaterialSourcing materialSourcing,
			ProductValuator productValuator,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			long marketLocationId) {
		// the activities of blueprints that are a product of an offer
		long start = System.currentTimeMillis();
		Map<Integer, List<BlueprintActivity>> typeToActivities = blueprintActivityService
				.forBPActivity(offers.stream().map(co -> co.getType().getTypeId()).toList(),
						List.of(ACTIVITY_TYPE.manufacturing))
				.stream().collect(Collectors.groupingBy(ac -> ac.getType().getTypeId()));
		long activitiesFetched = System.currentTimeMillis();
		Set<Integer> allIds = Stream.of(
				// products of offers
				offers.stream().map(o -> o.getType().getTypeId()),
				// offer requirements
				offers.stream().flatMap(offer -> offer.getRequirements().stream()).map(or -> or.getType().getTypeId()),
				// products of bp
				typeToActivities.values().stream().flatMap(List::stream).flatMap(bpa -> bpa.getProducts().stream())
						.map(pr -> pr.getType().getTypeId()),
				// BP mats
				typeToActivities.values().stream().flatMap(List::stream).flatMap(bpa -> bpa.getMaterials().stream())
						.map(mat -> mat.getType().getTypeId()))
				.flatMap(s -> s).collect(Collectors.toSet());
		long idsGathered = System.currentTimeMillis();
		Map<Integer, List<RegionLine>> bosByTypeId = regionLineService.locationBos(marketLocationId, allIds);
		long bosFetched = System.currentTimeMillis();
		Map<Integer, List<RegionLine>> sosByTypeId = regionLineService.locationSos(marketLocationId, allIds);
		long sosFetched = System.currentTimeMillis();
		List<LPOfferEval> ret = offers.parallelStream()
				.map(o -> value(o, maxLpAmount, materialSourcing, productValuator, brokerPct, taxPct, marginPct,
						marginPctPerHour, bpCost, typeToActivities, bosByTypeId, sosByTypeId))
				.filter(ev -> ev != null)
				.toList();
		long evaluated = System.currentTimeMillis();
		log.debug(" evaluated " + offers.size()
				+ " offers"
				+ " activitiesFetched=" + (activitiesFetched - start) + "ms"
				+ " idsGathered=" + (idsGathered - activitiesFetched) + "ms"
				+ " bosFetched=" + (bosFetched - idsGathered) + "ms"
				+ " sosFetched=" + (sosFetched - bosFetched) + "ms"
				+ " evaluated=" + (evaluated - sosFetched) + "ms");
		return ret;
	}

}
