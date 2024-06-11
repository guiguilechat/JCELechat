package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region;

public class Universe {

	public final LinkedHashMap<String, Region> abyssal = new LinkedHashMap<>();

	public final LinkedHashMap<String, Region> eve = new LinkedHashMap<>();

	public final LinkedHashMap<String, Region> starter = new LinkedHashMap<>();

	public final LinkedHashMap<String, Region> wormhole = new LinkedHashMap<>();

	private static Universe cache = null;

	public static synchronized Universe load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			cache = new Universe();
			for(Entry<String, LinkedHashMap<String, Region>> e : Map.of(
			    "universe/abyssal/", cache.abyssal,
			    "universe/eve/", cache.eve,
			    "universe/void/", cache.starter,
			    "universe/wormhole/", cache.wormhole
					).entrySet()) {
				String fileName = e.getKey();
				LinkedHashMap<String, Region> regions = e.getValue();
				Stream.of(new File(SDECache.INSTANCE.extractCacheDir(), fileName).listFiles()).parallel().forEach(f -> {
					Region reg = Region.load(f);
					synchronized (cache) {
						regions.put(f.getName(), reg);
					}
				});
			}
		}
		return cache;
	}

}
