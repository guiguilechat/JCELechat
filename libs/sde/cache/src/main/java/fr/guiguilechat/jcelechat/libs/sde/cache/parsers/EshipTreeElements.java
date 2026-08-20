package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EshipTreeElements {

	//
	// SDE loading
	//

	public static final IntMapLoader<EshipTreeElements> LOADER = new IntMapLoader<>(
			"shipTreeElements",
			EshipTreeElements.class);

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public String icon;
	public Map<String, String> name = new LinkedHashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : enName=" + first.enName());
	}

}
