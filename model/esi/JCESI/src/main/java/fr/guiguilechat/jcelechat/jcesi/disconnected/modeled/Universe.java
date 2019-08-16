package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_3_xnumber_ynumber_znumber;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag;
import fr.lelouet.collectionholders.impl.ObsObjHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;

public class Universe {

	private static final Logger logger = LoggerFactory.getLogger(Universe.class);

	protected final ESIStatic con;
	public final fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Universe cache;

	public Universe(ESIStatic connection) {
		con = connection;
		cache = connection.cache.universe;
	}

	/**
	 * assuming a request takes at max 100 characters, and a max url size of 2083,
	 * each int will use this number of characters(+1 for the comma)
	 */
	public static final int MAXINTIDPERREQUEST = (2083 - 100) / (1 + (int) Math.ceil(Math.log10(Integer.MAX_VALUE)));
	public static final int MAXLONGIDPERREQUEST = (2083 - 100) / (1 + (int) Math.ceil(Math.log10(Long.MAX_VALUE)));

	private HashMap<Integer, R_post_universe_names> cachedNames = new HashMap<>();

	public R_post_universe_names[] names(int... ids) {
		if (ids == null || ids.length == 0) {
			return new R_post_universe_names[0];
		}
		synchronized (cachedNames) {
			// have to work with long, because CCP bug.
			int[] missingIds = IntStream.of(ids).filter(i -> !cachedNames.containsKey(i)).toArray();
			int[] fullbuffer = new int[MAXLONGIDPERREQUEST];
			for (int start = 0; start < missingIds.length; start += MAXLONGIDPERREQUEST) {
				if (start + MAXLONGIDPERREQUEST >= missingIds.length) {
					fullbuffer = new int[missingIds.length - start];
				}
				System.arraycopy(missingIds, start, fullbuffer, 0, fullbuffer.length);
				Requested<R_post_universe_names[]> newreq;

				do {
					newreq = ESIStatic.INSTANCE.post_universe_names(fullbuffer, null);
				} while (newreq == null || newreq.isServerError());

				if (newreq.isOk()) {
					for (R_post_universe_names n : newreq.getOK()) {
						cachedNames.put(n.id, n);
					}
				} else {
					logger
					.error("could not load names for ids" + IntStream.of(ids).mapToObj(i -> i).collect(Collectors.toList())
							+ " resp=" + newreq.getResponseCode() + " err=" + newreq.getError());
				}
			}
			return IntStream.of(ids).mapToObj(cachedNames::get).toArray(R_post_universe_names[]::new);
		}
	}

	private Set<Long> publicStructures = new HashSet<>();

	public boolean isPublicStructure(long structureid) {
		synchronized (publicStructures) {
			if (publicStructures.isEmpty()) {
				publicStructures.addAll(con.cache.universe.structures(null).get());
			}
		}
		return publicStructures.contains(structureid);
	}

	public static class TripDistance {
		// number of jumps taken
		public int jumps;
		// total distance in AU between gates, the original station, and the ending
		// sun
		public double AU;

		@Override
		public String toString() {
			return "" + jumps + "jumps " + AU + "AU";
		}
	}

	public Map<R_get_universe_systems_system_id, TripDistance> distancesOneConstelJump(int stationId) {
		R_get_universe_stations_station_id station = cache.stations(stationId).get();
		R_get_universe_systems_system_id system = cache.systems(station.system_id).get();
		List<R_get_universe_systems_system_id> destinations = systemsWithinOneConstelJump(system)
				.collect(Collectors.toList());

		return destinations.parallelStream().collect(Collectors.toMap(sys -> sys, sys -> getDistance(station, sys).get()));
	}

	/**
	 * stream over the system in one system constellation, or in neighbor
	 * constellations
	 *
	 * @param system
	 * @return a new stream.
	 */
	public Stream<R_get_universe_systems_system_id> systemsWithinOneConstelJump(R_get_universe_systems_system_id system) {
		R_get_universe_constellations_constellation_id constel = cache.constellations(system.constellation_id).get();
		List<ObsObjHolder<R_get_universe_systems_system_id>> systemsholders = adjacentConstels(constel)
				.flatMapToInt(co -> IntStream.of(co.systems)).mapToObj(cache::systems).collect(Collectors.toList());
		return systemsholders.parallelStream().map(sh -> sh.get());
	}

