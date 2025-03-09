package fr.guiguilechat.jcelechat.libs.everaw;

import java.io.File;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.meta.ClientInfo;

public class LoadStaticData {

	public static void main(String[] args) {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		cache.extractOn(name -> name.startsWith("staticdata"));
	}

}
