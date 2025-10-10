package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars.StarStatistics;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AInspace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Star extends AInspace<EmapStars> {

	public static final Mapper<EmapStars, Star> CACHE = new Mapper<>(EmapStars.LOADER,
			Star::new);

	public long radius;

	public StarStatistics statistics;
	public int typeId;

	public Star(int id, EmapStars source) {
		super(id, source);
		radius = source.radius;
		statistics = source.statistics;
		typeId = source.typeID;
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = SolarSystem.CACHE.of(source().solarSystemID);

	@Override
	protected Position makePosition() {
		return solarSystem().position();
	}

	@Override
	protected String makeEnName() {
		return solarSystem().enName();
	}

}
