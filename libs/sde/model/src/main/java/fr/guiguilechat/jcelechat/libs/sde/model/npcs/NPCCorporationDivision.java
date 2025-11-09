package fr.guiguilechat.jcelechat.libs.sde.model.npcs;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NPCCorporationDivision extends DataSourced<EnpcCorporationDivisions> {

	public static final Mapper<EnpcCorporationDivisions, NPCCorporationDivision> CACHE = new Mapper<>(
			EnpcCorporationDivisions.LOADER,
			NPCCorporationDivision::new);

	public NPCCorporationDivision(DataSource datasource, int id, EnpcCorporationDivisions source) {
		super(datasource, id, source);
	}

	protected NPCCorporationDivision(int id, EnpcCorporationDivisions source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

}