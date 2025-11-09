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

	public static final NamingMapper<EagentTypes, AgentType> CACHE = new NamingMapper<>(EagentTypes.LOADER,
			AgentType::new, AgentType::name);

	private final String name;

	public AgentType(DataSource datasource, int id, EagentTypes source) {
		super(datasource, id, source);
		name = source.name;
	}

	protected AgentType(int id, EagentTypes source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

}
