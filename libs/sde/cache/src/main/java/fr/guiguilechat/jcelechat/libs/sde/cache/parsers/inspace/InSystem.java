package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

/**
 * anything in a system has a type, a position, the system. positions are
 * relative to that system
 */
public abstract class InSystem extends InSpace {

	public int solarSystemID;

	public int typeID;

}
