package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;

public record RegionDTO(int regionId, String name, String universe) {

	public static RegionDTO of(Region region) {
		return new RegionDTO(
				region.getId(),
				region.getName(),
				region.getUniverse());
	}

}
