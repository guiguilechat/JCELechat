package fr.guiguilechat.jcelechat.libs.routesolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.route.Route;

public class RouteParams {

	/** start the route from this system */
	public String startSystem;

	public SolarSystem start() {
		return SolarSystem.getSystem(startSystem);
	}

	public RouteParams withStart(String start) {
		startSystem = start;
		return this;
	}

	/**
	 * stop the route in that system. If null, use {@link #startSystem}
	 */
	public String stopSystem = null;

	public SolarSystem stop() {
		return stopSystem == null ? SolarSystem.getSystem(startSystem) : SolarSystem.getSystem(stopSystem);
	}

	public RouteParams withStop(String stop) {
		stopSystem = stop;
		return this;
	}

	/**
	 * the route must visit all the systems in those regions when they are
	 * reachable from start system.
	 */
	public Set<String> includeRegions = new HashSet<>();

	public RouteParams withIncludeRegions(String... regions) {
		includeRegions.addAll(Arrays.asList(regions));
		return this;
	}

	/**
	 * the route must visit all the systems in those constellations when they are
	 * reachable from start system.
	 */
	public Set<String> includeConstellations = new HashSet<>();

	public RouteParams withIncludeConstellations(String... constellations) {
		includeConstellations.addAll(Arrays.asList(constellations));
		return this;
	}

	/**
	 * the route must visit all those systems.
	 */
	public Set<String> includeSystems = new HashSet<>();

	public RouteParams withIncludeSystem(String... systems) {
		includeSystems.addAll(Arrays.asList(systems));
		return this;
	}

	/**
	 * if true, only go to systems that are in the same security status (HS, NS,
	 * LS) than the start, stop and included systems.
	 */
	public boolean keepSec = true;

	public RouteParams withKeepSec(boolean keepSec) {
		this.keepSec = keepSec;
		return this;
	}

	/**
	 * if true, only visit systems that are in the include list. Of course this
	 * makes no sense if the start or stop is not reachable from at least one of
	 * those.
	 */
	public boolean limitSystems = false;

	public RouteParams withLimitSystems(boolean limits) {
		limitSystems = limits;
		return this;
	}

	public Route solve() {
		return null;
	}

}
