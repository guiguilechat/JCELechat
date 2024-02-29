package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import jakarta.transaction.Transactional;

@Service
public class OfferValueService {

	/**
	 * strategy to source and sell items
	 */
	public static enum SourceType {
		/** buy using BO, sell using SO (long term) */
		boso {

			@Override
			public double materialCost(RegionLineService regionLineService, long locationId, int typeId, long quantity,
					double brkPct) {
				Double marketPrice = regionLineService.boValueLocation(locationId, typeId, 1, false);
				return marketPrice == null ? Double.POSITIVE_INFINITY : quantity * marketPrice * (100 + brkPct) / 100;
			}

			@Override
			public double productUnitPrice(RegionLineService regionLineService, long locationId, int typeId, long quantity) {
				Double marketPrice = regionLineService.soValueLocation(locationId, typeId, 1, false);
				return marketPrice == null ? 0.0 : marketPrice;
			}

			@Override
			public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct) {
				return productUnitPrice * quantity * (100 - taxPct - brkPct) / 100;
			}

		},
		/** buy from SO, sell to BO (direct), purchasing each intermediate offer */
		sobo {

			@Override
			public double materialCost(RegionLineService regionLineService, long locationId, int typeId, long quantity,
					double brkPct) {
				Double marketPrice = regionLineService.soValueLocation(locationId, typeId, quantity, false);
				return marketPrice == null ? Double.POSITIVE_INFINITY : marketPrice;
			}

			@Override
			public double productUnitPrice(RegionLineService regionLineService, long locationId, int typeId, long quantity) {
				Double marketPrice = regionLineService.boValueLocation(locationId, typeId, quantity, false);
				return marketPrice == null ? 0.0 : marketPrice / quantity;
			}

		},
		/** buy from SO, sell to BO (direct), using mass price each time */
		sobodump {

			@Override
			public double materialCost(RegionLineService regionLineService, long locationId, int typeId, long quantity,
					double brkPct) {
				Double marketPrice = regionLineService.soValueLocation(locationId, typeId, quantity, true);
				return marketPrice == null ? Double.POSITIVE_INFINITY : marketPrice;
			}

			@Override
			public double productUnitPrice(RegionLineService regionLineService, long locationId, int typeId, long quantity) {
				Double marketPrice = regionLineService.boValueLocation(locationId, typeId, quantity, true);
				return marketPrice == null ? 0.0 : marketPrice / quantity;
			}

		};

		/** cost to acquire a given quantity of material */
		public abstract double materialCost(RegionLineService regionLineService, long locationId, int typeId,
				long quantity, double brkPct);

		/** unit price to list the product at */
		public abstract double productUnitPrice(RegionLineService regionLineService, long locationId, int typeId,
				long quantity);

		/** actual gain when selling the product */
		public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct) {
			return productUnitPrice * quantity * (100 - taxPct) / 100;
		}
	}

	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	RegionLineService regionLineService;

	public static record OfferEval(CorporationOffer offer, long offerQuantity, long lpQuantity, Type product,
			long productQuantity, double productUnitPrice, double productIncome,
			long materialCost, long marginCost, long tediousCost, long gain, int iskplp) {

		public OfferEval(CorporationOffer offer, long offerQuantity, Type product,
				long productQuantity, double productUnitPrice, double productIncome,
				double materialCost, double marginCost, double tediousCost) {
			this(offer, offerQuantity, offer.getLpCost() * offerQuantity, product, productQuantity,
					0.01 * (int) (100 * productUnitPrice), (long) productIncome, (long) materialCost, (long) marginCost,
					(long) tediousCost,
					(long) (productIncome - materialCost - marginCost - tediousCost),
					(int) ((productIncome - materialCost - marginCost - tediousCost) / offer.getLpCost() / offerQuantity));
		}
	}

	@Async
	@Transactional
	public CompletableFuture<OfferEval> value(CorporationOffer offer, int minLpAmount, SourceType sourcing,
			double brokerPct, double taxPct, double marginPct, double marginPctPerHour, double bpCost,
			long marketLocationId) {
		long offerQuantity = (long) Math.ceil(1.0 * minLpAmount / offer.getLpCost());
		double materialCost = 0.0;
		double tediousCost = 0.0;
		for (OfferRequirement r : offer.getRequirements()) {
			long required = offerQuantity * r.getQuantity();
			materialCost += sourcing.materialCost(regionLineService, marketLocationId, r.getType().getTypeId(), required,
					brokerPct);
		}
		long productQuantity = offerQuantity * offer.getQuantity();
		Type product = null;
		double timeMarginPct = 0;
		List<BlueprintActivity> manufs = blueprintActivityService.forBPActivity(offer.getType().getTypeId(),
				ACTIVITY_TYPE.manufacturing);
		if (manufs.size() == 1) {
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
				materialCost += sourcing.materialCost(regionLineService, marketLocationId, mat.getType().getTypeId(), required,
						brokerPct);
			}
			tediousCost = bpCost * offerQuantity;
		} else {
			// not a blueprint
			product = offer.getType();
		}

		double productUnitPrice = sourcing.productUnitPrice(regionLineService, marketLocationId, product.getTypeId(),
				productQuantity);
		double productIncome = sourcing.productIncome(productUnitPrice, productQuantity, taxPct, brokerPct);
		double marginCost = productUnitPrice * productQuantity * (marginPct + timeMarginPct) / 100;

		OfferEval ret = new OfferEval(offer, offerQuantity, product, productQuantity, productUnitPrice, productIncome,
				materialCost, marginCost, tediousCost);
		return CompletableFuture.completedFuture(ret);
	}

}
