package fr.guiguilechat.jcelechat.libs.sde.model.locations.station;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EstationServices;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class StationService extends DataSourced<EstationServices> {

	public static final Mapper<EstationServices, StationService> CACHE = new Mapper<>(EstationServices.LOADER.yaml(),
			StationService::new);

	public StationService(DataSource datasource, int id, EstationServices source) {
		super(datasource, id, source);
	}

	protected StationService(int id, EstationServices source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final String description = source().enDescription();

	@Getter(lazy = true)
	private final String serviceName = source().enName();

}