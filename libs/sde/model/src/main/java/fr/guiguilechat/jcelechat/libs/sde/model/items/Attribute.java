package fr.guiguilechat.jcelechat.libs.sde.model.items;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaAttributes;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Attribute extends DataSourced<EdogmaAttributes> {

	public static final Mapper<EdogmaAttributes, Attribute> CACHE = new Mapper<>(
			EdogmaAttributes.LOADER.yaml(),
			Attribute::new);

	public Attribute(DataSource datasource, int id, EdogmaAttributes source) {
		super(datasource, id, source);
	}

	protected Attribute(int id, EdogmaAttributes source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

}
