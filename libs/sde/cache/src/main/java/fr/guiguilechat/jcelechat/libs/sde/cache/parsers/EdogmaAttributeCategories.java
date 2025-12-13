package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EdogmaAttributeCategories {

	//
	// SDE loading
	//

	public static final IntMapLoader<EdogmaAttributeCategories> LOADER = new IntMapLoader<>(
			"dogmaAttributeCategories",
			EdogmaAttributeCategories.class);

	//
	// file structure
	//

	public String description;
	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}
}
