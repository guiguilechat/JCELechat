package fr.guiguilechat.jcelechat.libs.routesolver;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.Constellation;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;

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
 * {@link RouteParams#includeConstellation constellations} and
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

	private final RouteParams params;

	public LinkedHashSet<SolarSystem> required = new LinkedHashSet<>();
	public LinkedHashSet<SolarSystem> optional = new LinkedHashSet<>();

	public PlanningAnalysis(RouteParams params) {
		this.params = params;
		build();
	}

	protected void build() {
		required.add(params.start());
		required.add(params.stop());
		for (String sn : params.includeSystems) {
			SolarSystem ss = SolarSystem.getSystem(sn);
			if (ss == null) {
				System.err.println("no system for " + sn);
			}
			required.add(ss);
		}

		// which security of included constellations and regions are allowed for
		// implicit systems ?
		EnumSet<SolarSystem.SECSTATUS> allowed = EnumSet.noneOf(SECSTATUS.class);
		if (params.keepSec) {
			for (SolarSystem ss : required) {
				allowed.add(ss.secStatus());
			}
		} else {
			allowed.addAll(Arrays.asList(SECSTATUS.values()));
		}

		// add the implicit required systems.
		Predicate<SolarSystem> implicitRequiredFilter = ss -> allowed.contains(ss.secStatus());
		for (String cn : params.includeConstellation) {
			Constellation cs = Constellation.getConstellation(cn);
			cs.systems.stream().map(SolarSystem::getSystem).filter(implicitRequiredFilter).forEach(required::add);
		}
		for (String rn : params.includeConstellation) {
			Region rg = Region.getRegion(rn);
			rg.systems().map(SolarSystem::getSystem).filter(implicitRequiredFilter).forEach(required::add);
		}

		// then add the optional systems.
		if (!params.limitSystems) {
			for (SolarSystem ss1 : required) {
				for (SolarSystem ss2 : required) {
					if (ss1.name.compareTo(ss2.name) < 0) {

					}
				}
			}
		}

	}

}
