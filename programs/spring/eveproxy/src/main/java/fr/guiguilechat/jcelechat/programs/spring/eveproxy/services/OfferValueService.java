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

@Service
public class OfferValueService {

	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	RegionLineService regionLineService;

	public static record OfferEval(CorporationOffer offer, Type product, long offerQuantity, long productQuantity,
			double materialPriceBO, double productPriceSO, double gainBoso, double iplBoso,
			double materialPriceSO, double productPriceBO, double gainSobo, double iplSobo) {

		public OfferEval(CorporationOffer offer, Type product, long offerQuantity, long productQuantity,
				double materialPriceBO,double productPriceSO, double gainBoso,
				double materialPriceSO,	double productPriceBO, double gainSobo ) {
			this( offer,  product,  offerQuantity,  productQuantity,
					materialPriceBO, productPriceSO, gainBoso, gainBoso / offerQuantity / offer.getLpCost(),
					materialPriceSO, productPriceSO, gainSobo, gainSobo / offerQuantity / offer.getLpCost());
		}
	}

	@Async
	public CompletableFuture<OfferEval> value(CorporationOffer offer, int minLpAmount, double brokerPct, double taxPct,
			double marginPct,
			double marginPctPerHour, double bpCost, long marketLocationId) {
		long offerQuantity = (long) Math.ceil(1.0 * minLpAmount / offer.getLpCost());
		double matPriceBO = 0.0;
		double matPriceSO = 0.0;
		double tediousValue = 0.0;
		for (OfferRequirement r : offer.getRequirements()) {
			long required = offerQuantity * r.getQuantity();
			matPriceBO += regionLineService.boValueLocation(marketLocationId, r.getType().getTypeId(), required, false);
			matPriceSO += required * regionLineService.soValueLocation(marketLocationId, r.getType().getTypeId(), 1, false);
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
				matPriceBO += regionLineService.boValueLocation(marketLocationId, mat.getType().getTypeId(), required, false);
				matPriceSO += required
						* regionLineService.soValueLocation(marketLocationId, mat.getType().getTypeId(), 1, false);
			}
			tediousValue = bpCost * offerQuantity;
		} else {
			// not a blueprint
			product = offer.getType();
		}

		matPriceBO *= (100 + brokerPct) / 100;

		double productPriceBO = regionLineService.boValueLocation(marketLocationId, product.getTypeId(), productQuantity,
				false);
		double afterTaxIncomeBO = productPriceBO * (100 - taxPct) / 100 * (100 - marginPct - timeMarginPct) / 100;
		double gainsobo = afterTaxIncomeBO - matPriceSO - tediousValue;

		double productPriceSO = productQuantity
				* regionLineService.soValueLocation(marketLocationId, product.getTypeId(), 1, false);
		double afterTaxIncomeSO = productPriceSO * (100 - taxPct - brokerPct) / 100 * (100 - marginPct - timeMarginPct)
				/ 100;
		double gainboso = afterTaxIncomeSO - matPriceBO - tediousValue;

		return CompletableFuture.completedFuture(new OfferEval(offer, product, offerQuantity, productQuantity,
				matPriceBO, productPriceSO, gainboso,
				matPriceSO, productPriceBO, gainsobo));
	}

}
