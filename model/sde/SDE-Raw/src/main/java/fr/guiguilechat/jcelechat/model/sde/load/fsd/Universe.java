package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region;

public class Universe {

	public final LinkedHashMap<String, Region> eve = new LinkedHashMap<>();

	public final LinkedHashMap<String, Region> wormhole = new LinkedHashMap<>();

	private static Universe cache = null;

	public static synchronized Universe load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			cache = new Universe();
			Stream.of(new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/universe/eve/").listFiles()).parallel().forEach(f -> {
				Region reg = Region.load(f);
				synchronized (cache) {
					cache.eve.put(f.getName(), reg);
				}
			});
			Stream.of(new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/universe/wormhole/").listFiles()).parallel()
					.forEach(f -> {
						Region reg = Region.load(f);
						synchronized (cache) {
							cache.wormhole.put(f.getName(), reg);
						}
					});
		}
		return cache;
	}

}
