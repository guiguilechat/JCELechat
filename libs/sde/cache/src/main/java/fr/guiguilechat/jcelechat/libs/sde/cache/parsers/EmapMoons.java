package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.AttributesMoon;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InPlanetOrbit;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.StatisticsCelestial;
import lombok.ToString;

/**
 * moons' orbitid is actually planetId
 */
@ToString
public class EmapMoons extends InPlanetOrbit {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapMoons> LOADER = new IntMapLoader<>(
			"mapMoons",
			EmapMoons.class);

	//
	// file structure
	//

	public AttributesMoon attributes = new AttributesMoon();
	/** {@link EnpcStations} */
	public List<Integer> npcStationIDs;
	public BigDecimal radius;
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
