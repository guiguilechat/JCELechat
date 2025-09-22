package fr.guiguilechat.jcelechat.model.sde2.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
		try {
			return mapper.readerFor(new TypeReference<U>() {
			}).readValue(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
