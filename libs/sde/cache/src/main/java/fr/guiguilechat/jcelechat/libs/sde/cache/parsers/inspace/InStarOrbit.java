package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

/**
 * common class for planets, moons, asteroid belts, stations.
 */
public abstract class InStarOrbit extends InSystem {

	/**
	 * the index of the orbit around the star this belongs to
	 */
	public int celestialIndex;

	/**
	 * reference to the type this orbits. Star for the planets, Planet for the moon
	 * and asteroid belts ; planet or moon for the station, depending on
	 * orbitIndex>0 (moon) or not (planet
	 */
	public int orbitID;
}
