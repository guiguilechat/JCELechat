package fr.guiguilechat.eveonline.model.esi.modeled.market;

import fr.guiguilechat.eveonline.esi.connected.modeled.ESIAccount;
import fr.guiguilechat.eveonline.esi.disconnected.modeled.market.CachedHistory;
import fr.guiguilechat.eveonline.esi.disconnected.modeled.market.Markets;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_history;

public class CachedHistoryMain {

	public static void main(String[] args) {
		// printhistorydate();
		showAverageWebII();
	}

	protected static void printhistorydate() {
		Markets mk = ESIAccount.DISCONNECTED.markets;
		CachedHistory h = new CachedHistory(mk.getMarket(10000002), 527);
		h.waitData();
		for (R_get_markets_region_id_history l : h.getData()) {
			System.err.println(l.date);
		}
	}

	protected static void showAverageWebII() {
		Markets mk = ESIAccount.DISCONNECTED.markets;
		CachedHistory h = new CachedHistory(mk.getMarket(10000002), 527);
		h.waitData();
		System.err.println("average=" + h.dailyAverage().get());
	}

}
