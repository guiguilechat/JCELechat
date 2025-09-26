package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Eskins {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "skins";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Eskins> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Eskins> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Eskins.class, Set.of("internalName"));

	public static final JacksonYamlLHMLoader<Eskins> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public boolean allowCCPDevs;
	public String internalName;
	public boolean isStructureSkin;
	public HashMap<String, String> skinDescription = new LinkedHashMap<>();
	public int skinID;
	public int skinMaterialID;
	public List<Integer> types = new ArrayList<>();
	public boolean visibleSerenity;
	public boolean visibleTranquility;

	public String enSkinDescription() {
		return skinDescription == null ? null : skinDescription.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : skinDescription=" + first.enSkinDescription() + " internalname=" + first.internalName);
		long diffSkinId = loaded.entrySet().stream().filter(e -> e.getKey() != e.getValue().skinID).count();
		System.out.println("with key mismatching skinID : " + diffSkinId);
	}
}
