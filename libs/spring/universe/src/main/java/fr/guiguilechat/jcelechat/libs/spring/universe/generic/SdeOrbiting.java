package fr.guiguilechat.jcelechat.libs.spring.universe.generic;

import java.math.BigDecimal;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SdeOrbiting extends SdeInSpace {

	private int celestialIndex;
	private int orbitId;
	private int orbitIndex;
	private BigDecimal radius;

	@ManyToOne
	private SolarSystem solarSystem;

	@ManyToOne
	private Type type;

	protected void update(Orbiting source, Function<Integer, Type> types, Function<Integer, SolarSystem> solarSystems) {
		super.update(source);
		setCelestialIndex(source.celestialIndex);
		setOrbitId(source.orbitID);
		setOrbitIndex(source.orbitIndex);
		setRadius(source.radius);
		setSolarSystem(solarSystems.apply(source.solarSystemID));
		setType(types.apply(source.typeID));

	}

}
