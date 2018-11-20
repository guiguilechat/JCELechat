package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.Universe.TripDistance;

public class TestUniverse {

	protected static fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.Universe uni = ESIAccess.INSTANCE.universe;

	public static void main(String[] args) {
		int jitaIV4=60003760;
		int perimeter = 30000144;
		int osmon = 30000180;
		int airaken = 30000185;

		evaluateTimes(compute(jitaIV4, perimeter));
		evaluateTimes(compute(jitaIV4, osmon));
		evaluateTimes(compute(jitaIV4, airaken));
	}

	protected static TripDistance compute(int stationFrom, int systemDest) {
		return uni
				.getDistance(uni.con.get_universe_stations(stationFrom, null), uni.con.get_universe_systems(systemDest, null))
				.get();
	}

	protected static void evaluateTimes(TripDistance trip) {
		System.err.println("for " + trip.jumps + " jumps over " + trip.AU + "AU");
		System.err.println("freeigther " + Universe.time_travel(trip.jumps, trip.AU, 1.5, 42, 97.5));
		System.err.println("kryos " + Universe.time_travel(trip.jumps, trip.AU, 3, 11, 187.5));
		System.err.println("inty " + Universe.time_travel(trip.jumps, trip.AU, 8, 2, 525));
	}

}
