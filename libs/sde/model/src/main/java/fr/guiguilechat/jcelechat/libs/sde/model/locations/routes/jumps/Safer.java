package fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.jumps;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.Dijkstra;

public class Safer extends Dijkstra<SolarSystem> {

	public Safer(SolarSystem start) {
		super(start);
	}

	@Override
	protected Iterable<SolarSystem> reachable(SolarSystem from) {
		return from.stargates().stream().map(sg -> sg.destination().solarSystem()).toList();
	}

	@Override
	protected double distance(SolarSystem start, SolarSystem end) {
		if (end.securityStatus().doubleValue() > 0.45) {
			return 1.0;
		}
		if (end.securityStatus().doubleValue() > 0.0) {
			return 1000.0;
		}
		return 1000000.0;
	}

	public List<ItemDist<SolarSystem>> to(String name) {
		return to(SolarSystem.CACHE.of(name));
	}

	public List<ItemDist<SolarSystem>> to(int id) {
		return to(SolarSystem.CACHE.of(id));
	}

}
