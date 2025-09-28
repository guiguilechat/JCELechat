package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EtypeDogma {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "typeDogma";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EtypeDogma> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EtypeDogma> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EtypeDogma.class, Set.of("dogmaAttributes"));

	public static final JacksonYamlLHMLoader<EtypeDogma> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class EAttributes{

		public int attributeID;
		public BigDecimal value;

	}

	public static class Eeffects{

		public int effectID;
		public boolean isDefault;

	}

	public List<EAttributes> dogmaAttributes = new ArrayList<>();

	public List<Eeffects> dogmaEffects = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : attributes=" + first.dogmaAttributes.size() + " effects=" + first.dogmaEffects.size());
	}

}
