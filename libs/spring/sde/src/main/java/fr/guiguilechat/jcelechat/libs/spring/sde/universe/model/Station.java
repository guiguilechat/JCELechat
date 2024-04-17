package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.NPCStation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeUniverseStation")
@Table(name = "sde_universe_station", indexes = {
		@Index(columnList = "solar_system_solar_system_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Station {

	@Id
	private int stationId;
	@ManyToOne
	private SolarSystem solarSystem;
	@ManyToOne
	private Type type;

	private int graphicId;
	private boolean isConquerable;
	private String name;
	private int operationId;
	private int ownerId;
	private double position_x;
	private double position_y;
	private double position_z;
	private double reprocessingEfficiency;
	private int reprocessingHangarFlag;
	private double reprocessingStationsTake;
	private boolean useOperationName;

	public static Station from(
			int stationId,
			NPCStation station, String name, SolarSystem solarSystem, Type type) {
		return Station.builder()
				.stationId(stationId)
				.build()
				.update(station, name, solarSystem, type);
	}

	public Station update(NPCStation station, String name, SolarSystem solarSystem, Type type) {
		this.name = name;
		this.solarSystem = solarSystem;
		this.type = type;
		graphicId = station.graphicID;
		isConquerable = station.isConquerable;
		operationId = station.operationID;
		ownerId = station.ownerID;
		position_x = station.position.x();
		position_y = station.position.y();
		position_z = station.position.z();
		reprocessingEfficiency = station.reprocessingEfficiency;
		reprocessingHangarFlag = station.reprocessingHangarFlag;
		reprocessingStationsTake = station.reprocessingStationsTake;
		useOperationName = station.useOperationName;
		return this;
	}

}
