package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Constellation;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Region;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * represent one or more solar systems. Can be a solar system, a constellation,
 * a region. This allows to store the adjacent systems, constellations, regions
 * easily
 */
@Accessors(fluent = true)
public abstract class SolarSystemGroup<T> extends ASpace<T> {

	protected SolarSystemGroup(DataSource datasource, int id, T source) {
		super(datasource, id, source);
	}

	public abstract Set<SolarSystem> solarSystems();

	public abstract Region region();

	public abstract Set<Constellation> constellations();

	protected abstract Stream<SolarSystem> streamAdjacentSolarSystems();

	/**
	 * solar systems adjacent to those of that group, not in that group
	 */
	@Getter(lazy = true)
	private final Set<SolarSystem> adjacentSolarSystems = streamAdjacentSolarSystems()
			.filter(s -> !solarSystems().contains(s))
			.collect(Collectors.toSet());

	protected Stream<Constellation> streamAdjacentConstellations() {
		return adjacentSolarSystems().stream()
				.map(SolarSystem::constellation);
	}

	/**
	 * constellations adjacent to that group, excluding constellations of that
	 * group.
	 */
	@Getter(lazy = true)
	private final Set<Constellation> adjacentConstellations = streamAdjacentConstellations()
			.filter(c -> !constellations().contains(c))
			.collect(Collectors.toSet());

	protected Stream<Region> streamAdjacentRegions() {
		return adjacentSolarSystems().stream()
				.map(SolarSystem::region);
	}

	@Getter(lazy = true)
	private final Set<Region> adjacentRegions = streamAdjacentRegions()
			.filter(r -> !r.equals(region()))
			.collect(Collectors.toSet());

	protected record MinMax(Position min, Position max) {
	}

	protected MinMax extractStats() {
		// x y z positions
		Position min = new Position(
				BigDecimal.valueOf(Double.MAX_VALUE),
				BigDecimal.valueOf(Double.MAX_VALUE),
				BigDecimal.valueOf(Double.MAX_VALUE));
		Position max = new Position(
				BigDecimal.valueOf(-0.99 * Double.MAX_VALUE),
				BigDecimal.valueOf(-0.99 * Double.MAX_VALUE),
				BigDecimal.valueOf(-0.99 * Double.MAX_VALUE));
		solarSystems().stream().flatMap(SolarSystem::streamInSpaces)
				.forEach(is -> {
//					System.out.println("comparing positions with " + is.enName() + " : " + is.position());
					Position p = is.position();
					if (p.x.compareTo(min.x) < 0) {
						min.x=p.x;
					}
					if (p.y.compareTo(min.y) < 0) {
						min.y = p.y;
					}
					if (p.z.compareTo(min.z) < 0) {
						min.z = p.z;
					}
					if (p.x.compareTo(max.x) > 0) {
						max.x = p.x;
					}
					if (p.y.compareTo(max.y) > 0) {
						max.y = p.y;
					}
					if (p.z.compareTo(max.z) > 0) {
						max.z = p.z;
					}
				});
		return new MinMax(min, max);
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final MinMax stats = extractStats();

	public Position min() {
		return stats().min;
	}

	public Position max() {
		return stats().max;
	}

}
