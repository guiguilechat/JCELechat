package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InPlanetOrbit;
import lombok.ToString;

@ToString
public class EnpcStations extends InPlanetOrbit {

	//
	// SDE loading
	//

	public static final IntMapLoader<EnpcStations> LOADER = new IntMapLoader<>(
			"npcStations",
			EnpcStations.class,
			Set.of("ownerID"));

	//
	// file structure
	//

	/**
	 * {@link EstationOperations}
	 */
	public int operationID;
	/**
	 * {@link EnpcCorporations}
	 */
	public int ownerID;
	public BigDecimal reprocessingEfficiency;
	public int reprocessingHangarFlag;
	public BigDecimal reprocessingStationsTake;
	public boolean useOperationName;

	public boolean orbitsMoon() {
		return orbitIndex > 0;
	}

	public boolean orbitsPlanet() {
		return orbitIndex == 0 && celestialIndex > 0;
	}

	public boolean orbitsStar() {
		return celestialIndex == 0;
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : index=" + first.celestialIndex);
//		for (var e : loaded.entrySet()) {
//			if (e.getValue().celestialIndex == 0) {
//				System.err.println(e.getKey() + " has no celestialindex");
//			}
//		}
	}
}
