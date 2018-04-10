package fr.guiguilechat.eveonline.model.sde.load.fsd;

import java.io.File;
import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.model.sde.load.SDECache;
import fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Region;

public class Universe {

	public final LinkedHashMap<String, Region> eve = new LinkedHashMap<>();

	public final LinkedHashMap<String, Region> wormhole = new LinkedHashMap<>();

	private static Universe cache = null;

	public static synchronized Universe load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			cache = new Universe();
			for (File f : new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/universe/eve/").listFiles()) {
				cache.eve.put(f.getName(), Region.load(f));
			}
			for (File f : new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/universe/wormhole/").listFiles()) {
				cache.wormhole.put(f.getName(), Region.load(f));
			}
		}
		return cache;
	}

}
