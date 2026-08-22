package fr.guiguilechat.jcelechat.libs.sde.cache.jsonl;

import fr.guiguilechat.jcelechat.libs.sde.cache.SDECacheListener;

public abstract class JsonlCacheListener implements SDECacheListener {

	public JsonlCacheListener() {
		JsonlCache.INSTANCE.registerCache(this);
	}

}
