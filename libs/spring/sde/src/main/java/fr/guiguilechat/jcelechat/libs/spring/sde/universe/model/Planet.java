package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeUniversePlanet")
@Table(name = "sde_universe_planet", indexes = {
		@Index(columnList = "solar_system_solar_system_id"),
		@Index(columnList = "type_type_id"),
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Planet {

	@Id
	private Long planetId;

	@ManyToOne
	private SolarSystem solarSystem;

	@ManyToOne
	private Type type;

	private String name;

	private double posX, posY, posZ;

	private double radius;

	public static Planet from(
			long planetId,
			fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Planet planet,
			String planetName,
			SolarSystem solarSystem,
			Type type) {
		return builder()
				.planetId(planetId)
				.build()
				.update(planet, planetName, solarSystem, type);
	}

	public Planet update(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Planet planet,
			String planetName,
			SolarSystem solarSystem,
			Type type) {
		this.solarSystem = solarSystem;
		this.type = type;
		name = planetName;
		posX = planet.position.x();
		posY = planet.position.y();
		posZ = planet.position.z();
		radius = planet.radius;
		return this;
	}

}
