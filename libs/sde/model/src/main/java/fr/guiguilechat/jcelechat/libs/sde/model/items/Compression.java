package fr.guiguilechat.jcelechat.libs.sde.model.items;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EcompressibleTypes;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import lombok.Getter;

public class Compression extends DataSourced<EcompressibleTypes> {

	public static final Mapper<EcompressibleTypes, Compression> CACHE = new Mapper<>(
			EcompressibleTypes.LOADER.yaml(),
			Compression::new);

	public Compression(DataSource datasource, int id, EcompressibleTypes source) {
		super(datasource, id, source);
	}

	protected Compression(int id, EcompressibleTypes source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final Type source = datasource().types().of(id());

	@Getter(lazy = true)
	private final Type product = datasource().types().of(source().compressedTypeID);

}
