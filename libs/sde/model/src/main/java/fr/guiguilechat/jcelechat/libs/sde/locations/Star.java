package fr.guiguilechat.jcelechat.libs.sde.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars.StarStatistics;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Star {

	public static final Mapper<EmapStars, Star> CACHE = new Mapper<>(EmapStars.LOADER,
			Star::new);

	private final int id;

	private final EmapStars source;

	public long radius;

	public StarStatistics statistics;
	public int typeId;

	public Star(int id, EmapStars source) {
		this.id = id;
		this.source = source;
		radius = source.radius;
		statistics = source.statistics;
		typeId = source.typeID;
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = SolarSystem.CACHE.of(source().solarSystemID);

}
