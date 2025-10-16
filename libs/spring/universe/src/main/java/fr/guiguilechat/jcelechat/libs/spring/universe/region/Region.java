package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.model.formula.space.Universe;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseRegion")
@Table(name = "sde_universe_region", indexes = {
    @Index(columnList = "universe"),
    @Index(columnList = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Region {

	@Id
	private int id;
	@Builder.Default
	private boolean received = false;
	@Builder.Default
	private boolean removed = false;
	@CreatedDate
	private Instant created;

	@OneToMany(mappedBy = "region")
	private List<Constellation> constellations;

	@Lob
	private String description;

	private String name;

	private String universe;

	public String name() {
		if(name!=null) {
			return name;
		}
		return "region:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "region:" + getId() : name + "(" + getId() + ")";
	}

	public void update(EmapRegions value) {
		received = true;
		removed = false;
		description = value.enDescription();
		name = value.enName();
		universe = Universe.of(getId()).name();
	}

}
