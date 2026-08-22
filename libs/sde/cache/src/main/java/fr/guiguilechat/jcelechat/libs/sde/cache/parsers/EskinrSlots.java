package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EskinrSlots {

	//
	// SDE loading
	//

	public static final IntMapLoader<EskinrSlots> LOADER = new IntMapLoader<>(
			"skinrSlots",
			EskinrSlots.class);

	//
	// file structure
	//

	public List<Integer> allowedDesignComponentCategories = new ArrayList<>();
	public int category;
	public HashMap<String, String> name = new LinkedHashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}

}
