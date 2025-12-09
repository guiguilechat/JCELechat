package fr.guiguilechat.jcelechat.libs.spring.npc.lp.eval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.Offer;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.Requirement;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MaterialSourcing;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.ProductValuator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * evaluate the isk/lp of npc corporations
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LPOfferEvalService {

	private final BlueprintActivityService blueprintActivityService;

	private final LinkCorporationOfferService linkCorporationOfferService;

	private final MarketLineService marketLineService;

	private final TypeService typeService;

	/**
	 * value once we have already fetched market data from DB.
	 */
	LPOfferEval value(Offer offer, int maxLpAmount, MaterialSourcing materialSourcing,
			ProductValuator productValuator,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			Map<Integer, List<BlueprintActivity>> typeToActivities,
			Map<Integer, List<MarketLine>> bosByTypeId,
			Map<Integer, List<MarketLine>> sosByTypeId) {

		long offerQuantity = (long) Math.floor(1.0 * maxLpAmount / offer.getLpCost());
		if (offerQuantity < 1) {
			log.trace("offer {} has offerquantiy {} from maxLPAmount {} and LpCost {}", offer.name(), offerQuantity,
					maxLpAmount, offer.getLpCost());
			return null;
		}
		double tediousCost = 0.0;
		HashMap<Integer, Long> requiredMats = new HashMap<>();
		for (Requirement r : offer.getRequirements()) {
			long required = offerQuantity * r.getQuantity();
			requiredMats.put(r.getType().getId(), required + requiredMats.getOrDefault(r.getType().getId(), 0L));
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
			BlueprintProduct manufProd = manuf.getProducts().get(0);
			product = typeService.byId(manufProd.getTypeId());
			productQuantity *= manufProd.getQuantity();
			int hours = (int) Math.ceil(1.0 * manuf.getTime() / 3600);
			timeMarginPct = hours * marginPctPerHour;

			for (BlueprintMaterial mat : manuf.getMaterials()) {
				long required = offerQuantity * offer.getQuantity() * mat.getQuantity();
				requiredMats.put(mat.getTypeId(),
						required + requiredMats.getOrDefault(mat.getTypeId(), 0L));
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

		return LPOfferEval.of(offer, offerQuantity, offer.getType(), product, productQuantity, productUnitPrice,
				productIncome, requiredMats, materialCost, marginCost, tediousCost);
	}

	/** give a value as cost, gain, isk/lp etc. to LP offers */
	@Transactional
	public List<LPOfferEval> value(List<Offer> offers, int maxLpAmount,
			MaterialSourcing materialSourcing,
			ProductValuator productValuator,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			long marketLocationId) {
		// the activities of blueprints that are a product of an offer
		long start = System.currentTimeMillis();
		Map<Integer, List<BlueprintActivity>> typeToActivities = blueprintActivityService
				.forBPActivity(offers.stream().map(co -> co.getType().getId()).toList(),
						List.of(ActivityType.manufacturing))
				.stream().collect(Collectors.groupingBy(BlueprintActivity::getTypeId));
		long activitiesFetched = System.currentTimeMillis();
		Set<Integer> allIds = Stream.of(
				// products of offers
				offers.stream().map(o -> o.getType().getId()),
				// offer requirements
				offers.stream().flatMap(offer -> offer.getRequirements().stream())
						.map(or -> or.getType().getId()),
				// products of bp
				typeToActivities.values().stream().flatMap(List::stream).flatMap(bpa -> bpa.getProducts().stream())
						.map(BlueprintProduct::getTypeId),
				// BP mats
				typeToActivities.values().stream().flatMap(List::stream).flatMap(bpa -> bpa.getMaterials().stream())
						.map(BlueprintMaterial::getTypeId))
				.flatMap(s -> s).collect(Collectors.toSet());
		long idsGathered = System.currentTimeMillis();
		Map<Integer, List<MarketLine>> bosByTypeId = marketLineService.locationBos(marketLocationId, allIds);
		long bosFetched = System.currentTimeMillis();
		Map<Integer, List<MarketLine>> sosByTypeId = marketLineService.locationSos(marketLocationId, allIds);
		long sosFetched = System.currentTimeMillis();
		List<LPOfferEval> ret = offers.parallelStream()
				.map(o -> value(o,
						maxLpAmount,
						materialSourcing,
						productValuator,
						brokerPct,
						taxPct,
						marginPct,
						marginPctPerHour,
						bpCost,
						typeToActivities,
						bosByTypeId,
						sosByTypeId))
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

	@Transactional
	public List<LPOfferEval> value(List<Offer> offers, LPEvalParams params) {
		return value(offers,
				params.getLp(),
				params.getMaterialSourcing(),
				params.getProductValuator(),
				params.getBrpct(),
				params.getTaxpct(),
				params.getMargin(),
				params.getMarginPerHour(),
				params.getBpcost(),
				params.getLocation());
	}

	@Transactional
	public List<LPOfferEval> valueCorp(int corpId, LPEvalParams params) {
		return value(linkCorporationOfferService.byCorporationIdRequiringLp(
				corpId,
				Integer.MAX_VALUE),
				params);
	}

	/**
	 * evaluate the lowest BO based on offering corporations highest isk/LP.
	 *
	 * @param typeId
	 */
	public void evaluateLPBO(int typeId, LPEvalParams params) {
		List<LinkCorporationOffer> producingLinks = linkCorporationOfferService.listProducingWithLP(typeId);
		List<LinkCorporationOffer> corpsLinks = linkCorporationOfferService
				.listCorpOffersWithLP(producingLinks.stream().map(l -> l.getLpCorp().getId()).distinct().toList());
		List<LPOfferEval> offerValues = value(
				corpsLinks.stream().map(LinkCorporationOffer::getOffer).distinct().toList(), params);
		Map<Offer, Double> offer2Iplp = offerValues.stream()
				.collect(Collectors.toMap(LPOfferEval::getOffer, LPOfferEval::getIskplp));
		Map<Integer, Double> corpId2BestIplp = new HashMap<>();
		for (LinkCorporationOffer lco : corpsLinks) {
			int corpId = lco.getLpCorp().getId();
			double iplp = offer2Iplp.get(lco.getOffer());
			corpId2BestIplp.put(corpId, Math.max(iplp, corpId2BestIplp.getOrDefault(corpId, 0.0)));
		}
	}

}
