package fr.guiguilechat.jcelechat.libs.sde.cache.yaml;

import fr.guiguilechat.jcelechat.libs.sde.cache.SDECacheListener;
import fr.guiguilechat.jcelechat.libs.sde.cache.YamlCache;

public abstract class YAMLCacheListener implements SDECacheListener {

	public YAMLCacheListener() {
		YamlCache.INSTANCE.registerCache(this);
	}

}
