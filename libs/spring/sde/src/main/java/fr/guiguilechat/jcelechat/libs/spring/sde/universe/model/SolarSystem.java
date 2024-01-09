package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "sde_universe_solarsystem", indexes = {
		@Index(columnList = "constellation_constellation_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SolarSystem {

	@Id
	private int solarSystemId;

	private String name;

	private double security;

	@ManyToOne
	private Constellation constellation;

	public static SolarSystem from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem source,
			String name, Constellation constel) {
		SolarSystemBuilder builder = SolarSystem.builder();
		builder
				.constellation(constel)
				.name(name)
				.security(source.security)
				.solarSystemId(source.solarSystemID);
		return builder.build();
	}

}
