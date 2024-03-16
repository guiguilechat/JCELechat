package fr.guiguilechat.jcelechat.programs.showattributes.lp;

import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.npcs.Corporation;
import fr.guiguilechat.jcelechat.model.sde.npcs.LPOffer;
import fr.guiguilechat.jcelechat.model.sde.npcs.LPOffer.ItemRef;
import fr.guiguilechat.tools.FormatTools;

public class ShowDEDLPs {

	public static void main(String[] args) {
		int dedid = 1000137;
		System.out.println("name\tLP\tisk\titems\tIPL0\tIPL50k\tIPL1M");
		RegionalMarket theforge = ESIAccess.INSTANCE.markets.getMarket(10000002);
		for (Corporation corp : Corporation.load().values()) {
			if (corp.id == dedid) {
				for (Integer offerid : corp.lpoffers) {
					LPOffer offer = LPOffer.of(offerid);
					System.out.println(offer.name
							+ "\t" + FormatTools.formatPrice(offer.requirements.lp)
							+ "\t" + FormatTools.formatPrice(offer.requirements.isk)
							+ "\t" + offer.requirements.items.stream().collect(Collectors.toMap(e -> e.type().name, e -> e.quantity))
							+ "\t" + FormatTools.formatPrice(iplp(offer, theforge, 0))
							+ "\t" + FormatTools.formatPrice(iplp(offer, theforge, 50000))
							+ "\t" + FormatTools.formatPrice(iplp(offer, theforge, 1000000)));
				}
			}
		}
	}

	public static double iplp(LPOffer offer, RegionalMarket market, int minlp) {
		if (offer.requirements.lp == 0) {
			return 0;
		}
		int nbOffers = Math.max(1, (int) Math.ceil(1.0 * minlp / offer.requirements.lp));
		double iskplp = market.getBO(offer.product.id, offer.product.quantity * nbOffers).get() / nbOffers
				- offer.requirements.isk;
		for (ItemRef it : offer.requirements.items) {
			iskplp -= market.getSO(it.id, it.quantity * nbOffers).get() / nbOffers;
		}
		iskplp /= offer.requirements.lp;
		return iskplp;
	}

}
