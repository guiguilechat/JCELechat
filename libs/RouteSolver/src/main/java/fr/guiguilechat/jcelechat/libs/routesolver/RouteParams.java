package fr.guiguilechat.jcelechat.libs.routesolver;

import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class RouteParams {

	/** start the route from this system */
	public String startSystem;

	public SolarSystem start() {
		return SolarSystem.getSystem(startSystem);
	}

	public RouteParams witStart(String start) {
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

	public RouteParams witStop(String stop) {
		stopSystem = stop;
		return this;
	}

	/**
	 * the route must visit all the systems in those regions when they are
	 * reachable from start system.
	 */
	public Set<String> includeRegions = new HashSet<>();

	/**
	 * the route must visit all the systems in those constellations when they are
	 * reachable from start system.
	 */
	public Set<String> includeConstellation = new HashSet<>();

	/**
	 * the route must visit all those systems.
	 */
	public Set<String> includeSystems = new HashSet<>();

	/**
	 * if true, only go to systems that are in the same security status (HS, NS,
	 * LS) than the start, stop and included systems.
	 */
	public boolean keepSec = true;

	/**
	 * if true, only visit systems that are in the include list. Of course this
	 * makes no sense if the start or stop is not reachable from at least one of
	 * those.
	 */
	public boolean limitSystems = false;

	public Route solve() {
		return null;
	}

}
