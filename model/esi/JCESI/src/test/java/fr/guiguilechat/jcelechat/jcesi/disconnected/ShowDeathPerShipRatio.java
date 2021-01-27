package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_jumps;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_kills;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;

public class ShowDeathPerShipRatio {

	public static void main(String[] args) {
		CacheStatic cache = ESIStatic.INSTANCE.cache();
		for (Integer r : cache.universe.systems().get()) {
			cache.universe.systems(r);
		}
		ObsMapHolder<Integer, R_get_universe_system_kills> kills = cache.universe.system_kills().toMap(sys -> sys.system_id);
		ObsMapHolder<Integer, R_get_universe_system_jumps> jumps_m = cache.universe.system_jumps()
				.toMap(sys -> sys.system_id);
		// useless because wh are not given their kill statistics
		Set<Integer> whconstels = IntStream.range(11000001, 11000033 + 1).parallel()
				.mapToObj(r_i -> cache.universe.regions(r_i)).flatMapToInt(h -> IntStream.of(h.get().constellations))
				.mapToObj(i -> i).collect(Collectors.toSet());
		Set<Integer> abyssConstels = IntStream.range(12000001, 12000005 + 1).parallel()
				.mapToObj(r_i -> cache.universe.regions(r_i)).flatMapToInt(h -> IntStream.of(h.get().constellations))
				.mapToObj(i -> i).collect(Collectors.toSet());
		// System.err.println("constel ids=" + whconstels);
		// HS, LS, NS, WH, abyssal
		String[] indexNames = { "HS", "LS", "NS", "WS", "abyss" };
		long[] npc_kills = new long[indexNames.length];
		long[] ship_kills = new long[indexNames.length];
		long[] jumps = new long[indexNames.length];
		int[] totalsystems = new int[indexNames.length];
		int[] nokills = new int[indexNames.length];
		int[] nojumps = new int[indexNames.length];
		int[] nokilljump = new int[indexNames.length];
		for (Integer sys_id : cache.universe.systems().get()) {
			R_get_universe_systems_system_id system = cache.universe.systems(sys_id).get();
			int index = 0;
			if (whconstels.contains(system.constellation_id)) {
				index=3;
			} else if (abyssConstels.contains(system.constellation_id)) {
				index = 4;
			} else {
				index= system.security_status > 0.45 ? 0 : system.security_status > 0.0 ? 1 : 2;
			}

			R_get_universe_system_kills syskills = kills.get(system.system_id);
			if (syskills != null) {
				npc_kills[index] += syskills.npc_kills;
				ship_kills[index] += syskills.ship_kills;
			} else {
				nokills[index]++;
			}

			R_get_universe_system_jumps sysjumps = jumps_m.get(system.system_id);
			if (sysjumps != null) {
				jumps[index] += sysjumps.ship_jumps;
			} else {
				nojumps[index]++;
			}

			if (syskills == null && sysjumps == null) {
				nokilljump[index]++;
				nokills[index]--;
				nojumps[index]--;

			}
			totalsystems[index]++;
		}
		System.out.println(
				"space\tship kills\tnpc kills\tjumps\tnpc / ship\tnpc / jumps\t#systems\tnokilldata\tnojumpdata\tnok&j");
		for (int i = 0; i < indexNames.length; i++) {
			System.out.println(indexNames[i] + "\t" + ship_kills[i] + "\t" + npc_kills[i] + "\t" + jumps[i] + "\t"
					+ 1.0f * npc_kills[i] / ship_kills[i] + "\t" + 1.0f * npc_kills[i] / jumps[i]
							+ "\t" + totalsystems[i] + "\t" + nokills[i] + "\t" + nojumps[i] + "\t" + nokilljump[i]);
		}
	}

}
