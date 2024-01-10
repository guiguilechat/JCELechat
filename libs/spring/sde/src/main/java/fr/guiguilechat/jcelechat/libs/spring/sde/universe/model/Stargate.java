package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeUniverseStargate")
@Table(name = "sde_universe_stargate", indexes = {
		@Index(columnList = "solar_system_solar_system_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Stargate {

	@Id
	private int stargateId;

	@OneToOne
	private Stargate destination;

	@ManyToOne
	private SolarSystem solarSystem;

	private double posX, posY, posZ;

	// TODO use types later
	private int typeId;

	public static Stargate from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate stargate,
			int stargateId, SolarSystem solarSystem) {
		StargateBuilder builder = Stargate.builder();
		builder
				.posX(stargate.position.x())
				.posY(stargate.position.y())
				.posZ(stargate.position.z())
				.solarSystem(solarSystem)
				.stargateId(stargateId)
				.typeId(stargate.typeID);
		return builder.build();
	}

}
