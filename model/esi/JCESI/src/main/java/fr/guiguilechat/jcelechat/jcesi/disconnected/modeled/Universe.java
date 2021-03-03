package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_3_xnumber_ynumber_znumber;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Universe {

	@Getter
	@Accessors(fluent = true)
	private final ESIStatic con;

	@Getter
	@Accessors(fluent = true)
	private final fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Universe cache;

	public Universe(ESIStatic connection) {
		con = connection;
		cache = connection.cache().universe;
	}

	//
	// names
	//

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
			IntStream.of(ids).filter(i -> !cachedNames.containsKey(i));
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
					log.error("could not load names for ids" + IntStream.of(ids).mapToObj(i -> i).collect(Collectors.toList())
							+ " resp=" + newreq.getResponseCode() + " err=" + newreq.getError());
				}
			}
			return IntStream.of(ids).mapToObj(cachedNames::get).toArray(R_post_universe_names[]::new);
		}
	}

	//
	// access to public structures
	//


	@Getter(lazy = true)
	private final ObsSetHolder<Long> publicStructures = con().cache().universe.structures(null).distinct();

	public boolean isPublicStructure(long structureid) {
		return getPublicStructures().get().contains(structureid);
	}

	public boolean isPublicDockable(long locationID) {
		if (locationID < 60000000l) {
			return false;
		}
		if (locationID < 61000000l) {
			return true;
		}
		return isPublicStructure(locationID);
	}

	//
	// distances between systems, constellations, regions
	//

	/**
	 * a trip from a place to another is both a number of AU to jump, as well as a
	 * number of jumps to perform. This is a simplification because "short jump"
	 * take more time per AU to do them.
	 *
	 */
	public static class TripDistance {
		/** number of jumps taken */
		public int jumps;
		/**
		 * total distance in AU between gates, the original station, and the ending
		 * sun
		 */
		public double AU;

		@Override
		public String toString() {
			return "" + jumps + "jumps " + AU + "AU";
		}
	}

	/**
	 * get the distances to the sun of systems that are on the constellation, or
	 * the adjacent constellations, of a station.
	 *
	 * @param stationId
	 *          the id of the station
	 * @return the map for each system to its tripdistance
	 */
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

	private int[] pochvenSystems = null;

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
		if (pochvenSystems == null) {
			synchronized (this) {
				if (pochvenSystems == null) {
					List<ObsObjHolder<R_get_universe_constellations_constellation_id>> constellations = IntStream
							.of(con.cache().universe.regions(10000070).get().constellations)
							.mapToObj(c -> con.cache().universe.constellations(c)).collect(Collectors.toList());
					pochvenSystems = constellations.parallelStream().flatMapToInt(c -> IntStream.of(c.get().systems)).toArray();
					log.debug("pochven systems are " + IntStream.of(pochvenSystems).boxed().collect(Collectors.toList()));
				}
			}
		}
		List<R_get_universe_systems_system_id> systems = con.cache().route
				.get(pochvenSystems, null, destination.system_id, flag.secure, station.system_id).get().parallelStream()
				.map(si -> cache.systems(si).get()).collect(Collectors.toList());
		M_3_xnumber_ynumber_znumber lastPos = station.position;
		R_get_universe_systems_system_id lastSys = cache.systems(station.system_id).get();
		// System.err.println("starting from " + station.name);
		for (R_get_universe_systems_system_id nextSys : systems) {
			if (nextSys.system_id == lastSys.system_id) {
				continue;
			}
			R_get_universe_systems_system_id flastSys = lastSys;
			R_get_universe_stargates_stargate_id nextGate = IntStream.of(lastSys.stargates)
					.mapToObj(stargate -> cache.stargates(stargate).get())
					.filter(sg -> sg.destination.system_id == nextSys.system_id).findFirst()
					.orElseThrow(() -> new RuntimeException("can't find gate from " + flastSys.name + " to " + nextSys.name));
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
					ObsObjHolderSimple<TripDistance> fret = new ObsObjHolderSimple<>();
					ret = fret;
					cachedDistances.put(key, ret);
					new Thread(() -> fret.set(computeDistance(station, destination))).start();
				}
			}
		}
		return ret;
	}

	/**
	 * check if an id is correct for a region.
	 *
	 * @param regionID
	 * @return
	 */
	public boolean correctRegionID(int regionID) {
		return cache.regions().get().contains(regionID);
	}

	// default 0;0;0 pos
	public static final M_3_xnumber_ynumber_znumber SUN_POS = new M_3_xnumber_ynumber_znumber();

	public static double distance(M_3_xnumber_ynumber_znumber pos1, M_3_xnumber_ynumber_znumber pos2) {
		return Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2) + Math.pow(pos1.z - pos2.z, 2));
	}

	/**
	 * evaluates the time, in seconds, to travel through given parameters assuming
	 * a ship with given stats
	 *
	 * @param nbGates
	 *          total number of gate to take to make. amount of warp jumps is
	 *          assumed to be this number plus one.
	 * @param nbAU
	 *          total number of AU traveled. this is the sum, per gate-to-gate, of
	 *          the distance from one gate to another, and per start and end, from
	 *          the warp destination to the warp exit.
	 * @param warpseed
	 *          ship warp speed, in AU per second.
	 * @param align
	 *          ship align time, in seconds. This needs to be rounded up from the
	 *          exact ship align time due to server tick mechanism.
	 * @param speedmps
	 *          the subwarp speed, in metter per second, of the ship. This value
	 *          being <200 can increase the exit off warp delay.
	 * @return
	 */
	public static int time_travel(int nbGates, double nbAU, double warpseed, int align, double speedmps) {
		// time to accelerate from 0 to cruise speed
		double time_accel = Math.log(M_PER_AU) / warpseed;
		// effective deceleration warpspeed
		double decel_warpseed = Math.min(warpseed / 3, 2);
		// speed at which we can exit warp.
		double decel_speed = Math.min(100, speedmps / 2);
		// time to reach from cruise speed to decel speed.
		double time_decel = Math.log(warpseed * M_PER_AU / decel_speed) / decel_warpseed;
		// distance in AU to decelerate from cruise to exit warp.
		double dist_decel = warpseed / decel_warpseed;
		int nbjumps = nbGates + 1;
		// time required to go from 0 speed to cruise speed, and then exit warp.
		int time_per_jump_change = (int) (align + Math.ceil(time_accel + time_decel));
		// cruise distance is the effective distance we travel at cruise speed.
		double cruise_distance = Math.max(nbAU - nbjumps * (dist_decel + 1), 0);

		return (int) (time_per_jump_change * nbjumps + nbGates * 10 + Math.ceil(cruise_distance / warpseed));
	}

	/**
	 * get the highest distance, in AU, between gates in a system
	 *
	 * @param system
	 *          the system.
	 * @return
	 */
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
			planets = Stream.of(system.planets).map(planet -> cache.planets(planet.planet_id)).toArray(ObsObjHolder[]::new);
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

	/**
	 * get the HS anomaly rate of a system, over a range. This rate is equal to
	 * the number of HS systems in that range, with lower truesec than the system
	 * considered, divided by the total number of systems with lower truesec than
	 * the system considered.
	 *
	 * @param system
	 *          the center system we consider
	 * @param minDist
	 *          minimal distance (in gate jumps) at which we consider the area.
	 * @param maxDist
	 *          maximal distance (in gate jumps) at which we consider the area.
	 * @return the rate.
	 */
	public double systemHSAnomRate(R_get_universe_systems_system_id system, int minDist, int maxDist) {
		Set<Integer> doneSystems = new HashSet<>();
		Set<Integer> reachableHS = new HashSet<>();
		reachableHS.add(system.system_id);
		List<R_get_universe_systems_system_id> nextRange = Arrays.asList(system);
		for (int dist = 1; dist <= maxDist; dist++) {
			for (R_get_universe_systems_system_id s : nextRange) {
				doneSystems.add(s.system_id);
			}
			nextRange = nextRange.parallelStream().map(sys -> getAdjacentSystems(sys.system_id))
					.flatMap(h -> h.get().stream()).distinct().filter(s -> !doneSystems.contains(s.system_id))
					.collect(Collectors.toList());
		}
		return 0.0;
	}

	private HashMap<Integer, ObsListHolder<R_get_universe_systems_system_id>> cachedAdjacentSystems = new HashMap<>();

	public ObsListHolder<R_get_universe_systems_system_id> getAdjacentSystems(int systemId) {
		ObsListHolder<R_get_universe_systems_system_id> ret = cachedAdjacentSystems.get(systemId);
		if (ret == null) {
			synchronized (cachedAdjacentSystems) {
				ret = cachedAdjacentSystems.get(systemId);
				if (ret == null) {
					ret = cache.systems(systemId)
							.toList(sys -> IntStream.of(sys.stargates).mapToObj(i -> cache.stargates(i)).collect(Collectors.toList()))
							.mapItems(sgh -> cache.systems(sgh.get().destination.system_id)).mapItems(sysh -> sysh.get());
					cachedAdjacentSystems.put(systemId, ret);
				}
			}
		}
		return ret;
	}

	//
	// public structures
	//

	public void publicStructures() {
		// TODO ? is it doable ?
		cache.structures(null).map(sid -> cache);
	}

	// factions

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, R_get_universe_factions> factionsByID = cache.factions().toMap(f -> f.faction_id);

}
