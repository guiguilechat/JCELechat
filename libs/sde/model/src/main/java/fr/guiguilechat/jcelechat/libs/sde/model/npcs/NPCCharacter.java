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

	public static final NamingMapper<EnpcCharacters, NPCCharacter> CACHE = new NamingMapper<>(EnpcCharacters.LOADER,
			NPCCharacter::new, NPCCharacter::name);

	private final String name;

	public NPCCharacter(DataSource datasource, int id, EnpcCharacters source) {
		super(datasource, id, source);
		name = source.enName();
	}

	protected NPCCharacter(int id, EnpcCharacters source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

}
