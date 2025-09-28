package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlCache;

public abstract class ACaching implements Caching {

	public ACaching() {
		YamlCache.INSTANCE.registerCache(this);
	}

}
