package fr.guiguilechat.jcelechat.libs.sde.cache.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import fr.guiguilechat.jcelechat.libs.sde.cache.YamlCache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class YamlMapIntLoader<U> extends YAMLCacheListener {

	@Getter
	private final String archiveFileName;

	@Getter
	private final Class<U> clazz;

	@Getter(lazy = true)
	private final File archiveFile = new File(YamlCache.INSTANCE.extractCacheDir(), archiveFileName);

	private LinkedHashMap<Integer, U>  cache = null;

	@Override
	public void onSDECacheCleared() {
		cache = null;
	}

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

	public LinkedHashMap<Integer, U>  from(InputStream is) {
		LoaderOptions loaderOptions = new LoaderOptions();
		loaderOptions.setCodePointLimit(Integer.MAX_VALUE);
		YAMLFactory yamlFactory = YAMLFactory.builder()
			    .loaderOptions(loaderOptions)
			    .build();
		var mapper = new ObjectMapper(yamlFactory);
		try {
			MapType mapType = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, Integer.class,
					clazz);
			var reader = mapper.readerFor(mapType);
			// can't call readValue with second param as it will close the stream.
			return reader.readValue(reader.createParser(is), mapType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public U get(int id) {
		return load().get(id);
	}

}
