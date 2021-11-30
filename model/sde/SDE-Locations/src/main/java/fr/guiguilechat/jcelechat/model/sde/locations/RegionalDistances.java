package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.HashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * matrix of distances in a single region. Useful for market.
 *
 * @author
 *
 */
@RequiredArgsConstructor
public class RegionalDistances {

	@Getter
	private final int regionId;

	@Getter(lazy = true)
	private final Region region = Region.getRegion(getRegionId());

	@Getter(value = AccessLevel.PROTECTED, lazy = true)
	private final SolarSystem[] solarSystems = makeSolarSystems();

	private SolarSystem[] makeSolarSystems() {
		Region region = getRegion();
		return region
				.systems()
				.sorted()
				.map(SolarSystem::getSystem)
				.toArray(SolarSystem[]::new);
	}

	@Getter(value = AccessLevel.PROTECTED, lazy = true)
	private final HashMap<SolarSystem, Integer> sysToPos = makeSysToPos();

	private HashMap<SolarSystem, Integer> makeSysToPos() {
		HashMap<SolarSystem, Integer> ret = new HashMap<>();
		SolarSystem[] sols = getSolarSystems();
		for (int i = 0; i < sols.length; i++) {
			ret.put(sols[i], i);
		}
		return ret;
	}

	@Getter(value = AccessLevel.PROTECTED, lazy = true)
	private final int[][] distMatrix = makeDistMatrix();

	private final int DEFAULT_INT = Integer.MAX_VALUE / 3;

	private int[][] makeDistMatrix() {
		SolarSystem[] systems = getSolarSystems();
		int[][] ret = new int[systems.length][systems.length];
		// fill with 1 when neighbor, high value if not;
		for (int i = 0; i < ret.length; i++) {
			int[] line = ret[i];
			SolarSystem source = systems[i];
			ret[i][i] = 0;
			for (int j = 0; j < i; j++) {
				SolarSystem dest = systems[j];
				ret[j][i] = line[j] = source.adjacentSystems.contains(dest.name) ? 1 : DEFAULT_INT;
			}
		}
		boolean change = true;
		for (int run = 0; run < systems.length && change; run++) {
			change = false;
			for (int i = 0; i < systems.length; i++) {
				for (int j = 0; j < i; j++) {
					for (int t = 0; t < systems.length; t++) {
						if (ret[i][t] + ret[t][j] < ret[i][j]) {
							ret[i][j] = ret[j][i] = ret[i][t] + ret[t][j];
							change = true;
						}
					}
				}
			}
		}
		return ret;
	}

	public int distance(SolarSystem source, SolarSystem dest) {
		int si = getSysToPos().get(source);
		int di = getSysToPos().get(dest);
		int ret = getDistMatrix()[si][di];
		return ret == DEFAULT_INT ? Integer.MAX_VALUE : ret;
	}


}
