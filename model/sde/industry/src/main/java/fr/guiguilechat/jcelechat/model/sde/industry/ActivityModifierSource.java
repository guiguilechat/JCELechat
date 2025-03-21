package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
public class ActivityModifierSource {

	//
	// storage
	//

	public static final String RESOURCE_PATH = "SDE/industry/activitymodifiersources.yaml";

	public static LinkedHashMap<Integer, ActivityModifierSource> load(InputStream is) {
		try (InputStreamReader reader = new InputStreamReader(is)) {
			LoaderOptions options = new LoaderOptions();
			options.setCodePointLimit(Integer.MAX_VALUE);
			return new Yaml(options).loadAs(reader, Container.class).filters;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final LinkedHashMap<Integer, ActivityModifierSource> load = load(
			TargetFilter.class.getClassLoader().getResourceAsStream(RESOURCE_PATH));

	public static File export(LinkedHashMap<Integer, ActivityModifierSource> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.filters = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new RuntimeException("while exporting to " + output.getAbsolutePath(), e);
		}
		return output;
	}

	private static final class Container {
		public LinkedHashMap<Integer, ActivityModifierSource> filters;
	}

	//
	// structure
	//

	@AllArgsConstructor
	@NoArgsConstructor
	public static class ModifiedActivity {

		@AllArgsConstructor
		@NoArgsConstructor
		public static class AttributeFilter {
			public int attributeId;
			public Integer filterId;
		}

		public List<AttributeFilter> cost, material, time;

	}

	int typeId;

	public ModifiedActivity copying, invention, manufacturing, reaction, researchMaterial, researchTime;

}
