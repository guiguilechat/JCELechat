package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SolarSystem {

	@Id
	private int solarSystemID;

	private String name;

	@ManyToOne
	private Constellation constellation;

	public static SolarSystem from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem source,
			String name, Constellation constel) {
		SolarSystemBuilder builder = SolarSystem.builder();
		builder
				.constellation(constel)
				.name(name)
				.solarSystemID(source.solarSystemID);
		return builder.build();
	}

}