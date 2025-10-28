package fr.guiguilechat.jcelechat.libs.spring.sde.space.stargate;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseStargate")
@Table(name = "esi_universe_stargate", indexes = {
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
public class Stargate extends RemoteEntity<Integer, R_get_universe_stargates_stargate_id> {


	/**
	 * destination object
	 */
	@ManyToOne
	private Stargate destination;

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
	public void update(R_get_universe_stargates_stargate_id data) {
		setName(data.name);
		setPosX(data.position.x);
		setPosY(data.position.y);
		setPosZ(data.position.z);
	}

}
