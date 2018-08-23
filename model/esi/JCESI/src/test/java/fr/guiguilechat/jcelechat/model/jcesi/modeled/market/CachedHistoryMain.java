package fr.guiguilechat.jcelechat.model.jcesi.modeled.market;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.CachedHistory;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_markets_region_id_history;

public class CachedHistoryMain {

	public static void main(String[] args) {
		// printhistorydate();
		showAverageWebII();
	}

	protected static void printhistorydate() {
		CachedHistory h = new CachedHistory(ESIStatic.INSTANCE.cache, 10000002, 527);
		h.waitData();
		for (R_get_markets_region_id_history l : h.getData()) {
			System.err.println(l.date);
		}
	}

	protected static void showAverageWebII() {
		CachedHistory h = new CachedHistory(ESIStatic.INSTANCE.cache, 10000002, 527);
		h.waitData();
		System.err.println("average=" + h.dailyAverage().get());
	}

}
