package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.ArrayList;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EtypeMaterials {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "typeMaterials";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EtypeMaterials> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EtypeMaterials> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EtypeMaterials.class, Set.of("materials"));

	public static final JacksonYamlLHMLoader<EtypeMaterials> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Material {
		public int quantity;
		public int materialTypeID;

		@Override
		public String toString() {
			return "" + quantity + "×id:" + materialTypeID;
		}
	}

	public ArrayList<Material> materials = new ArrayList<>();

	@Override
	public String toString() {
		return materials.toString();
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : materials=" + first.materials.size());
		for(var val : loaded.values()) assert(val.getClass().equals(first.getClass())); 
	}

}
