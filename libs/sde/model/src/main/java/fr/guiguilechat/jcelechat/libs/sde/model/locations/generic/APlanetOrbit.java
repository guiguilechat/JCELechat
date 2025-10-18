package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InPlanetOrbit;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import lombok.Getter;

/**
 * represents an {@link InPlanetOrbit}
 */
@Getter
public abstract class APlanetOrbit<T extends InPlanetOrbit> extends AStarOrbit<T> {

	private int orbitIndex;

	protected APlanetOrbit(DataSource datasource, int id, T source) {
		super(datasource, id, source);
		orbitIndex = source.orbitIndex;
	}

}
