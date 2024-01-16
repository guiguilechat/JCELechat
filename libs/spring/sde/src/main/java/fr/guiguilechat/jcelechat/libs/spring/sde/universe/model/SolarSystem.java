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
		@Index(columnList = "constellation_constellation_id"),
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SolarSystem {

	@ManyToOne
	private Constellation constellation;
	private String name;
	@Id
	private int solarSystemId;

	// copy from SDE structure
	private boolean border;
	private double center_x;
	private double center_y;
	private double center_z;
	private boolean corridor;
	private int descriptionId;
	private int factionId;
	private boolean fringe;
	private boolean hub;
	private boolean international;
	private double max_x;
	private double max_y;
	private double max_z;
	private double min_x;
	private double min_y;
	private double min_z;
	private double luminosity;
	private double radius;
	private boolean regional;
	private double security;
	private String securityClass;
	private int solarSystemNameId;
	private int sunTypeID;
	private String visualEffect;
	private int wormholeClassId;


	public static SolarSystem from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem source,
			String name, Constellation constel) {
		return SolarSystem.builder()
				.constellation(constel)
				.name(name)
				.solarSystemId(source.solarSystemID)

				.border(source.border)
				.center_x(source.center.x())
				.center_y(source.center.y())
				.center_z(source.center.z())
				.corridor(source.corridor)
				.descriptionId(source.descriptionID)
				.factionId(source.factionID)
				.fringe(source.fringe)
				.hub(source.hub)
				.international(source.international)
				.max_x(source.max.x())
				.max_y(source.max.y())
				.max_z(source.max.z())
				.min_x(source.min.x())
				.min_y(source.min.y())
				.min_z(source.min.z())
				.luminosity(source.luminosity)
				.radius(source.radius)
				.regional(source.regional)
				.security(source.security)
				.securityClass(source.securityClass)
				.solarSystemNameId(source.solarSystemNameID)
				.sunTypeID(source.sunTypeID)
				.visualEffect(source.visualEffect)
				.wormholeClassId(source.wormholeClassID)
		.build();
	}

}
