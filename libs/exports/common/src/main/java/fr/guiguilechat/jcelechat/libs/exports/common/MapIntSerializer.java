package fr.guiguilechat.jcelechat.libs.exports.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;

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

@AllArgsConstructor
public class MapIntSerializer<T> {

	private final String resourcePath;

	private final Class<T> internalClass;

	public LinkedHashMap<Integer, T> load(InputStream is) {
		LoaderOptions loaderOptions = new LoaderOptions();
		loaderOptions.setCodePointLimit(Integer.MAX_VALUE);
		YAMLFactory yamlFactory = YAMLFactory.builder()
				.loaderOptions(loaderOptions)
				.build();
		var mapper = new ObjectMapper(yamlFactory);
		try {
			MapType mapType = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, Integer.class,
					internalClass);
			var reader = mapper.readerFor(mapType);
			// can't call readValue with second param as it will close the stream.
			return reader.readValue(reader.createParser(is), mapType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final LinkedHashMap<Integer, T> load = load(
			getClass().getClassLoader().getResourceAsStream(resourcePath));

	public File export(LinkedHashMap<Integer, T> data, File folderout) {
		File output = new File(folderout, resourcePath);
		output.mkdirs();
		output.delete();
		try {
			var mapper = YAMLMapper.builder()
					.disable(Feature.WRITE_DOC_START_MARKER)
					.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
					.build();
			mapper.setDefaultPropertyInclusion(Include.NON_DEFAULT);
			mapper.writeValue(output, data);
		} catch (IOException e) {
			throw new RuntimeException("while exporting to " + output.getAbsolutePath(), e);
		}
		return output;
	}

}
