package fr.guiguilechat.jcelechat.libs.sde.model.npcs;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NPCCorporation extends DataSourced<EnpcCorporations> {

	public static final Mapper<EnpcCorporations, NPCCorporation> CACHE = new Mapper<>(EnpcCorporations.LOADER.yaml(),
			NPCCorporation::new);

	public NPCCorporation(DataSource datasource, int id, EnpcCorporations source) {
		super(datasource, id, source);
	}

	protected NPCCorporation(int id, EnpcCorporations source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final Faction faction = datasource().factions().of(source().factionID);

	@Getter(lazy = true)
	private final String name = source().enName();


}