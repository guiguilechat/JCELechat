package fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseAsteroidBelt")
@Table(name = "esi_universe_asteroidbelt", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "solar_system_id,planet_id"),
    @Index(columnList = "name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AsteroidBelt extends ARemoteEntity<Integer, R_get_universe_asteroid_belts_asteroid_belt_id> {

	@ManyToOne
	private Planet planet;

	@ManyToOne
	private SolarSystem solarSystem;


	/**
	 * name string
	 */
	private String name;

	/**
	 * position object
	 */
	private double posX, posY, posZ;

	@Override
	public void update(R_get_universe_asteroid_belts_asteroid_belt_id data) {
		setName(data.name);
		setPosX(data.position.x);
		setPosY(data.position.y);
		setPosZ(data.position.z);
	}

}
