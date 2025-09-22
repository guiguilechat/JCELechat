package fr.guiguilechat.jcelechat.libs.routesolver;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.locations.Constellation;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;
import fr.guiguilechat.jcelechat.model.sde.locations.route.PredicateRouter;

/**
 * analysis of a planning. This consist in the listing of the systems that are
 * required in the route, and those that are possible in the route.
 *
 * <p>
 * explicit required systems are the {@link RouteParams#startSystem start}, the
 * {@link RouteParams#stopSystem stop}, and the
 * {@link RouteParams#includeSystems included systems}.
 * </p>
 *
 * <p>
 * implicit required systems are those in the included
 * {@link RouteParams#includeConstellations constellations} and
 * {@link RouteParams#includeRegions regions} that share the security status
 * with one explicit required system, or all of them if
 * {@link RouteParams#keepSec} is set to false.
 * </p>
 *
 * <p>
 * optional systems are those between two required systems. This is empty if
 * {@link RouteParams#limitSystems is set to true}.
 * </p>
 */
public class PlanningAnalysis {

	private static final Logger logger = LoggerFactory.getLogger(PlanningAnalysis.class);

	private final RouteParams params;

	public LinkedHashSet<SolarSystem> required = new LinkedHashSet<>();
	public LinkedHashSet<SolarSystem> optional = new LinkedHashSet<>();

	public Map<SolarSystem, Integer> system2Index = new HashMap<>();

	public int system(SolarSystem ss) {
		return system2Index.getOrDefault(ss, -1);
	}

	public SolarSystem[] index2system;

	public SolarSystem system(int index) {
		return index2system[index];
	}

	public PlanningAnalysis(RouteParams params) {
		this.params = params;
		build();
	}

	protected void build() {
		SolarSystem start = params.start();
		SolarSystem stop = params.stop();
		required.add(start);
		required.add(stop);
		for (String sn : params.includeSystems) {
			SolarSystem ss = SolarSystem.getSystem(sn);
			if (ss == null) {
				logger.error("no system for " + sn);
			}
			required.add(ss);
		}

		Predicate<SolarSystem> pred = _ -> true;
		// which security of included constellations and regions are allowed for
		// implicit systems ?

		EnumSet<SolarSystem.SECSTATUS> allowedSecStatus = EnumSet.noneOf(SECSTATUS.class);
		if (params.keepSec) {
			for (SolarSystem ss : required) {
				allowedSecStatus.add(ss.secStatus());
			}
			pred = ss -> allowedSecStatus.contains(ss.secStatus());
		}
		PredicateRouter router = new PredicateRouter(pred);

		// check that each explicitly required system, besides the source, is
		// reachable from source
		if (params.keepSec) {
			for (SolarSystem ss : required) {
				if (ss != start) {
					int[] route = router.getRoute(start.id, ss.id);
					if (route == null || route.length == 0) {
						logger.warn("can't find route from " + start.name + " to " + ss.name
								+ " that only matches systems secutiry " + allowedSecStatus);
					}
				}
			}
		}

		// add the implicit required systems.
		Predicate<SolarSystem> implicitRequiredFilter = ss -> allowedSecStatus.contains(ss.secStatus())
				&& router.getRoute(start.id, ss.id).length > 0;
				for (String cn : params.includeConstellations) {
					Constellation cs = Constellation.getConstellation(cn);
					cs.systems.stream().map(SolarSystem::getSystem).filter(implicitRequiredFilter).forEach(required::add);
				}
				for (String rn : params.includeRegions) {
					Region rg = Region.getRegion(rn);
					rg.systems().map(SolarSystem::getSystem).filter(implicitRequiredFilter).forEach(required::add);
				}
				// then add the optional systems.
				if (!params.limitSystems) {
					for (SolarSystem ss1 : required) {
						for (SolarSystem ss2 : required) {
							if (ss1.name.compareTo(ss2.name) < 0) {
								int[] intermediates = router.getRoute(ss1.id, ss2.id);
								for (int i = 0; i < intermediates.length - 1; i++) {
									SolarSystem intermediate = SolarSystem.getSystem(intermediates[i]);
									if (!required.contains(intermediate)) {
										optional.add(intermediate);
									}
								}
							}
						}
					}
				}

				index2system = Stream.concat(required.stream(), optional.stream()).toArray(SolarSystem[]::new);
				for (int i = 0; i < index2system.length; i++) {
					system2Index.put(index2system[i], i);
				}

	}

}
