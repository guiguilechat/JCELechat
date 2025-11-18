package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapAsteroidBelts;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AStarOrbit;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class AsteroidBelt extends AStarOrbit<EmapAsteroidBelts> {

	public static final Mapper<EmapAsteroidBelts, AsteroidBelt> CACHE = new Mapper<>(EmapAsteroidBelts.LOADER.yaml(),
			AsteroidBelt::new);

	protected AsteroidBelt(DataSource datasource, int id, EmapAsteroidBelts source) {
		super(datasource, id, source);
	}

	protected AsteroidBelt(int id, EmapAsteroidBelts source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Override
	protected Position3D makePosition() {
		return source().position.add(solarSystem().position());
	}

}
