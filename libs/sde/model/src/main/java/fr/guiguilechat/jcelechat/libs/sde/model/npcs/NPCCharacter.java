package fr.guiguilechat.jcelechat.libs.sde.model.npcs;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NPCCharacter extends DataSourced<EnpcCharacters> {

	public static final NamingMapper<EnpcCharacters, NPCCharacter> CACHE = new NamingMapper<>(
			EnpcCharacters.LOADER.yaml(),
			NPCCharacter::new, NPCCharacter::name);

	@Getter(lazy = true)
	private final String name = source().enName();

	public static class AgentData {
		private final fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters.AgentData data;
		private final DataSource datasource;

		@Getter(lazy = true)
		private final AgentType agentType = datasource.agentTypes().of(data.agentTypeID);

		@Getter(lazy = true)
		private final NPCCorporationDivision npcCorporationDivision = datasource.npcCorporationDivisions()
				.of(data.divisionID);

		public boolean isLocator;

		public int level;

		public AgentData(fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters.AgentData data,
				DataSource datasource) {
			this.data = data;
			this.datasource = datasource;
			isLocator = data.isLocator;
			level = data.level;
		}
	}

	@Getter(lazy = true)
	private final AgentData data = source().agent == null ? null : new AgentData(source().agent, datasource());

	@Getter(lazy = true)
	private final NPCCorporation corporation = datasource().npcCorporations().of(source().corporationID);

	public NPCCharacter(DataSource datasource, int id, EnpcCharacters source) {
		super(datasource, id, source);
	}

	protected NPCCharacter(int id, EnpcCharacters source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

}
