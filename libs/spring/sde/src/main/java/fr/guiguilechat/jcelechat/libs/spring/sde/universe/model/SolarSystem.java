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

@Entity(name = "SdeUniverseSolarSystem")
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

	private double security, radius;

	private double centerX, centerY, centerZ, minX, minY, minZ, maxX, maxY, maxZ;

	@ManyToOne
	private Constellation constellation;

	public static SolarSystem from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem source,
			String name, Constellation constel) {
		SolarSystemBuilder builder = SolarSystem.builder();
		builder
				.centerX(source.center.x())
				.centerY(source.center.y())
				.centerZ(source.center.z())
				.constellation(constel)
				.minX(source.min.x())
				.minY(source.min.y())
				.minZ(source.min.z())
				.maxX(source.max.x())
				.maxY(source.max.y())
				.maxZ(source.max.z())
				.name(name)
				.radius(source.radius)
				.security(source.security)
				.solarSystemId(source.solarSystemID);
		return builder.build();
	}

}
