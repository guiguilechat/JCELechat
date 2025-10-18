package fr.guiguilechat.jcelechat.libs.spring.universe.generic;

import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InStarOrbit;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SdeInStarOrbit extends SdeInSystem {

	private int celestialIndex;
	private int orbitId;

	protected void update(InStarOrbit source, Function<Integer, Type> types, Function<Integer, SolarSystem> solarSystems) {
		super.update(source, types, solarSystems);
		setCelestialIndex(source.celestialIndex);
		setOrbitId(source.orbitID);
	}

}
