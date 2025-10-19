package fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapConstellations;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.generic.SdeInSpace;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeSpaceConstellation")
@Table(name = "sde_space_constellation", indexes = {
    @Index(columnList = "region_id"),
    @Index(columnList = "name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Constellation extends SdeInSpace {

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

	public void update(EmapConstellations source, Function<Integer, Region> regions) {
		super.update(source);
		setName(source.enName());
		setRegion(regions.apply(source.regionID));
	}

}
