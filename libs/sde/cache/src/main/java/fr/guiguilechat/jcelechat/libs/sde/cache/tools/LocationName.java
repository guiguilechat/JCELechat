package fr.guiguilechat.jcelechat.libs.sde.cache.tools;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapAsteroidBelts;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EstationOperations;

/**
 * https://developers.eveonline.com/docs/services/static-data/#celestial-names
 */
public class LocationName {

	/**
	 * https://developers.eveonline.com/docs/guides/id-ranges/
	 */
	public static String of(int id) {
		// type checks sorted by the most probable calls
		if (id >= 40000000 && id <= 49999999) {
			EmapMoons moon = EmapMoons.LOADER.get(id);
			if (moon != null) {
				return of(moon);
			}

			EmapPlanets planet = EmapPlanets.LOADER.get(id);
			if (planet != null) {
				return of(planet);
			}

			EmapStars star = EmapStars.LOADER.get(id);
			if (star != null) {
				return of(star);
			}

			EmapAsteroidBelts belt = EmapAsteroidBelts.LOADER.get(id);
			if (belt != null) {
				return of(belt);
			}
		} else if (id >= 60000000 && id <= 69999999) {
			EnpcStations station = EnpcStations.LOADER.get(id);
			if (station != null) {
				return of(station);
			}
		} else if (id >= 50000000 && id <= 59999999) {
			EmapStargates gate = EmapStargates.LOADER.get(id);
			if (gate != null) {
				return of(gate);
			}
		}
		return null;
	}

	public static String of(EmapStars star) {
		return EmapSolarSystems.LOADER.get(star.solarSystemID).enName();
	}

	public static String of(EmapPlanets planets) {
		String ret = planets.enName();
		if (ret != null) {
			return ret;
		}
		ret = of(planets.orbitID) + (planets.celestialIndex > 0 ? roman(planets.celestialIndex) : "");
		return ret;
	}

	public static String of(EmapMoons moon) {
		String ret = moon.enName();
		if (ret != null) {
			return ret;
		}
		ret = of(moon.orbitID) + (moon.orbitIndex > 0 ? " - Moon " + roman(moon.orbitIndex) : "");
		return ret;
	}

	public static String of(EmapAsteroidBelts belt) {
		String ret = belt.enName();
		if (ret != null) {
			return ret;
		}
		ret = of(belt.orbitID) + (belt.orbitIndex > 0 ? " - Asteroid Belt " + roman(belt.orbitIndex) : "");
		return ret;
	}

	public static String of(EnpcStations sta) {
		String ret = sta.enName();
		if (ret != null) {
			return ret;
		}

		ret = of(sta.orbitID);
		var corp = sta.ownerID > 0 ? EnpcCorporations.LOADER.get(sta.ownerID) : null;
		if (corp != null) {
			ret += " - " + corp.enName();
		}
		if (sta.useOperationName) {
			EstationOperations op = EstationOperations.LOADER.get(sta.operationID);
			ret += " " + op.enOperationName();
		}
		return ret;
	}

	public static String of(EmapStargates gate) {
		var sol = EmapSolarSystems.LOADER.get(gate.destination.solarSystemID);
		return "Stargate (" + sol.enName() + ")";
	}

	static String roman(int value) {
		return switch (value) {
		case 1 -> "I";
		case 2 -> "II";
		case 3 -> "III";
		case 4 -> "IV";
		case 5 -> "V";
		case 6 -> "VI";
		case 7 -> "VII";
		case 8 -> "VIII";
		case 9 -> "IX";
		case 10 -> "X";
		case 11 -> "XI";
		case 12 -> "XII";
		case 13 -> "XIII";
		case 14 -> "XIV";
		case 15 -> "XV";
		default -> "FKU";
		};
	}

}
