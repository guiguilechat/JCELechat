package fr.guiguilechat.jcelechat.libs.spring.universe.planet;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt.AsteroidBelt;
import fr.guiguilechat.jcelechat.libs.spring.universe.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniversePlanet")
@Table(name = "esi_universe_planet", indexes = {
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
public class Planet extends ARemoteFetchedResource<Integer, R_get_universe_planets_planet_id> {

	@OneToMany(mappedBy = "planet")
	private List<AsteroidBelt> asteroidBelts;

	@OneToMany(mappedBy = "planet")
	private List<Moon> moons;

	/**
	 * The solar system this stargate is in
	 */
	@ManyToOne
	private SolarSystem solarSystem;

	/**
	 * type_id integer
	 */
	@ManyToOne
	private Type type;


	/**
	 * name string
	 */
	private String name;

	/**
	 * position object
	 */
	private double posX, posY, posZ;

	@Override
	public void update(R_get_universe_planets_planet_id data) {
		setName(data.name);
		setPosX(data.position.x);
		setPosY(data.position.y);
		setPosZ(data.position.z);
	}

}
