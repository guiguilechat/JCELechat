package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapRegions extends InSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapRegions";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapRegions> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapRegions> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmapRegions.class, Set.of("constellationIDs"));

	public static final JacksonYamlLHMLoader<EmapRegions> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public List<Integer> constellationIDs = new ArrayList<>();
	public Map<String, String> description = new LinkedHashMap<>();
	public int factionID;
	public int nebulaID;
	public int wormholeClassID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : whclass=" + first.wormholeClassID + " name=" + first.enName() + " position=" + first.position);
	}
}
