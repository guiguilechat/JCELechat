package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class Efactions {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "factions";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Efactions> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Efactions> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Efactions.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<Efactions> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int corporationID;
	public HashMap<String, String> description = new HashMap<>();
	public String flatLogo;
	public String flatLogoWithName;
	public int iconID;
	public List<Integer> memberRaces = new ArrayList<>();
	public int militiaCorporationID;
	public HashMap<String, String> name = new HashMap<>();
	public HashMap<String, String> shortDescription = new HashMap<>();
	public BigDecimal sizeFactor;
	public int solarSystemID;
	public boolean uniqueName;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}
}
