package fr.guiguilechat.jcelechat.model.sde.locations.route;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public interface IRouter {

	public int[] getRoute(int idFrom, int idTo);

	public default Route getRoute(SolarSystem from, SolarSystem to) {
		return new Route(
				IntStream.of(getRoute(from.id, to.id)).mapToObj(SolarSystem::getSystem).collect(Collectors.toList()));
	}

}
