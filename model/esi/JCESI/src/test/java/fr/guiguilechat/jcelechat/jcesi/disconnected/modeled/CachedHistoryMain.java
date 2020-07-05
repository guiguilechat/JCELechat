package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionTypeHistory;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;

public class CachedHistoryMain {

	public static void main(String[] args) {
		// printhistorydate();
		showAverageWebII();
	}

	protected static void printhistorydate() {
		RegionTypeHistory h = new RegionTypeHistory(ESIStatic.INSTANCE.cache, 10000002, 527);
		for (R_get_markets_region_id_history l : h.getData().get()) {
			System.err.println(l.date);
		}
	}

	protected static void showAverageWebII() {
		RegionTypeHistory h = new RegionTypeHistory(ESIStatic.INSTANCE.cache, 10000002, 527);
		System.err.println("average=" + h.daily.getAverage().get());
	}

}
