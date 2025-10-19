package fr.guiguilechat.jcelechat.libs.spring.sde.universe.planet;

import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.generic.SdeInStarOrbit;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniversePlanet")
@Table(name = "sde_universe_planet", indexes = {
		@Index(columnList = "solar_system_id"),
		@Index(columnList = "type_id"),
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Planet extends SdeInStarOrbit {

	private long radius;
	@OneToMany(mappedBy = "planet")
	private Set<Moon> moons;

	public void update(EmapPlanets source, Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems) {
		super.update(source, types, solarSystems);
		setRadius(source.radius);
	}

}
