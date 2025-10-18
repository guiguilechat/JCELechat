package fr.guiguilechat.jcelechat.libs.spring.universe.generic;

import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSystem;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SdeInSystem extends SdeInSpace {

	@ManyToOne
	private SolarSystem solarSystem;

	@ManyToOne
	private Type type;

	protected void update(InSystem source, Function<Integer, Type> types, Function<Integer, SolarSystem> solarSystems) {
		super.update(source);
		setSolarSystem(solarSystems.apply(source.solarSystemID));
		setType(types.apply(source.typeID));
	}

}
