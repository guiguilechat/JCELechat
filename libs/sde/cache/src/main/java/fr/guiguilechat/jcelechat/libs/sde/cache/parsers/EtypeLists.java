package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EtypeLists {

	//
	// SDE loading
	//

	public static final IntMapLoader<EtypeLists> LOADER =
			new IntMapLoader<>(
					"typeLists",
					EtypeLists.class);

	//
	// file structure
	//

	public Map<String, String> displayDescription = new LinkedHashMap<>();
	public Map<String, String> displayName = new LinkedHashMap<>();
	public List<Integer> excludedCategoryIDs;
	public List<Integer> excludedGroupIDs;
	public List<Integer> excludedTypeIDs;
	public List<Integer> includedCategoryIDs;
	public List<Integer> includedGroupIDs;
	public List<Integer> includedTypeIDs;
	public String name;

	public String enDisplayDescription() {
		return displayDescription == null ? null : displayDescription.get("en");
	}

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	// testing

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}

}
