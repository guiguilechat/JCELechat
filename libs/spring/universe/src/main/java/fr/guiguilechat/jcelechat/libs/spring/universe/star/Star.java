package fr.guiguilechat.jcelechat.libs.spring.universe.star;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseStar")
@Table(name = "sde_universe_star", indexes = {
		@Index(columnList = "solar_system_id"),
		@Index(columnList = "type_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Star extends SdeEntity<Integer> {

	private BigDecimal age;
	private BigDecimal life;
	private BigDecimal luminosity;
	private long radius;
	@OneToOne
	private SolarSystem solarSystem;
	private String spectralClass;
	private BigDecimal temperature;
	@ManyToOne
	private Type type;

	public void update(EmapStars source,
			Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems) {
		super.receivedSource();
		setAge(source.statistics.age);
		setLife(source.statistics.life);
		setLuminosity(source.statistics.luminosity);
		setRadius(source.radius);
		setSolarSystem(solarSystems.apply(source.solarSystemID));
		setSpectralClass(source.statistics.spectralClass);
		setTemperature(source.statistics.temperature);
		setType(types.apply(source.typeID));
	}

}
