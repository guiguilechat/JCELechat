package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InStarOrbit;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * represents an {@link InStarOrbit}
 */
@Getter
@Accessors(fluent = true)
public abstract class AStarOrbit<T extends InStarOrbit> extends ASpace<T> {

	private final int celestialIndex;
	private final int orbitId;
//	private final int typeID;

	protected AStarOrbit(DataSource datasource, int id, T source) {
		super(datasource, id, source);
		celestialIndex = source.celestialIndex;
		orbitId = source.orbitID;
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = SolarSystem.CACHE.of(source().solarSystemID);

}
