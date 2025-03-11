package fr.guiguilechat.jcelechat.libs.gameclient;

import java.io.File;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;

public class LoadStaticData {

	public static void main(String[] args) {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		cache.extractOn(name -> name.startsWith("staticdata"));
	}

}
