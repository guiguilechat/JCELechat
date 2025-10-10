package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapConstellations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AInspace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Constellation extends AInspace<EmapConstellations> {

	public static final NamingMapper<EmapConstellations, Constellation> CACHE = new NamingMapper<>(
			EmapConstellations.LOADER, Constellation::new, Constellation::enName);

	private final int factionId;
	private final int wormholeClassId;

	protected Constellation(int id, EmapConstellations source) {
		super(id, source);
		factionId = source.factionID;
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

	@Getter(lazy = true)
	private final Region region = Region.CACHE.of(source().regionID);

	@Getter(lazy = true)
	private final List<SolarSystem> solarSystems = SolarSystem.CACHE.of(source().solarSystemIDs);


}
