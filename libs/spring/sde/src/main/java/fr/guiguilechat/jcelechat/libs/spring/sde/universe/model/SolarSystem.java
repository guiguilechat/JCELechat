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
@Table(name = "sde_universe_solarsystem")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SolarSystem {

	@Id
	private int solarSystemId;

	private String name;

	@ManyToOne
	private Constellation constellation;

	public static SolarSystem from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem source,
			String name, Constellation constel) {
		SolarSystemBuilder builder = SolarSystem.builder();
		builder
				.constellation(constel)
				.name(name)
				.solarSystemId(source.solarSystemID);
		return builder.build();
	}

}
