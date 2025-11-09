package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class Ecertificates {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "certificates";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Ecertificates> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Ecertificates> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Ecertificates.class, Set.of("groupID"));

	public static final JacksonYamlLHMLoader<Ecertificates> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> description = new HashMap<>();
	/** ? {@link Egroups} ? */
	public int groupID;
	public HashMap<String, String> name = new HashMap<>();
	public List<Integer> recommendedFor = new ArrayList<>();

	public static class SkillTypes {
		public int advanced;
		public int basic;
		public int elite;
		public int improved;
		public int standard;
	}

	public HashMap<Integer, SkillTypes> skillTypes = new LinkedHashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " recommended=" + first.recommendedFor.size());
	}

}
