package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars.StarStatistics;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.LocalCacheDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
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

	public Star(SDEDataSource datasource, int id, EmapStars source) {
		super(datasource, id, source);
		radius = source.radius;
		statistics = source.statistics;
		typeId = source.typeID;
	}

	protected Star(int id, EmapStars source) {
		this(LocalCacheDataSource.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = datasource().solarSystems().of(source().solarSystemID);

	@Override
	protected Position makePosition() {
		return solarSystem().position();
	}

	@Override
	protected String makeEnName() {
		return solarSystem().enName();
	}

}
