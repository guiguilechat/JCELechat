package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
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
	@ManyToOne
	private Type type;

	private double position_x;
	private double position_y;
	private double position_z;

	public static Stargate from(int stargateId,
			fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate stargate,
			SolarSystem solarSystem, Type type) {
		return Stargate.builder()
				.stargateId(stargateId)
				.build()
				.update(stargate, solarSystem, type);
	}

	public Stargate update(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate stargate,
			SolarSystem solarSystem, Type type) {
		this.solarSystem = solarSystem;
		this.type = type;

		position_x = stargate.position.x();
		position_y = stargate.position.y();
		position_z = stargate.position.z();
		return this;
	}

}
