package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.star.Star;
import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseSolarSystem")
@Table(name = "esi_universe_solarsystem", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "constellation_id"),
    @Index(columnList = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolarSystem extends ARemoteFetchedResource<Integer, R_get_universe_systems_system_id> {

	@ManyToOne
	private Constellation constellation;

	/**
	 * planets array
	 */
	// private get_universe_systems_system_id_planets[] planets;

	@OneToOne(mappedBy = "solarSystem")
	private Star star;

	@OneToMany(mappedBy = "solarSystem")
	private List<Stargate> stargates;

	@OneToMany(mappedBy = "solarSystem")
	private List<Station> stations;


	/**
	 * name string
	 */
	private String name;

	/**
	 * position object
	 */
	private double posX, posY, posZ;

	/**
	 * security_class string
	 */
	private String securityClass;

	/**
	 * security_status number
	 */
	private float securityStatus;

	@Override
	public void update(R_get_universe_systems_system_id data) {
		setName(data.name);
		setPosX(data.position.x);
		setPosY(data.position.y);
		setPosZ(data.position.z);
		setSecurityClass(data.security_class);
		setSecurityStatus(data.security_status);
	}

}
