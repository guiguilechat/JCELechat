package fr.guiguilechat.jcelechat.libs.spring.anon.lp.eval;

import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer.Offer;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.tools.FormatTools;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * evaluation of a LP offer
 */
@Getter
@Setter
@Builder
public class LPOfferEval {

	private final Offer offer;
	private long offerQuantity;
	private long lpQuantity;
	/** offer product */
	private Type product;
	/** final product, in the case product is a blueprint */
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


	public static LPOfferEval of(Offer offer, long offerQuantity, Type product, Type finalProduct,
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
		    .iskplp(
						(productIncome - materialCost - marginCost - tediousCost) / offer.getLpCost() / offerQuantity)
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