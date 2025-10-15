package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Orbiting.Statistic;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * represents an {@link Orbiting}
 */
@Getter
@Accessors(fluent = true)
public abstract class AOrbiting<T extends Orbiting> extends AInspace<T> {

	private final int celestialIndex;
	private final int orbitId;
	private final int orbitIndex;
	private final BigDecimal radius;
	private final Statistic statistics;
	private final int typeID;

	protected AOrbiting(SDEDataSource datasource, int id, T source) {
		super(datasource, id, source);
		celestialIndex = source.celestialIndex;
		orbitId = source.orbitID;
		orbitIndex = source.orbitIndex;
		radius = source.radius;
		statistics = source.statistics;
		typeID = source.typeID;
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = SolarSystem.CACHE.of(source().solarSystemID);

}
