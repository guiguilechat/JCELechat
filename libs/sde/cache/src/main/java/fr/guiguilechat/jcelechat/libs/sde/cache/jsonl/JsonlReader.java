package fr.guiguilechat.jcelechat.libs.sde.cache.jsonl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;

@RequiredArgsConstructor
public class JsonlReader<K, V> {

	@Getter
	private final String archiveFileName;

	@Getter(lazy = true)
	private final File archiveFile = new File(JsonlCache.INSTANCE.extractCacheDir(), archiveFileName);

	public Stream<JsonlKV<K, V>> from(InputStream is) {
		ObjectReader reader =
				new ObjectMapper()
						.readerFor(new TypeReference<JsonlKV<K, V>>() {
		});
		return new BufferedReader(new InputStreamReader(is)).lines()
				.map(l -> reader.readValue(l));
	}

	public Stream<JsonlKV<K, V>> stream() {
		try {
			return from(new FileInputStream(getArchiveFile()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
