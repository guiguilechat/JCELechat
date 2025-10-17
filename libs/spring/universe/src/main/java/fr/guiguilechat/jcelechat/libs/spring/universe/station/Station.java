package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.generic.SdeOrbiting;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseStation")
@Table(name = "sde_universe_station", indexes = {
    @Index(columnList = "solar_system_id"),
    @Index(columnList = "type_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Station extends SdeOrbiting {

	private int operationId;
	private int ownerId;
	private BigDecimal reprocessingEfficiency;
	private int reprocessingHangarFlag;
	private BigDecimal reprocessingStationsTake;
	private boolean useOperationName;

	public void update(EnpcStations source, Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems) {
		super.update(source, types, solarSystems);
		setOperationId(source.operationID);
		setOwnerId(source.ownerID);
		setReprocessingEfficiency(source.reprocessingEfficiency);
		setReprocessingHangarFlag(source.reprocessingHangarFlag);
		setReprocessingStationsTake(source.reprocessingStationsTake);
		setUseOperationName(source.useOperationName);
	}

	public String getName() {
		return "station:" + getId();
	}

}
