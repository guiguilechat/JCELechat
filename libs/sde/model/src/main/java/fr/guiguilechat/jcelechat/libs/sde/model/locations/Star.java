package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars.StarStatistics;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.ASpace;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Star extends ASpace<EmapStars> {

	public static final Mapper<EmapStars, Star> CACHE = new Mapper<>(EmapStars.LOADER,
			Star::new);

	public long radius;

	public StarStatistics statistics;
	public int typeId;

	public Star(DataSource datasource, int id, EmapStars source) {
		super(datasource, id, source);
		radius = source.radius;
		statistics = source.statistics;
		typeId = source.typeID;
	}

	protected Star(int id, EmapStars source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final SolarSystem solarSystem = datasource().solarSystems().of(source().solarSystemID);

	@Override
	protected Position3D makePosition() {
		return solarSystem().position();
	}

	@Override
	protected String makeEnName() {
		return solarSystem().enName();
	}

}
