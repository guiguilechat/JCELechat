package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EplanetSchematics;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity(name = "SdePlanetarySchematic")
@Table(name = "sde_planetary_schematic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Schematic implements Serializable {

	@Id
	private int id;

	@ToString.Exclude
	@OneToMany(mappedBy = "schematic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SchemMaterial> materials;

	@ToString.Exclude
	@ManyToMany
	private Set<Type> pins;

	@ToString.Exclude
	@OneToMany(mappedBy = "schematic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SchemProduct> products;

	private int cycleTime;

	private String name;

	@Getter(lazy = true)
	@Transient
	private final int cpuLoad = pins.isEmpty() ? 0
	    : pins.iterator().next().getAttributes().stream()
					.filter(ta -> ta.getAttributeId() == 49)
	        .mapToInt(ta -> ta.getValue().intValue())
	        .findFirst().orElse(0);

	@Getter(lazy = true)
	@Transient
	private final int powerLoad = pins.isEmpty() ? 0
	    : pins.iterator().next().getAttributes().stream()
					.filter(ta -> ta.getAttributeId() == 15)
	        .mapToInt(ta -> ta.getValue().intValue())
					.findFirst().orElse(0);

	public static Schematic of(EplanetSchematics schem, int id) {
		SchematicBuilder builder = builder()
				.id(id)
				.cycleTime(schem.cycleTime)
				.name(schem.enName());
		return builder.build();
	}

}
