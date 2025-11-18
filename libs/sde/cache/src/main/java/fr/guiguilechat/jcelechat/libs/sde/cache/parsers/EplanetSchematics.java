package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

/**
 * an entry in the sde/fsd/planetSchematics.yaml
 */
public class EplanetSchematics {

	//
	// SDE loading
	//

	public static final IntMapLoader<EplanetSchematics> LOADER = new IntMapLoader<>(
			"planetSchematics",
			EplanetSchematics.class,
			Set.of("cycleTime"));

	//
	// file structure
	//

	public static class SchemType {
		public boolean isInput;
		public int quantity;
	}

	public int cycleTime;
	public Map<String, String> name = new HashMap<>();
	public List<Integer> pins = new ArrayList<>();
	public Map<Integer, SchemType> types = new HashMap<>();

	public String enName() {
		return name.getOrDefault("en", "");
	}

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : cycletime=" + first.cycleTime + " name=" + first.enName());
	}

}
