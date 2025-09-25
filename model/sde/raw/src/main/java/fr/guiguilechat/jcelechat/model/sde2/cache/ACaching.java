package fr.guiguilechat.jcelechat.model.sde2.cache;

import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache;

public abstract class ACaching implements Caching {

	public ACaching() {
		YamlCache.INSTANCE.registerCache(this);
	}

}
