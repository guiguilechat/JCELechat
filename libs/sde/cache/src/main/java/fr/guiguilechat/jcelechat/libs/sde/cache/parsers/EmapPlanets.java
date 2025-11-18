package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.AttributesPlanet;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InStarOrbit;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.StatisticsCelestial;
import lombok.ToString;

@ToString
public class EmapPlanets extends InStarOrbit {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapPlanets> LOADER = new IntMapLoader<>(
			"mapPlanets",
			EmapPlanets.class,
			Set.of("solarSystemID"));

	//
	// file structure
	//

	/** {@link EmapAsteroidBelts} */
	public List<Integer> asteroidBeltIDs;
	public AttributesPlanet attributes = new AttributesPlanet();
	/** {@link EmapMoons} */
	public List<Integer> moonIDs;
	/** {@link EnpcStations} */
	public List<Integer> npcStationIDs;
	public long radius;
	public StatisticsCelestial statistics = new StatisticsCelestial();

	public LinkedHashMap<String, String> uniqueName;

	public String enUniqueName() {
		return uniqueName == null ? null : uniqueName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.uniqueName != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemID=" + first.solarSystemID + " typeID=" + first.typeID);
		for (var e : loaded.entrySet()) {
			if (e.getValue().celestialIndex == 0) {
				System.err.println(e.getKey() + " has no celestialindex");
			}
		}
	}

}
