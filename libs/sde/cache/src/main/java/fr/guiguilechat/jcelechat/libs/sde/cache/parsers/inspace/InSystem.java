package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;

/**
 * anything in a system has a type, a position, the system. positions are
 * relative to that system
 */
public abstract class InSystem extends InSpace {

	/** {@link EmapSolarSystems} */
	public int solarSystemID;

	/** {@link Etypes} */
	public int typeID;

}
