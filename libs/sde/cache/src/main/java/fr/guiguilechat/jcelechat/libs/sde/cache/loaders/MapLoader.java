package fr.guiguilechat.jcelechat.libs.sde.cache.loaders;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.jsonl.JsonlReader;

public interface MapLoader<K, V> {

	FormattedLoader<LinkedHashMap<K, V>> yaml();

	JsonlReader<K, V> jsonl();

}
