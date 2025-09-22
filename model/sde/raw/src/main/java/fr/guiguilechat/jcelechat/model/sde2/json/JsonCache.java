package fr.guiguilechat.jcelechat.model.sde2.json;

import fr.guiguilechat.jcelechat.model.sde2.RemoteMeta;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache;

public class JsonCache extends YamlCache {

	public static final JsonCache INSTANCE = new JsonCache();

	@Override
	protected String formatDir() {
		return "json/";
	}

	@Override
	protected String extractURL(RemoteMeta meta) {
		return meta.jsonURL();
	}

}
