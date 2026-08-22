package fr.guiguilechat.jcelechat.libs.sde.cache.jsonl;

import java.io.File;

import fr.guiguilechat.jcelechat.libs.sde.cache.RemoteMeta;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlCache;
import tools.jackson.databind.ObjectMapper;

public class JsonlCache extends YamlCache {

	public static final JsonlCache INSTANCE = new JsonlCache();

	@Override
	protected String format() {
		return "jsonl";
	}

	@Override
	protected String extractURL(RemoteMeta meta) {
		return meta.jsonURL();
	}

	@Override
	protected RemoteMeta extractArchiveMeta(File file) {
		RemoteMeta parsed = new ObjectMapper().readerFor(RemoteMeta.class).readValue(file);
		return parsed;
	}

}