	/**
	 * stream over the constellation that are adjacent to one provided
	 *
	 * @param constel
	 *          a constellation
	 * @return the stream of constellations that are adjacent.
	 */
	public Stream<R_get_universe_constellations_constellation_id> adjacentConstels(
			R_get_universe_constellations_constellation_id constel) {
		List<ObsObjHolder<R_get_universe_systems_system_id>> neighboursArr = IntStream.of(constel.systems).parallel()
				.mapToObj(cache::systems).collect(Collectors.toList());
		List<ObsObjHolder<R_get_universe_stargates_stargate_id>> gates = neighboursArr.parallelStream()
				.flatMapToInt(n -> IntStream.of(n.get().stargates)).mapToObj(cache::stargates).collect(Collectors.toList());
		List<ObsObjHolder<R_get_universe_systems_system_id>> othersystems = gates.parallelStream()
				.map(gate -> cache.systems(gate.get().destination.system_id)).collect(Collectors.toList());
		int[] constelsIds = othersystems.parallelStream().mapToInt(os -> os.get().constellation_id).distinct().toArray();
		List<ObsObjHolder<R_get_universe_constellations_constellation_id>> constelsHolders = IntStream.of(constelsIds)
				.mapToObj(cache::constellations).collect(Collectors.toList());
		return constelsHolders.parallelStream().map(ch -> ch.get());
	}

	/** number of meter in an AU */
	public static final long M_PER_AU = 149597870700l;

	/**
	 * get the trip distance, from one station to the sun of a system
	 *
	 * @param systemOrigin
	 * @param systemDest
	 * @return the number of jumps and the total amount of UA to jump
	 */
	protected TripDistance computeDistance(R_get_universe_stations_station_id station,
			R_get_universe_systems_system_id destination) {
		TripDistance ret = new TripDistance();
		List<R_get_universe_systems_system_id> systems = con.cache.route
				.get(null, null, destination.system_id, flag.secure, station.system_id).get().parallelStream()
				.map(si -> cache.systems(si).get()).collect(Collectors.toList());
		M_3_xnumber_ynumber_znumber lastPos = station.position;
		R_get_universe_systems_system_id lastSys = cache.systems(station.system_id).get();
		// System.err.println("starting from " + station.name);
		for (R_get_universe_systems_system_id nextSys : systems) {
			if (nextSys.system_id == lastSys.system_id) {
				continue;
			}
			R_get_universe_stargates_stargate_id nextGate = IntStream.of(lastSys.stargates)
					.mapToObj(stargate -> cache.stargates(stargate).get())
					.filter(sg -> sg.destination.system_id == nextSys.system_id).findFirst().get();
			double dist_AU = distance(lastPos, nextGate.position) / M_PER_AU;
			// System.err.println("dist to " + nextSys.name + " is " + dist_AU);
			ret.AU += dist_AU;
			ret.jumps++;
			lastSys = nextSys;
			lastPos = cache.stargates(nextGate.destination.stargate_id).get().position;
		}
		double dist_AU = distance(lastPos, SUN_POS) / M_PER_AU;
		// System.err.println("dist to sun is " + dist_AU);
		ret.AU += dist_AU;
		return ret;
	}

	protected HashMap<Map<Integer, Integer>, ObsObjHolder<TripDistance>> cachedDistances = new HashMap<>();

	/**
	 * get the holder on the distance from a station to the sun of a solar system
	 *
	 * @param station
	 * @param destination
	 * @return a holder. if need, created and a thread is started to compute and
	 *         hold the value.
	 */
	public ObsObjHolder<TripDistance> getDistance(R_get_universe_stations_station_id station,
			R_get_universe_systems_system_id destination) {
		Map<Integer, Integer> key = new HashMap<>();
		key.put(station.station_id, destination.system_id);
		ObsObjHolder<TripDistance> ret = cachedDistances.get(key);
		if (ret == null) {
			synchronized (cachedDistances) {
				ret = cachedDistances.get(key);
				if (ret == null) {
					SimpleObjectProperty<TripDistance> underlying = new SimpleObjectProperty<>();
					ret = new ObsObjHolderImpl<>(underlying);
					cachedDistances.put(key, ret);
					new Thread(() -> underlying.set(computeDistance(station, destination))).start();
				}
			}
		}
		return ret;
	}

