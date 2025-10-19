package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;

public class ShowZarzakMoon {

	public static void main(String[] args) {
		var moon = EmapMoons.LOADER.get(40488503);
		System.out.println("got moon " + moon);
		var planet = EmapPlanets.LOADER.get(40488503);
		System.out.println("got planet " + planet);
		var station = EnpcStations.LOADER.get(60015187);
		System.out.println("got station " + station);
	}

}
