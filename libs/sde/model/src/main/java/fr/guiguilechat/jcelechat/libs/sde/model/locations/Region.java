package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Universe;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.SolarSystemGroup;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Region extends SolarSystemGroup<EmapRegions> {

	public static final NamingMapper<EmapRegions, Region> CACHE = new NamingMapper<>(
			EmapRegions.LOADER, Region::new, Region::enName);

	private final int factionId, nebulaId, wormholeClassId;

	public Region(int id, EmapRegions source) {
		super(id, source);
		factionId = source.factionID;
		nebulaId = source.nebulaID;
		wormholeClassId = source.wormholeClassID;
	}

	@Override
	protected String makeEnName() {
		return source().enName();
	}

	@Override
	protected Position makePosition() {
		return source().position;
	}

	public String enDescription() {
		return source().enDescription();
	}

	@Getter(lazy = true)
	private final Set<Constellation> constellations = new HashSet<>(Constellation.CACHE.of(source().constellationIDs));

	@Getter(lazy = true)
	private final Universe universe = Universe.of(id());

	@Getter(lazy = true)
	private final Set<SolarSystem> solarSystems = constellations().stream()
			.flatMap(c -> c.solarSystems().stream())
			.collect(Collectors.toSet());

	@Override
	protected Stream<SolarSystem> streamAdjacentSolarSystems() {
		return solarSystems().stream()
				.flatMap(SolarSystem::streamAdjacentSolarSystems)
				.filter(ss -> !solarSystems().contains(ss));
	}

}
