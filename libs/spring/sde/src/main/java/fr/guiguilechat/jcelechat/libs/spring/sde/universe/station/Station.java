package fr.guiguilechat.jcelechat.libs.spring.sde.universe.station;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.generic.SdeInPlanetOrbit;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.station.operation.StationOperation;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseStation")
@Table(name = "sde_universe_station", indexes = {
		@Index(columnList = "solar_system_id"),
		@Index(columnList = "type_id"),
		@Index(columnList = "operation_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Station extends SdeInPlanetOrbit {

	@ManyToOne
	private StationOperation operation;
	@ManyToOne
	private NpcCorporation owner;
	@ManyToOne
	private Planet planet;
	private BigDecimal reprocessingEfficiency;
	private int reprocessingHangarFlag;
	private BigDecimal reprocessingStationsTake;
	private boolean useOperationName;

	public void update(EnpcStations source,
			Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems,
			Function<Integer, StationOperation> operations) {
		super.update(source, types, solarSystems);
		setOperation(operations.apply(source.operationID));
//		setOwnerId(source.ownerID);
		setReprocessingEfficiency(source.reprocessingEfficiency);
		setReprocessingHangarFlag(source.reprocessingHangarFlag);
		setReprocessingStationsTake(source.reprocessingStationsTake);
		setUseOperationName(source.useOperationName);
	}

	public String getName() {
		return "station:" + getId();
	}

}
