package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Universe;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AInspace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Region extends AInspace<EmapRegions> {

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

	public String enDescription() {
		return source().enDescription();
	}

	@Getter(lazy = true)
	private final List<Constellation> constellations = Constellation.CACHE.of(source().constellationIDs);

	@Getter(lazy = true)
	private final Universe universe = Universe.of(id());

}
