package fr.guiguilechat.jcelechat.libs.spring.universe.moon;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.generic.SdeInPlanetOrbit;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseMoon")
@Table(name = "sde_universe_moon", indexes = {
		@Index(columnList = "solar_system_id"),
		@Index(columnList = "type_id"),
		@Index(columnList = "planet_id"),
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Moon extends SdeInPlanetOrbit {

	@ManyToOne
	private Planet planet;
	private BigDecimal radius;

	public void update(EmapMoons source,
			Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems,
			Function<Integer, Planet> planets) {
		super.update(source, types, solarSystems);
		setPlanet(planets.apply(source.orbitID));
		setRadius(source.radius);
	}

}
