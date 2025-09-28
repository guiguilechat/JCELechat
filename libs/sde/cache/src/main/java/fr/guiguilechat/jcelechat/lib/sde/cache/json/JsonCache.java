package fr.guiguilechat.jcelechat.lib.sde.cache.json;

import java.io.File;

import fr.guiguilechat.jcelechat.lib.sde.cache.RemoteMeta;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.YamlCache;

public class JsonCache extends YamlCache {

	public static final JsonCache INSTANCE = new JsonCache();

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
		// TODO
		throw new UnsupportedOperationException();
	}

}
