package fr.guiguilechat.jcelechat.libs.gameclient;

import java.io.File;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.PythonLoadedLib;

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
		System.out.println("build number " + cache.getClientInfo().getBuildNumber());

		System.out.println("\nstaticdata/");
		System.out.println(cache.extractOn(name -> name.startsWith("staticdata/"), System.out, null));

		System.out.println("\n*Loader.pyd");
		System.out.println(cache.extractOn(name -> name.endsWith("Loader.pyd"), System.out, null));

		System.out.println("\n.*.fsdbinary");
		System.out.println(cache.extractOn(name -> name.endsWith(".fsdbinary"), System.out, null));

		System.out.println("\npython loaders : ");
		AtomicInteger pyLibCnt = new AtomicInteger(0);
		cache.getPyLibs().values().stream()
				.sorted(Comparator.comparing(PythonLoadedLib::getResourceName))
				.forEach(pdl -> {
					System.out.println(pdl.getResourceName() + " from " + pdl.getFsdbinary().resName() + " using "
							+ pdl.getLoader().resName());
					cache.cache(pdl);
					pyLibCnt.addAndGet(1);
				});
		System.out.println(pyLibCnt);
	}

}
