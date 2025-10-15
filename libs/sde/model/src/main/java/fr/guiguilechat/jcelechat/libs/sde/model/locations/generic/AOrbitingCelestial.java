package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.OrbitingCelestial;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.OrbitingCelestial.Attributes;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
import lombok.Getter;

/**
 * represents an {@link OrbitingCelestial}
 */
@Getter
public abstract class AOrbitingCelestial<T extends OrbitingCelestial> extends AOrbiting<T> {

	private final Attributes attributes;

	protected AOrbitingCelestial(SDEDataSource datasource, int id, T source) {
		super(datasource, id, source);
		attributes = source.attributes;
	}

}
