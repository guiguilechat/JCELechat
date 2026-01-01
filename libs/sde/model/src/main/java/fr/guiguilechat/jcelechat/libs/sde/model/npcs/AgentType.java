package fr.guiguilechat.jcelechat.libs.sde.model.npcs;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EagentTypes;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class AgentType extends DataSourced<EagentTypes> {

	public static final NamingMapper<EagentTypes, AgentType> CACHE = new NamingMapper<>(
			EagentTypes.LOADER.yaml(),
			AgentType::new,
			AgentType::name);

	@Getter(lazy = true)
	private final String name = source().name;

	public AgentType(DataSource datasource, int id, EagentTypes source) {
		super(datasource, id, source);
	}

	protected AgentType(int id, EagentTypes source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	public String toString() {
		return "" + name() + " (" + id() + ")";
	}

}
