package fr.guiguilechat.jcelechat.libs.spring.universe.star;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResource;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_universe_stars_star_id_spectral_class;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseStar")
@Table(name = "esi_universe_star", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "solar_system_id"),
    @Index(columnList = "type_id"),
    @Index(columnList = "name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Star extends ARemoteResource<Integer, R_get_universe_stars_star_id> {


	/**
	 * The solar system this stargate is in
	 */
	@OneToOne
	private SolarSystem solarSystem;

	/**
	 * type_id integer
	 */
	@ManyToOne
	private Type type;


	/**
	 * Age of star in years
	 */
	private long age;

	/**
	 * luminosity number
	 */
	private float luminosity;

	/**
	 * name string
	 */
	private String name;

	/**
	 * radius integer
	 */
	private long radius;

	/**
	 * spectral_class string
	 */
	@Enumerated(EnumType.STRING)
	private get_universe_stars_star_id_spectral_class spectralClass;

	/**
	 * temperature integer
	 */
	private int temperature;
	@Override
	public void update(R_get_universe_stars_star_id data) {
		setAge(data.age);
		setLuminosity(data.luminosity);
		setName(data.name);
		setRadius(data.radius);
		setSpectralClass(data.spectral_class);
		setTemperature(data.temperature);
	}

}
