package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseConstellation")
@Table(name = "sde_universe_constellation", indexes = {
//    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "region_id"),
    @Index(columnList = "name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Constellation extends SdeEntity<Integer> {

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
	private BigDecimal posX, posY, posZ;

	public String name() {
		if (name != null) {
			return name;
		}
		return "constel:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "constel:" + getId() : name + "(" + getId() + ")";
	}

}
