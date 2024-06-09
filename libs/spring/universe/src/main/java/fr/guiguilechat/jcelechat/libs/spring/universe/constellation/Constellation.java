package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseConstellation")
@Table(name = "esi_universe_constellation", indexes = { @Index(columnList = "region_id") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Constellation extends ARemoteFetchedResource<Integer, R_get_universe_constellations_constellation_id> {

	/**
	 * The region this constellation is in
	 */
	@ManyToOne
	private Region region;

	@OneToMany(mappedBy = "constellation")
	private List<SolarSystem> solarSystems;

	/**
	 * name string
	 */
	private String name;

	/**
	 * position object
	 */
	private double posX, posY, posZ;

	@Override
	public void update(R_get_universe_constellations_constellation_id data) {
		setName(data.name);
		setPosX(data.position.x);
		setPosY(data.position.y);
		setPosZ(data.position.z);
	}

}
