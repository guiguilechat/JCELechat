package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EmarketGroups {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmarketGroups> LOADER = new IntMapLoader<>(
			"marketGroups",
			EmarketGroups.class,
			Set.of("name"));

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public boolean hasTypes;
	public int iconID;
	public Map<String, String> name = new LinkedHashMap<>();
	/** {@link EmarketGroups} */
	public int parentGroupID;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}
}
