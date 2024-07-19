package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ERegion;

public class Universe {

	public final LinkedHashMap<String, ERegion> abyssal = new LinkedHashMap<>();

	public final LinkedHashMap<String, ERegion> eve = new LinkedHashMap<>();

	public final LinkedHashMap<String, ERegion> starter = new LinkedHashMap<>();

	public final LinkedHashMap<String, ERegion> wormhole = new LinkedHashMap<>();

	private static Universe cache = null;

	public static synchronized Universe load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			cache = new Universe();
			for(Entry<String, LinkedHashMap<String, ERegion>> e : Map.of(
			    "universe/abyssal/", cache.abyssal,
			    "universe/eve/", cache.eve,
			    "universe/void/", cache.starter,
			    "universe/wormhole/", cache.wormhole
					).entrySet()) {
				String fileName = e.getKey();
				LinkedHashMap<String, ERegion> regions = e.getValue();
				Stream.of(new File(SDECache.INSTANCE.extractCacheDir(), fileName).listFiles()).parallel().forEach(f -> {
					ERegion reg = ERegion.load(f);
					synchronized (cache) {
						regions.put(f.getName(), reg);
					}
				});
			}
		}
		return cache;
	}

}
