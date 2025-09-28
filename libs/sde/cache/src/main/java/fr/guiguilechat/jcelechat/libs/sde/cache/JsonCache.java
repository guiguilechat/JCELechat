package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.io.File;

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
