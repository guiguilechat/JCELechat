package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe.dto;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;

public record ConstellationDTO(int constellationId, String name, int regionId, String universe) {

	public static ConstellationDTO of(Constellation constel) {
		Region region = constel.getRegion();
		String universe = region == null ? null : region.getUniverse();
		return new ConstellationDTO(
				constel.getId(),
				constel.getName(),
				region == null ? -1 : region.getId(),
				universe);
	}


}
