package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Region {

	@Id
	private int regionID;

	private String name;

	private String universe;

	private int factionID;

	public static Region from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region source, String RegionName,
			String uniName) {
		RegionBuilder builder = Region.builder();
		builder
				.factionID(source.factionID)
				.name(RegionName)
				.regionID(source.regionID)
				.universe(uniName)
		;
		return builder.build();
	}

}
