package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AStarOrbit;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.station.StationOperation;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.NPCCorporation;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Station extends AStarOrbit<EnpcStations> {

	public static final NamingMapper<EnpcStations, Station> CACHE = new NamingMapper<>(
			EnpcStations.LOADER.yaml(), Station::new, Station::enName);

	private final BigDecimal reprocessingEfficiency;
	private final int reprocessingHangarFlag;
	private final BigDecimal reprocessingStationsTake;
	private final boolean useOperationName;

	protected Station(DataSource datasource, int id, EnpcStations source) {
		super(datasource, id, source);
		reprocessingEfficiency = source.reprocessingEfficiency;
		reprocessingHangarFlag = source.reprocessingHangarFlag;
		reprocessingStationsTake = source.reprocessingStationsTake;
		useOperationName = source.useOperationName;
	}

	protected Station(int id, EnpcStations source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Override
	protected Position3D makePosition() {
		return source().position.add(solarSystem().position());
	}

	@Getter(lazy = true)
	private final StationOperation operation = datasource().stationOperations().of(source().operationID);

	@Getter(lazy = true)
	private final NPCCorporation owner = datasource().npcCorporations().of(source().ownerID);

}
