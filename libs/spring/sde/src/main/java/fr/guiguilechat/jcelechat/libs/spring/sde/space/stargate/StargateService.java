package fr.guiguilechat.jcelechat.libs.spring.sde.space.stargate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import fr.guiguilechat.jcelechat.model.formula.space.WarpTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class StargateService extends
		SdeEntityService<Stargate, Integer, StargateRepository> {

	public StargateService() {
		super(Stargate::new);
	}

	//
	// warp management

	public static record WarpJumpDist(int start, int end, double distance) implements Serializable {

		public static WarpJumpDist of(Object[] arr) {
			return new WarpJumpDist(
					((Stargate) arr[0]).getId(),
					((Stargate) arr[1]).getId(),
					(double) arr[2]);
		}

		public WarpJumpDist(Station from, Stargate end, double distance) {
			this(from.getId(), end.getId(), distance);
		}
	}

	public static record WarpDist(int start, int end, double distance) implements Serializable {

		public WarpDist(Stargate from, Station end, double distance) {
			this(from.getId(), end.getId(), distance);
		}

	}

	/**
	 * @return all the couple of gates (start, end) for which you can start, warp to
	 *         a third gate, jump it, and arrive at the end ; also contains the
	 *         distance of the warp, in m
	 */
	@Cacheable("SdeUniverseWJD")
	public List<WarpJumpDist> warpJumpsG2G(boolean hsOnly) {
		return (hsOnly ? repo().listWarpJumpHS() : repo().listWarpJump()).stream().map(WarpJumpDist::of).toList();
	}

	/**
	 * @return all the stargates you can arrive to after a warp from the station and
	 *         a jump through another gate ; also contains the distance of the
	 *         warp, in m
	 */
	public List<WarpJumpDist> warpJumpsFrom(Station station) {
		return repo().listWarpJumpFromStation(
				station.getSolarSystem().getId(),
				station.getPosX().doubleValue(),
				station.getPosY().doubleValue(),
				station.getPosZ().doubleValue()).stream()
				.map(arr -> new WarpJumpDist(station, (Stargate) arr[0], (double) arr[1]))
				.toList();
	}

	/**
	 * @return all the stargates in the system of the station ; also contains their
	 *         distance to the station, in m
	 */
	public List<WarpDist> warpJumpsTo(Station station) {
		return repo().listWarpJumpToStation(station.getSolarSystem().getId(),
				station.getPosX().doubleValue(),
				station.getPosY().doubleValue(),
				station.getPosZ().doubleValue()).stream()
				.map(arr -> new WarpDist((Stargate) arr[0], station, (double) arr[1]))
				.toList();
	}

	/**
	 * a warp consists in
	 * <p>
	 * initial 0.5 s between the click and the server tick (plus latency)
	 * </p>
	 * <p>
	 * align phase, in seconds between two ticks (so ceil of align_s)
	 * </p>
	 * <p>
	 * acceleration phase : limited to speed_au = ws (then it's cruise phase)
	 * distance_m(time_s) = e(ws×t)
	 * speed_m(time_s) = ws×e(ws×t),
	 * speed_au(time_s)=ws×e(ws×t)/AU_IN_M = distance_m(time_s)×ws/AU_IN_M
	 * => speed(distance)=d×ws
	 * </p>
	 * <p>
	 * deceleration phase : uses j= min(ws, 6)/3 , it's acceleration from the end (t
	 * is time before the warp end)
	 * speed_m(time_s)=ws×e(j×t)
	 * distance_m(time_s)=ws/j×e(j×t) = speed_m(time_s)/j
	 * speed(distance)= d×j
	 * time_s(distance_m) = ln(j/ws × distance)/j
	 * meeting point : the moment when acceleration and deceleration meet is when
	 * speed_accel(d) = speed_decel(warpdistance-d)
	 * <=> ws×d = j×(warpdistance-d)
	 * <=> d = j×warpdistance / (ws+j)
	 * Also speed can't go higher than ws so acceleration phase limited to 1 AU
	 * And deceleration phase limited to distance=ws/j
	 * </p>
	 * <p>
	 * cruise phase : distance_au(time_s) = ws×t . But only between acceleration and
	 * deceleration
	 * </p>
	 *
	 * @param distance_m     distance to warp over
	 * @param align_s        ship time to align of the ship (in fitting screen).
	 *                       Will be rounded up.
	 * @param warpspeed_uaps warp speed attribute of the ship (in fitting screen)
	 * @return average time in s to actually perform the warp from standstill point.
	 */
	public static double convertWarpTotime(double distance_m, double align_s, double warpspeed_aups) {
		return 0.5 + Math.ceil(align_s)
				+ WarpTime.of((float) (distance_m / WarpTime.AU_IN_M), warpspeed_aups, 100);
	}

	public static record TravelTime(int start, int end, double time) implements Serializable {

		public static TravelTime from(WarpJumpDist warpJumpDist, double align_s, double warpspeed_aups) {
			// add 10s for jump
			return new TravelTime(warpJumpDist.start, warpJumpDist.end,
					10 + convertWarpTotime(warpJumpDist.distance(), align_s, warpspeed_aups));
		}

		public static TravelTime from(WarpDist warpDist, double align_s, double warpspeed_aups) {
			return new TravelTime(warpDist.start, warpDist.end,
					convertWarpTotime(warpDist.distance(), align_s, warpspeed_aups));
		}
	}

	/**
	 * @param start          station to include the warp time from towards an out
	 *                       gate
	 * @param end            station to include the warp time toward, from each
	 *                       in-system gate
	 * @param align_s        time to align, in s
	 * @param warpspeed_aups ship warp speed, in AU per s
	 * @param hsOnly         if true, only allow to travel to a stargate when its
	 *                       system is HS. Does not impact start station, but does
	 *                       impact end station since its stargates won't be
	 *                       reachable.
	 * @return all the travel times for warp-jump between stargates, including FROM
	 *         start station as well as direct warp from stargate to end station
	 */
	public List<TravelTime> travelTimes(Station start, Station end, double align_s, double warpspeed_aups,
			boolean hsOnly) {
		if (start.getSolarSystem().getId() == end.getSolarSystem().getId()) {
			return List.of(new TravelTime(start.getId(), end.getId(),
					convertWarpTotime(
							Math.sqrt(Math.pow(start.getPosX().doubleValue() - end.getPosX().doubleValue(), 2)
									+ Math.pow(start.getPosY().doubleValue() - end.getPosY().doubleValue(), 2)
									+ Math.pow(start.getPosZ().doubleValue() - end.getPosZ().doubleValue(), 2)),
							align_s, warpspeed_aups)));
		}
		return Stream.of(
				warpJumpsFrom(start).stream().map(wjd -> TravelTime.from(wjd, align_s, warpspeed_aups)),
				warpJumpsG2G(hsOnly).stream().map(wjd -> TravelTime.from(wjd, align_s, warpspeed_aups)),
				warpJumpsTo(end).stream().map(jd -> TravelTime.from(jd, align_s, warpspeed_aups))).flatMap(s -> s)
				.toList();
	}

	static class AStar {

		/** known static distances */
		private final Map<Integer, Map<Integer, Double>> distances = new HashMap<>();

		public void addDistance(int from, int to, double distance) {
			Map<Integer, Double> m = distances.computeIfAbsent(from, _ -> new HashMap<>(10));
			// System.err.println("map size=" + m.size() + " , distances size=" +
			// distances.size());
			m.put(to, Math.min(distance, m.getOrDefault(to, Double.POSITIVE_INFINITY)));
		}

		public double distanceDirect(int from, int to) {
			Map<Integer, Double> m = distances.get(from);
			return m == null ? Double.POSITIVE_INFINITY : m.getOrDefault(to, Double.POSITIVE_INFINITY);
		}

		//

		/** distance we got to reach a node, as well as its previous */
		@Getter
		@RequiredArgsConstructor
		static class NodeReach {
			private final int node;
			private Integer previous = null;
			private double distance = Double.POSITIVE_INFINITY;

			public boolean with(int newPrevious, double newdistance) {
				if (previous == null || newdistance < distance) {
					previous = newPrevious;
					distance = newdistance;
					return true;
				}
				return false;
			}
		}

		/**
		 * set to false whenever a new node reach is changed.
		 */
		private boolean sorted = false;

		/** nodes we have a way to reach, by node */
		private final Map<Integer, NodeReach> reachable = new HashMap<>();

		/**
		 * nodes we have a way to reach and not already iterated over, sorted by lowest
		 * distance
		 */
		private final List<NodeReach> sortedNext = new ArrayList<>();

		private final Set<Integer> alreadyProcessed = new HashSet<>();

		protected void addReach(int reached, int previous, double distance) {
			NodeReach nr = reachable.get(reached);
			if (nr == null) {
				nr = new NodeReach(reached);
				sortedNext.add(nr);
				reachable.put(reached, nr);
				sorted = false;
			}
			if (nr.with(previous, distance)) {
				sorted = false;
			}
		}

		public void addStart(int start) {
			addReach(start, start, 0);
		}

		protected NodeReach popNext() {
			if (!sorted) {
				Collections.sort(sortedNext, Comparator.comparing(NodeReach::getDistance));
				sorted = true;
			}
			if (sortedNext.isEmpty()) {
				return null;
			}
			NodeReach ret = sortedNext.remove(0);
			alreadyProcessed.add(ret.node);
			return ret;
		}

		protected void process(NodeReach nodereach) {
			double distance = nodereach.getDistance();
			Map<Integer, Double> targets = distances.get(nodereach.getNode());
			if (targets == null) {
				return;
			}
			for (Entry<Integer, Double> e : targets.entrySet()) {
				addReach(e.getKey(), nodereach.getNode(), distance + e.getValue());
			}
		}

		public void find(int node) {
			if (alreadyProcessed.contains(node)) {
				return;
			}
			NodeReach nextNode = null;
			while ((nextNode = popNext()) != null) {
				process(nextNode);
				if (nextNode.getNode() == node) {
					return;
				}
			}
		}

		public List<Integer> pathTo(int node) {
			find(node);
			List<Integer> ret = new ArrayList<>();
			NodeReach r = reachable.get(node);
			while (r != null && r.getPrevious() != r.getNode()) {
				ret.add(0, r.getNode());
				r = reachable.get(r.getPrevious());
			}
			return ret;
		}
	}

	public static record WayPoint(
			int targetId,
			String targetName,
			long duration_s) {
	}

	/**
	 * find the quickest (as sum of duration) path from a station to another one,
	 * using ship properties. This is using an astar pathing, with weight being
	 * warp+jump time
	 *
	 * @param start          station to start from
	 * @param end            station to end at
	 * @param align_s        time to align, in s, of the ship
	 * @param warpspeed_aups warp speed, in AU/s, of the ship
	 * @param hsOnly         when true, only HS stargates are used
	 * @return the list of system jumps to performs, as well as the jump duration,
	 *         to reach the station in the fastest way possible
	 */
	public List<WayPoint> travel(Station start, Station end, double align_s, double warpspeed_aups,
			boolean hsOnly) {
		List<TravelTime> travelTimes = travelTimes(start, end, align_s, warpspeed_aups, hsOnly);
		AStar astar = new AStar();
		travelTimes.forEach(tt -> astar.addDistance(tt.start(), tt.end(), tt.time()));
		astar.addStart(start.getId());
		List<Integer> steps = astar.pathTo(end.getId());
		List<WayPoint> ret = new ArrayList<>();
		int lastPos = start.getId();
		for (int step : steps) {
			long duration_s = (long) Math.ceil(astar.distanceDirect(lastPos, step));
			if (step == end.getId()) {
				ret.add(new WayPoint(step, end.name(), duration_s));
			} else {
				SolarSystem ss = repo().findById(step).get().getSolarSystem();
				ret.add(new WayPoint(ss.getId(), ss.getName(), duration_s));
			}
			lastPos = step;
		}
		return ret;
	}

}
