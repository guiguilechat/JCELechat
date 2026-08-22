package fr.guiguilechat.jcelechat.libs.sde.cache.loaders;

import java.util.LinkedHashMap;

public interface MapLoader<K, V> {

	FormattedLoader<LinkedHashMap<K, V>> yaml();

}
