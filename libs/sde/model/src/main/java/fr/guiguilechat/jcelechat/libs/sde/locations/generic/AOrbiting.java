package fr.guiguilechat.jcelechat.libs.sde.locations.generic;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Orbiting.Statistic;
import lombok.Getter;

/**
 * represents an {@link Orbiting}
 */
@Getter
public abstract class AOrbiting<T extends Orbiting> extends ALocation<T> {

	private final int celestialIndex;
	private final int orbitId;
	private final int orbitIndex;
	private final BigDecimal radius;
	private final Statistic statistics;
	private final int typeID;

	protected AOrbiting(int id, T source) {
		super(id, source);
		celestialIndex = source.celestialIndex;
		orbitId = source.orbitID;
		orbitIndex = source.orbitIndex;
		radius = source.radius;
		statistics = source.statistics;
		typeID = source.typeID;
	}

}
