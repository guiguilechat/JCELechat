package fr.guiguilechat.jcelechat.libs.gameclient;

import java.io.File;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;

public class LoadStaticData {

	/**
	 * load the static data into a cache. If not arg specified, it will be
	 * PWD/.evecache ; otherwise, using args[0]
	 */
	public static void main(String[] args) {
		File cacheDir = new File(".evecache");
		if (args != null && args.length > 0) {
			cacheDir=new File(args[0]);
		}
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		cache.extractOn(name -> name.startsWith("staticdata"));
	}

}
