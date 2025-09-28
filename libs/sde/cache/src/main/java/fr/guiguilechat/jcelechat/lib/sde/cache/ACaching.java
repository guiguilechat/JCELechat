package fr.guiguilechat.jcelechat.lib.sde.cache;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.YamlCache;

public abstract class ACaching implements Caching {

	public ACaching() {
		YamlCache.INSTANCE.registerCache(this);
	}

}
