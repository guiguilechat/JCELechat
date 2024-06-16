package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.io.Serializable;
import java.util.List;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EplanetSchematics;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity(name = "SdePlanetarySchematic")
@Table(name = "sde_planetary_schematic")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Schematic implements Serializable {

	@Id
	private int id;

	@ToString.Exclude
	@OneToMany(mappedBy = "schematic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SchemMaterial> materials;

	@ToString.Exclude
	@OneToMany(mappedBy = "schematic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SchemProduct> products;

	private int cycleTime;

	private String name;

	private int cpuLoad;

	private int powerLoad;

	public static Schematic of(EplanetSchematics schem, int id) {
		SchematicBuilder builder = builder()
				.id(id)
				.cycleTime(schem.cycleTime)
				.name(schem.enName());
		return builder.build();
	}

}
