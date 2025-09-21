package fr.guiguilechat.jcelechat.model.sde.load;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JacksonYamlLoader<U> {

	@Getter
	private final String archiveFileName;

	@Getter(lazy = true)
	private final File archiveFile = new File(SDECache.INSTANCE.extractCacheDir(), archiveFileName);

	private U cache = null;

	public synchronized U load() {
		if (cache == null) {
			try {
				cache = from(new FileInputStream(getArchiveFile()));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException(e);
			}
		}
		return cache;
	}

	public U from(InputStream is) {
		LoaderOptions loaderOptions = new LoaderOptions();
		loaderOptions.setCodePointLimit(Integer.MAX_VALUE);
		YAMLFactory yamlFactory = YAMLFactory.builder()
			    .loaderOptions(loaderOptions)
			    .build();
		var mapper = new ObjectMapper(yamlFactory);
		try {
			return mapper.readerFor(new TypeReference<U>() {
			}).readValue(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
