package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeUniverseRegion")
@Table(name = "sde_universe_region", indexes = {
		@Index(columnList = "universe"),
		@Index(columnList = "name")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Region {

	private String name;
	@Id
	private int regionId;
	private String universe;

	// copy from SDE structure
	private double center_x;
	private double center_y;
	private double center_z;
	private int descriptionId;
	private int factionId;
	private double max_x;
	private double max_y;
	private double max_z;
	private double min_x;
	private double min_y;
	private double min_z;
	private int nameId;
	private int nebula;
	private int wormholeClassId;

	public static Region from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region source, String RegionName,
			String uniName) {
		return Region.builder()
				.name(RegionName)
				.regionId(source.regionID)
				.universe(uniName)

				.center_x(source.center.x())
				.center_y(source.center.y())
				.center_z(source.center.z())
				.descriptionId(source.descriptionID)
				.factionId(source.factionID)
				.max_x(source.max.x())
				.max_y(source.max.y())
				.max_z(source.max.z())
				.min_x(source.min.x())
				.min_y(source.min.y())
				.min_z(source.min.z())
				.nameId(source.nameID)
				.nebula(source.nebula)
				.wormholeClassId(source.wormholeClassID)
			.build();
	}

}
