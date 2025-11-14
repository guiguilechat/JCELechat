package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EdynamicItemAttributes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dynamicItemAttributes";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EdynamicItemAttributes> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EdynamicItemAttributes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML,
			EdynamicItemAttributes.class, Set.of("attributeIDs"));

	public static final JacksonYamlLHMLoader<EdynamicItemAttributes> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class MinMax {
		public boolean highIsGood;
		public BigDecimal max;
		public BigDecimal min;
	}
	public Map<Integer, MinMax> attributeIDs = new LinkedHashMap<>();

	public static class TransformTypes {
		/** {@link Etypes} */
		public List<Integer> applicableTypes = new ArrayList<>();
		/** {@link Etypes} */
		public int resultingType;
	}
	public List<TransformTypes> inputOutputMapping = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		long morethanOneMapping = loaded.values().stream().filter(att -> att.inputOutputMapping.size() > 1).count();
		System.out.println("with more than one mapping : " + morethanOneMapping);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : attributes=" + first.attributeIDs.size());
	}
}
