package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapConstellations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.SolarSystemGroup;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Constellation extends SolarSystemGroup<EmapConstellations> {

	public static final NamingMapper<EmapConstellations, Constellation> CACHE = new NamingMapper<>(
			EmapConstellations.LOADER.yaml(), Constellation::new, Constellation::enName);

	private final int factionId;
	private final int wormholeClassId;

	protected Constellation(DataSource datasource, int id, EmapConstellations source) {
		super(datasource, id, source);
		factionId = source.factionID;
		wormholeClassId = source.wormholeClassID;
	}

	protected Constellation(int id, EmapConstellations source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return source().enName();
	}

	@Override
	protected Position3D makePosition() {
		return source().position;
	}

	@Getter(lazy = true)
	private final Region region = datasource().regions().of(source().regionID);

	@Getter(lazy = true)
	private final Set<Constellation> constellations = Set.of(this);

	@Getter(lazy = true)
	private final Set<SolarSystem> solarSystems = new LinkedHashSet<>(
			datasource().solarSystems().of(source().solarSystemIDs));

	@Override
	protected Stream<SolarSystem> streamAdjacentSolarSystems() {
		return solarSystems().stream()
				.flatMap(SolarSystem::streamAdjacentSolarSystems)
				.filter(ss -> !solarSystems().contains(ss));
	}


}
