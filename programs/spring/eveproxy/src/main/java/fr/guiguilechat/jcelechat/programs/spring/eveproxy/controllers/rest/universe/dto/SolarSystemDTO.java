package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe.dto;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;

public record SolarSystemDTO(int solarSystemId, String name,
		BigDecimal securityStatus,
		int constellationId,
		int regionId,
		String universe) {

	public static SolarSystemDTO of(SolarSystem ss) {
		Constellation constel = ss.getConstellation();
		Region region = constel == null ? null : constel.getRegion();
		String universe = region == null ? null : region.getUniverse();
		return new SolarSystemDTO(ss.getId(),
				ss.getName(),
				ss.getSecurityStatus(),
				constel == null || constel.getId() == null ? -1 : constel.getId(),
				region == null ? -1 : region.getId(),
				universe);
	}
}