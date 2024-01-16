package fr.guiguilechat.jcelechat.libs.spring.sde.universe.model;

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

	@ManyToOne
	private SolarSystem solarSystem;
	@Id
	private int stationId;

	private int graphicId;
	private boolean isConquerable;
	private int operationId;
	private int ownerId;
	private double position_x;
	private double position_y;
	private double position_z;
	private double reprocessingEfficiency;
	private int reprocessingHangarFlag;
	private double reprocessingStationsTake;
	private int typeId;
	private boolean useOperationName;

	public static Station from(NPCStation station,
			int stationId, SolarSystem solarSystem) {
		return Station.builder()
				.solarSystem(solarSystem)
				.stationId(stationId)

				.graphicId(station.graphicID)
				.isConquerable(station.isConquerable)
				.operationId(station.operationID)
				.ownerId(station.ownerID)
				.position_x(station.position.x())
				.position_y(station.position.y())
				.position_z(station.position.z())
				.reprocessingEfficiency(station.reprocessingEfficiency)
				.reprocessingHangarFlag(station.reprocessingHangarFlag)
				.reprocessingStationsTake(station.reprocessingStationsTake)
				.typeId(station.typeID)
				.useOperationName(station.useOperationName)
			.build();
	}

}