	public boolean correctRegionID(int regionID) {
		return cache.regions().get().contains(regionID);
	}

	// default 0;0;0 pos
	public static final M_3_xnumber_ynumber_znumber SUN_POS = new M_3_xnumber_ynumber_znumber();

	public static double distance(M_3_xnumber_ynumber_znumber pos1, M_3_xnumber_ynumber_znumber pos2) {
		return Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2) + Math.pow(pos1.z - pos2.z, 2));
	}

	public static int time_travel(int nbjumps, double nbAU, double warpseed, int align, double speedmps) {
		double time_accel = Math.log(M_PER_AU) / warpseed;
		double decel_warpseed = Math.min(warpseed / 3, 2);
		double decel_speed = Math.min(100, speedmps / 2);
		double time_decel = Math.log(warpseed * M_PER_AU / decel_speed) / decel_warpseed;
		double dist_decel = warpseed / decel_warpseed;
		int time_per_jump = (int) (10 + align + Math.ceil(time_accel + time_decel));
		double cruise_distance = Math.max(nbAU - (nbjumps + 1) * (dist_decel + 1), 0);
		// System.err.println("ship goes " + warpseed + "AU/s and " + speedmps +
		// "m/s, align " + align + " : time per jump="
		// + time_per_jump + " distDecel" + dist_decel + ", time accel " +
		// time_accel + " ,time decel " + time_decel);
		return (int) (time_per_jump * nbjumps + align + Math.ceil(time_accel + time_decel)
		+ Math.ceil(cruise_distance / warpseed));
	}

	@SuppressWarnings("unchecked")
	public double systemRadiusGates(R_get_universe_systems_system_id system) {
		double maxdist = 0;
		if (system.stargates == null || system.stargates.length == 0) {
			return 0.0;
		}
		ObsObjHolder<R_get_universe_stargates_stargate_id>[] gates = IntStream.of(system.stargates)
				.mapToObj(stargate -> cache.stargates(stargate)).toArray(ObsObjHolder[]::new);
		for (int i = 0; i < gates.length; i++) {
			for (int j = i + 1; j < gates.length; j++) {
				double distance = distance(gates[i].get().position, gates[j].get().position) / M_PER_AU;
				if (maxdist < distance) {
					maxdist = distance;
				}
			}
		}
		return maxdist;
	}

	@SuppressWarnings("unchecked")
	public double systemRadiusCelestials(R_get_universe_systems_system_id system) {
		double maxdist = 0;
		ObsObjHolder<R_get_universe_stargates_stargate_id>[] gates = new ObsObjHolder[0];
		if (system.stargates != null) {
			gates = IntStream.of(system.stargates).mapToObj(stargate -> cache.stargates(stargate))
					.toArray(ObsObjHolder[]::new);
		}
		ObsObjHolder<R_get_universe_planets_planet_id>[] planets = new ObsObjHolder[0];
		if (system.planets != null) {
			planets =
					Stream.of(system.planets)
					.map(planet -> cache.planets(planet.planet_id)).toArray(ObsObjHolder[]::new);
		}
		M_3_xnumber_ynumber_znumber[] positions = Stream
				.concat(Stream.of(gates).map(gate -> gate.get().position), Stream.of(planets).map(plan -> plan.get().position))
				.toArray(M_3_xnumber_ynumber_znumber[]::new);
		for (int i = 0; i < positions.length; i++) {
			for (int j = i + 1; j < positions.length; j++) {
				double distance = distance(positions[i], positions[j]) / M_PER_AU;
				if (maxdist < distance) {
					maxdist = distance;
				}
			}
		}
		return maxdist;
	}

}
