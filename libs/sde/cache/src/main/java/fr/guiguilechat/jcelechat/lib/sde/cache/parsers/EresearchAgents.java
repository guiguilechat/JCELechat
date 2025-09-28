package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EresearchAgents {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "researchAgents";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EresearchAgents> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EresearchAgents> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EresearchAgents.class, Set.of("skills"));

	public static final JacksonYamlLHMLoader<EresearchAgents> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class TypeID {
		public int typeID;
	}
	public List<TypeID> skills = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : skills=" + first.skills.size());
	}
}
