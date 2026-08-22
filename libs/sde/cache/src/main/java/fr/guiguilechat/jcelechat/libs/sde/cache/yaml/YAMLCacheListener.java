package fr.guiguilechat.jcelechat.libs.sde.cache.yaml;

import org.snakeyaml.engine.v2.api.LoadSettings;

import fr.guiguilechat.jcelechat.libs.sde.cache.SDECacheListener;
import tools.jackson.dataformat.yaml.YAMLFactory;
import tools.jackson.dataformat.yaml.YAMLMapper;

/// auto registers itself on the yaml cache upon construction.
///
/// is abstract because can't define constructor/post constructor in interfaces.
public abstract class YAMLCacheListener implements SDECacheListener {

	public YAMLCacheListener() {
		YamlCache.INSTANCE.registerCache(this);
	}

	/// utility to be sure we can load big yaml files.
	///
	/// This is because internally, jackson calls snakeyaml to load files, so we need
	/// to build a snakeyaml LoadSettings, into a YamlFactory, to create our
	/// mapper off.
	///
	/// Complex ? THIS. IS. JAVAAAAA .
	public YAMLMapper mapper() {
		return new YAMLMapper(YAMLFactory.builder()
				.loadSettings(LoadSettings.builder()
						.setCodePointLimit(Integer.MAX_VALUE)
						.build())
				.build());
	}

}
