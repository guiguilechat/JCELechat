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

@Entity(name = "SdeUniverseConstellation")
@Table(name = "sde_universe_constellation", indexes = {
		@Index(columnList = "region_region_id"),
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Constellation {

	@Id
	private int constellationId;
	private String name;
	@ManyToOne
	private Region region;

	// copy from SDE structure
	private double center_x;
	private double center_y;
	private double center_z;
	private int factionId;
	private double max_x;
	private double max_y;
	private double max_z;
	private double min_x;
	private double min_y;
	private double min_z;
	private int nameId;
	private double radius;
	private int wormholeClassId;


	public static Constellation from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation source,
			String name, Region region) {
		return Constellation.builder()
				.constellationId(source.constellationID)
				.name(name)
				.region(region)

				.center_x(source.center.x())
				.center_y(source.center.y())
				.center_z(source.center.z())
				.factionId(source.factionID)
				.max_x(source.max.x())
				.max_y(source.max.y())
				.max_z(source.max.z())
				.min_x(source.min.x())
				.min_y(source.min.y())
				.min_z(source.min.z())
				.nameId(source.nameID)
				.radius(source.radius)
				.wormholeClassId(source.wormholeClassID)
			.build();
	}

}
