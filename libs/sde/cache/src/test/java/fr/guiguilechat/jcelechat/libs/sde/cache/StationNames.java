package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;

public class StationNames {

	public static void main(String[] args) {
		for (Entry<Integer, EnpcStations> e : EnpcStations.LOADER.load().entrySet()) {
			System.out.println(e.getKey() + "\t" + LocationName.of(e.getValue()));
		}
	}

}
