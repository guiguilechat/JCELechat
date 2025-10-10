package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AOrbiting;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Station extends AOrbiting<EnpcStations> {

	public static final NamingMapper<EnpcStations, Station> CACHE = new NamingMapper<>(
			EnpcStations.LOADER, Station::new, Station::enName);

	private final int operationId;
	private final int ownerId;
	private final BigDecimal reprocessingEfficiency;
	private final int reprocessingHangarFlag;
	private final BigDecimal reprocessingStationsTake;
	private final boolean useOperationName;

	protected Station(int id, EnpcStations source) {
		super(id, source);
		operationId = source.operationID;
		ownerId = source.ownerID;
		reprocessingEfficiency = source.reprocessingEfficiency;
		reprocessingHangarFlag = source.reprocessingHangarFlag;
		reprocessingStationsTake = source.reprocessingStationsTake;
		useOperationName = source.useOperationName;
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Override
	protected Position makePosition() {
		return source().position;
	}

}
