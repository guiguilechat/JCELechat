package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Eskins {

	//
	// SDE loading
	//

	public static final IntMapLoader<Eskins> LOADER = new IntMapLoader<>(
			"skins",
			Eskins.class,
			Set.of("internalName"));

	//
	// file structure
	//

	public boolean allowCCPDevs;
	public String internalName;
	public boolean isStructureSkin;
	public HashMap<String, String> skinDescription = new LinkedHashMap<>();
	public int skinMaterialID;
	public List<Integer> types = new ArrayList<>();
	public boolean visibleSerenity;
	public boolean visibleTranquility;

	public String enSkinDescription() {
		return skinDescription == null ? null : skinDescription.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : skinDescription=" + first.enSkinDescription() + " internalname=" + first.internalName);
	}
}
