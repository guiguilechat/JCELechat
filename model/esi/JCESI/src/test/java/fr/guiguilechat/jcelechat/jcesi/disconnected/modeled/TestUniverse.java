package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.Universe.TripDistance;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;

public class TestUniverse {

	protected static fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.Universe uni = ESIAccess.INSTANCE.universe;

	static int jitaIV4 = 60003760;
	static int lanngisiIII2 = 60012625;
	static int perimeter = 30000144;
	static int osmon = 30000180;
	static int airaken = 30000185;

	public static void main(String[] args) {

		// evaluateTimes(compute(jitaIV4, perimeter));
		// evaluateTimes(compute(jitaIV4, osmon));
		// evaluateTimes(compute(jitaIV4, airaken));
		for (Entry<R_get_universe_systems_system_id, TripDistance> e : uni.distancesOneConstelJump(lanngisiIII2)
				.entrySet()) {
			System.err.println(e.getKey().name + " : " + e.getValue());
		}
	}

	protected static TripDistance compute(int stationFrom, int systemDest) {
		return uni
				.getDistance(uni.con.get_universe_stations(stationFrom, null).getOK(),
						uni.con.get_universe_systems(systemDest, null).getOK())
				.get();
	}

	protected static void evaluateTimes(TripDistance trip) {
		System.err.println("for " + trip.jumps + " jumps over " + trip.AU + "AU");
		System.err.println("freeigther " + Universe.time_travel(trip.jumps, trip.AU, 1.5, 42, 97.5));
		System.err.println("kryos " + Universe.time_travel(trip.jumps, trip.AU, 3, 11, 187.5));
		System.err.println("inty " + Universe.time_travel(trip.jumps, trip.AU, 8, 2, 525));
	}

	@Test
	public void testMethod() {
		Assert.assertEquals(compute(jitaIV4, perimeter).jumps, 1);
		Assert.assertEquals(Universe.time_travel(0, 10, 6, 2, 500), 2 + 4 + 1 + 11 + 1);
	}

}
