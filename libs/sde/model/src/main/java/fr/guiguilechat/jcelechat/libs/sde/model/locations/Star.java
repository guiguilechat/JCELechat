package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars.StarStatistics;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AIDBasedObject;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Star extends AIDBasedObject {

	public static final Mapper<EmapStars, Star> CACHE = new Mapper<>(EmapStars.LOADER,
			Star::new);

	private final EmapStars source;

	public long radius;

	public StarStatistics statistics;
	public int typeId;

	public Star(int id, EmapStars source) {
		super(id);
		this.source = source;
		radius = source.radius;
		statistics = source.statistics;
		typeId = source.typeID;
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = SolarSystem.CACHE.of(source().solarSystemID);

}
