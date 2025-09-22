package fr.guiguilechat.jcelechat.model.sde2.json;

import java.io.File;

import fr.guiguilechat.jcelechat.model.sde2.RemoteMeta;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache;

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
