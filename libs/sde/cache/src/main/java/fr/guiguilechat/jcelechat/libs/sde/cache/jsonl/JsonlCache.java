package fr.guiguilechat.jcelechat.libs.sde.cache.jsonl;

import java.io.File;

import fr.guiguilechat.jcelechat.libs.sde.cache.RemoteMeta;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlCache;

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

		// return new ObjectMapper().readValue(file, SdeMeta.class).sde;
		// TODO
		throw new UnsupportedOperationException();
	}

}
