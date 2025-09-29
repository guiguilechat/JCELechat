package fr.guiguilechat.jcelechat.libs.sde.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.OrbitingCelestial;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.OrbitingCelestial.Attributes;
import lombok.Getter;

/**
 * represents an {@link OrbitingCelestial}
 */
@Getter
public abstract class AOrbitingCelestial<T extends OrbitingCelestial> extends AOrbiting<T> {

	private final Attributes attributes;

	protected AOrbitingCelestial(int id, T source) {
		super(id, source);
		attributes = source.attributes;
	}

}
