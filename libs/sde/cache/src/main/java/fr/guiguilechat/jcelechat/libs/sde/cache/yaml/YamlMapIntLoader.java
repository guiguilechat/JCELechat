package fr.guiguilechat.jcelechat.libs.sde.cache.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.FormattedLoader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.type.MapType;
import tools.jackson.databind.type.TypeFactory;

/// [FormattedLoader] dedicated to `Map<Integer, U>` loading, from yaml
/// file format.
@RequiredArgsConstructor
public class YamlMapIntLoader<U> extends YAMLCacheListener implements FormattedLoader<LinkedHashMap<Integer, U>> {

	@Getter
	private final String archiveFileName;

	@Getter
	private final Class<U> clazz;

	@Getter(lazy = true)
	private final File archiveFile = new File(YamlCache.INSTANCE.extractCacheDir(), archiveFileName);

	private LinkedHashMap<Integer, U> cache = null;

	@Override
	public void onSDECacheCleared() {
		cache = null;
	}

	@Override
	public synchronized LinkedHashMap<Integer, U> load() {
		if (cache == null) {
			try {
				YamlCache.INSTANCE.donwloadSDE();
				cache = from(new FileInputStream(getArchiveFile()));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException(e);
			}
		}
		return cache;
	}

	public LinkedHashMap<Integer, U> from(InputStream is) {
		MapType mapType =
				TypeFactory.createDefaultInstance().constructMapType(LinkedHashMap.class, Integer.class,
						clazz);
		var reader = mapper().readerFor(mapType);
		// can't call readValue with second param as it will close the stream.
		return reader.readValue(reader.createParser(is));
	}

	public U get(int id) {
		return load().get(id);
	}

}
