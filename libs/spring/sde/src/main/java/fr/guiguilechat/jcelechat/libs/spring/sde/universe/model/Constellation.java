package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "sde_universe_constellation")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Constellation {

	@Id
	private int constellationId;

	private String name;

	private int factionID;

	@ManyToOne
	private Region region;

	public static Constellation from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation source,
			String name, Region region) {
		ConstellationBuilder builder = Constellation.builder();
		builder
				.constellationId(source.constellationID)
				.factionID(source.factionID)
				.name(name)
				.region(region)
		;
		return builder.build();
	}

}
