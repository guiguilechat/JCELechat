package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlLoader;

public interface MapLoader<K, V> {

	YamlLoader<Map<K, V>> yaml();

}
