package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EfreelanceJobSchemas;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EfreelanceJobSchemas.FrelanceJob;

public class JacksonParsing {

	public static void main(String[] args) throws IOException {
		YamlCache.INSTANCE.donwloadSDE();
		File source = new File(YamlCache.INSTANCE.extractCacheDir(), EfreelanceJobSchemas.LOADER.yamlFileName());

		LoaderOptions loaderOptions = new LoaderOptions();
		loaderOptions.setCodePointLimit(Integer.MAX_VALUE);
		YAMLFactory yamlFactory = YAMLFactory.builder()
				.loaderOptions(loaderOptions)
				.build();
		var mapper = new ObjectMapper(yamlFactory);

		{
			var tr = new TypeReference<LinkedHashMap<Integer, EfreelanceJobSchemas>>() {
			};
			var reader = mapper.readerFor(tr);
			LinkedHashMap<Integer, EfreelanceJobSchemas> map = reader.readValue(reader.createParser(source), tr);
			FrelanceJob bs = map.get(1).BoostShield;
			System.out.println(bs.enDescription());
		}

		{
			MapType mapType = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, Integer.class,
					EfreelanceJobSchemas.class);
			var reader = mapper.readerFor(mapType);
			LinkedHashMap<Integer, EfreelanceJobSchemas> map = reader.readValue(reader.createParser(source), mapType);
			FrelanceJob bs = map.get(1).BoostShield;
			System.out.println(bs.enDescription());
		}

		{
			LinkedHashMap<Integer, EfreelanceJobSchemas> map = EfreelanceJobSchemas.LOADER.jackson().load();
			FrelanceJob bs = map.get(1).BoostShield;
			System.out.println(bs.enDescription());
		}
	}

}
