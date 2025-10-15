package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.Collection;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AOrbitingCelestial;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Moon extends AOrbitingCelestial<EmapMoons> {

	public static final Mapper<EmapMoons, Moon> CACHE = new Mapper<>(EmapMoons.LOADER,
			Moon::new);

	protected Moon(DataSource datasource, int id, EmapMoons source) {
		super(datasource, id, source);
	}

	protected Moon(int id, EmapMoons source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Override
	protected Position makePosition() {
		return source().position.add(solarSystem().position());
	}

	@Getter(lazy = true)
	private final Collection<Station> stations = datasource().stations().of(source().npcStationIDs);

}
