package fr.guiguilechat.jcelechat.libs.sde.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Universe;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.locations.generic.ALocation;
import lombok.Getter;

@Getter
public class Region extends ALocation<EmapRegions> {

	public static final Mapper<EmapRegions, Region> CACHE = new Mapper<>(EmapRegions.LOADER, Region::new);

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

	public String enDescription() {
		return source().enDescription();
	}

	@Getter(lazy = true)
	private final List<Constellation> constellations = Constellation.CACHE.of(source().constellationIDs);

	@Getter(lazy = true)
	private final Universe universe = Universe.of(id());

}
