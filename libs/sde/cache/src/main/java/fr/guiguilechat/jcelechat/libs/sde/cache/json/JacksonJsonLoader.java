package fr.guiguilechat.jcelechat.libs.sde.cache.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import fr.guiguilechat.jcelechat.libs.sde.cache.JsonCache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
public class JacksonJsonLoader<U> {

	@Getter
	private final String archiveFileName;

	@Getter(lazy = true)
	private final File archiveFile = new File(JsonCache.INSTANCE.extractCacheDir(), archiveFileName);

	private U cache = null;

	public synchronized U load() {
		if (cache == null) {
			try {
				JsonCache.INSTANCE.donwloadSDE();
				cache = from(new FileInputStream(getArchiveFile()));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException(e);
			}
		}
		return cache;
	}

	public U from(InputStream is) {
		var mapper = new ObjectMapper();
		return mapper.readerFor(new TypeReference<U>() {
		}).readValue(is);
	}

}
