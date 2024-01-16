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

	@OneToOne
	private Stargate destination;
	@ManyToOne
	private SolarSystem solarSystem;
	@Id
	private int stargateId;

	private double position_x;
	private double position_y;
	private double position_z;
	// TODO use types later
	private int typeId;

	public static Stargate from(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate stargate,
			int stargateId, SolarSystem solarSystem) {
		return Stargate.builder()
				.solarSystem(solarSystem)
				.position_x(stargate.position.x())
				.position_y(stargate.position.y())
				.position_z(stargate.position.z())
				.stargateId(stargateId)
				.typeId(stargate.typeID)
		.build();
	}

}
