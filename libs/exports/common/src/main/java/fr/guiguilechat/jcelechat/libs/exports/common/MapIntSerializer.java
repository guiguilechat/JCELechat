package fr.guiguilechat.jcelechat.libs.exports.common;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.type.MapType;
import tools.jackson.databind.type.TypeFactory;
import tools.jackson.dataformat.yaml.YAMLFactory;
import tools.jackson.dataformat.yaml.YAMLMapper;
import tools.jackson.dataformat.yaml.YAMLWriteFeature;

@AllArgsConstructor
public class MapIntSerializer<T> {

	private final String resourcePath;

	private final Class<T> internalClass;

	public LinkedHashMap<Integer, T> load(InputStream is) {
//		LoaderOptions loaderOptions = new LoaderOptions();
//		loaderOptions.setCodePointLimit(Integer.MAX_VALUE);
		YAMLFactory yamlFactory = YAMLFactory.builder()
//				.loaderOptions(loaderOptions)
				.build();
		var mapper = new ObjectMapper(yamlFactory);
		MapType mapType =
				TypeFactory.createDefaultInstance().constructMapType(LinkedHashMap.class, Integer.class,
						internalClass);
		var reader = mapper.readerFor(mapType);
		// we need to avoid closing the stream. So here we create a new parser instead
		// of reading the stream directlly.
		return reader.readValue(reader.createParser(is));
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final LinkedHashMap<Integer, T> load = load(
			getClass().getClassLoader().getResourceAsStream(resourcePath));

	public File export(LinkedHashMap<Integer, T> data, File folderout) {
		File output = new File(folderout, resourcePath);
		output.mkdirs();
		output.delete();
		var mapper =
				YAMLMapper.builder()
						.disable(YAMLWriteFeature.WRITE_DOC_START_MARKER)
						.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
						.changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(Include.NON_DEFAULT))
						.build();
		mapper.writeValue(output, data);
		return output;
	}

}
