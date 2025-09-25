package fr.guiguilechat.jcelechat.model.sde2.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import fr.guiguilechat.jcelechat.model.sde2.cache.ACaching;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JacksonYamlLHMLoader<U> extends ACaching {

	@Getter
	private final String archiveFileName;

	@Getter(lazy = true)
	private final File archiveFile = new File(YamlCache.INSTANCE.extractCacheDir(), archiveFileName);

	private LinkedHashMap<Integer, U>  cache = null;

	@Override
	public void clearCache() {
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
			var tr = new TypeReference<LinkedHashMap<Integer, U>>() {
			};
			var reader = mapper.readerFor(tr);
			// can't readValue as it will close the stream.
			return reader.readValue(reader.createParser(is), tr);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public U get(int id) {
		return load().get(id);
	}

}
