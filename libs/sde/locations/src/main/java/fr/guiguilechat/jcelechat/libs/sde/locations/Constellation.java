package fr.guiguilechat.jcelechat.libs.sde.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapConstellations;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import lombok.Getter;

@Getter
public class Constellation extends ALocation<EmapConstellations> {

	public static final Mapper<EmapConstellations, Constellation> CACHE = new Mapper<>(EmapConstellations.LOADER,
			Constellation::new);


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

	@Getter(lazy = true)
	private final Region region = Region.CACHE.of(source().regionID);

}
