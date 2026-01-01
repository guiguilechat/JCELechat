package fr.guiguilechat.jcelechat.libs.sde.model.npcs;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Efactions;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Faction extends DataSourced<Efactions> {

	public static final NamingMapper<Efactions, Faction> CACHE = new NamingMapper<>(
			Efactions.LOADER.yaml(),
			Faction::new,
			Faction::name);

	public Faction(DataSource datasource, int id, Efactions source) {
		super(datasource, id, source);
	}

	protected Faction(int id, Efactions source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final String name = source().enName();

}
