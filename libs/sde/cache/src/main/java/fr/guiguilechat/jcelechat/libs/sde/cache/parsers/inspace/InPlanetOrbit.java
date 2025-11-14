package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

/**
 * basically moons, but also asteroidbelts and stations (stations can be on moon
 * orbit too, if the orbitIndex >0 ; or on star is orbitindex<1)
 */
public abstract class InPlanetOrbit extends InStarOrbit {

	public int orbitIndex;

}
