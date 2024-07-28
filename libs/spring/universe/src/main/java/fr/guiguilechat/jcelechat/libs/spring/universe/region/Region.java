package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseRegion")
@Table(name = "esi_universe_region", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "universe"),
    @Index(columnList = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Region extends ARemoteEntity<Integer, R_get_universe_regions_region_id> {

	@OneToMany(mappedBy = "region")
	private List<Constellation> constellations;

	/**
	 * description string
	 */
	@Column(columnDefinition = "TEXT")
	private String description;

	/**
	 * name string
	 */
	private String name;

	private String universe;

	@Override
	public void update(R_get_universe_regions_region_id data) {
		setDescription(data.description);
		setName(data.name);
	}

}
