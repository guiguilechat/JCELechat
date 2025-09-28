package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;

public class MismatchingSationNames {

	public static void main(String[] args) {
		System.out.println("planetId\tplanetName\tstationId\tstationName");
		for (Entry<Integer, EnpcStations> e : EnpcStations.LOADER.load().entrySet()) {
			EnpcStations sta = e.getValue();
			EmapPlanets p = EmapPlanets.LOADER.get(sta.orbitID);
			if (p != null) {
				if( p.enName() != null) {
					System.out.println(sta.orbitID + "\t" + p.enName() + "\t" + e.getKey() + "\t" + sta.enName());
				}
			} else {

			}
		}

	}

}
