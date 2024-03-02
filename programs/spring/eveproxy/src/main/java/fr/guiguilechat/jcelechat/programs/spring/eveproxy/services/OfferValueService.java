package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfferValueService {

	/**
	 * strategy to source and sell items
	 */
	public static enum SourceType {
		/** buy using BO, sell using SO (long term) */
		boso {

			@Override
			public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct) {
				return productUnitPrice * quantity * (100 - taxPct - brkPct) / 100;
			}

			@Override
			protected double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
					List<RegionLine> sos) {
				return (bos == null || bos.isEmpty() ? 0.01 : bos.get(0).getOrder().price) * (100 + brokerPct) / 100;
			}

			@Override
			protected double productUnitPrice(int typeId, long productQuantity, List<RegionLine> bos, List<RegionLine> sos) {
				return sos == null || sos.isEmpty() ? Double.POSITIVE_INFINITY : sos.get(0).getOrder().price;
			}

		},
		/** buy from SO, sell to BO (direct), purchasing each intermediate offer */
		sobo {

			@Override
			protected double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
					List<RegionLine> sos) {
				long remain = quantity;
				if (sos != null) {
					double ret = 0.0;
					for (RegionLine so : sos) {
						int remove = (int) Math.min(remain, so.getOrder().volume_remain);
						ret += so.getOrder().price * remove;
						remain -= remove;
						if (remain == 0) {
							return ret;
						}
					}
				}
				return Double.POSITIVE_INFINITY;
			}

			@Override
			protected double productUnitPrice(int typeId, long quantity, List<RegionLine> bos, List<RegionLine> sos) {
				long remain = quantity;
				double ret = 0.0;
				if (bos != null) {
					for (RegionLine bo : bos) {
						int remove = (int) Math.min(remain, bo.getOrder().volume_remain);
						ret += bo.getOrder().price * remove;
						remain -= remove;
						if (remain == 0) {
							return ret / quantity;
						}
					}
				}
				return ret / quantity;
			}

		},
		/** buy from SO, sell to BO (direct), using mass price each time */
		sobodump {

			@Override
			protected double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
					List<RegionLine> sos) {
				long remain = quantity;
				if (sos != null) {
					for (RegionLine so : sos) {
						int remove = (int) Math.min(remain, so.getOrder().volume_remain);
						remain -= remove;
						if (remain == 0) {
							return so.getOrder().price * quantity;
						}
					}
				}
				return Double.POSITIVE_INFINITY;
			}

			@Override
			protected double productUnitPrice(int typeId, long quantity, List<RegionLine> bos, List<RegionLine> sos) {
				long remain = quantity;
				if (bos != null) {
					for (RegionLine bo : bos) {
						int remove = (int) Math.min(remain, bo.getOrder().volume_remain);
						remain -= remove;
						if (remain == 0) {
							return bo.getOrder().price;
						}
					}
				}
				return 0.0;
			}

		};

		/** actual gain when selling the product */
		public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct) {
			return productUnitPrice * quantity * (100 - taxPct) / 100;
		}

		protected double materialCost(HashMap<Integer, Long> requiredMats, double brokerPct,
				Map<Integer, List<RegionLine>> bos, Map<Integer, List<RegionLine>> sos) {
			return requiredMats.entrySet().stream()
					.mapToDouble(k -> materialCost(k.getKey(), k.getValue(), brokerPct, bos.get(k.getKey()), sos.get(k.getKey())))
					.sum();
		}

		protected abstract double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos);

		protected abstract double productUnitPrice(int typeId, long productQuantity, List<RegionLine> bos,
				List<RegionLine> sos);
	}

	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	private RegionLineService regionLineService;

	public static record OfferEval(CorporationOffer offer, long offerQuantity, long lpQuantity, Type product,
			Type finalProduct,
			long productQuantity, double productUnitPrice, double productIncome,
			long materialCost, long marginCost, long tediousCost, long gain, int iskplp) {

		public OfferEval(CorporationOffer offer, long offerQuantity, Type product, Type finalProduct,
				long productQuantity, double productUnitPrice, double productIncome,
				double materialCost, double marginCost, double tediousCost) {
			this(offer, offerQuantity, offer.getLpCost() * offerQuantity, product, finalProduct, productQuantity,
					0.01 * (int) (100 * productUnitPrice), (long) productIncome, (long) materialCost, (long) marginCost,
					(long) tediousCost,
					(long) (productIncome - materialCost - marginCost - tediousCost),
					(int) ((productIncome - materialCost - marginCost - tediousCost) / offer.getLpCost() / offerQuantity));
		}
	}

	/**
	 * value once we have already fetched data from DB.
	 */
	OfferEval value(CorporationOffer offer, int minLpAmount, SourceType sourcing,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			Map<Integer, List<BlueprintActivity>> typeToActivities, Map<Integer, List<RegionLine>> bosByTypeId,
			Map<Integer, List<RegionLine>> sosByTypeId) {

		long offerQuantity = (long) Math.ceil(1.0 * minLpAmount / offer.getLpCost());
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
				long required = offerQuantity * manufProd.getQuantity() * mat.getQuantity();
				requiredMats.put(mat.getType().getTypeId(),
						required + requiredMats.getOrDefault(mat.getType().getTypeId(), 0l));
			}
			tediousCost = bpCost * offerQuantity;
		} else {
			// not a blueprint
			product = offer.getType();
		}

		double materialCost = sourcing.materialCost(requiredMats, timeMarginPct, bosByTypeId, sosByTypeId)
				+ offer.getIskCost() * offerQuantity;
		double productUnitPrice = sourcing.productUnitPrice(product.getTypeId(), productQuantity,
				bosByTypeId.get(product.getTypeId()), sosByTypeId.get(product.getTypeId()));
		double productIncome = sourcing.productIncome(productUnitPrice, productQuantity, taxPct, brokerPct);
		double marginCost = materialCost * (marginPct + timeMarginPct) / 100;

		return new OfferEval(offer, offerQuantity, offer.getType(), product, productQuantity, productUnitPrice,
				productIncome, materialCost, marginCost, tediousCost);
	}

	public List<OfferEval> value(List<CorporationOffer> offers, int minLpAmount, SourceType sourcing,
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
		List<OfferEval> ret = offers.parallelStream().map(o -> value(o, minLpAmount, sourcing, brokerPct, taxPct, marginPct,
				marginPctPerHour, bpCost, typeToActivities, bosByTypeId, sosByTypeId)).toList();
		long evaluated = System.currentTimeMillis();
		log.info(" evaluated " + offers.size()
				+ " offers"
				+ " activitiesFetched=" + (activitiesFetched - start) + "ms"
				+ " idsGathered=" + (idsGathered - activitiesFetched) + "ms"
				+ " bosFetched=" + (bosFetched - idsGathered) + "ms"
				+ " sosFetched=" + (sosFetched - bosFetched) + "ms"
				+ " evaluated=" + (evaluated - sosFetched) + "ms");
		return ret;
	}

}
