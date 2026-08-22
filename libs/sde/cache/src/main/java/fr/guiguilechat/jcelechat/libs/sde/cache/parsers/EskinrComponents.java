package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EskinrComponents {

	//
	// SDE loading
	//

	public static final MapIntLoader<EskinrComponents> LOADER = new MapIntLoader<>(
			"skinrComponents",
			EskinrComponents.class);

	//
	// file structure
	//

	public static class LicensedType {
		public int licenseUsesGranted;
		public int typeID;
	}

	public List<LicensedType> associatedTypeIds = new ArrayList<>();
	public int category;
	public String finish;
	public String iconFile;
	public HashMap<String, String> name = new LinkedHashMap<>();
	public String projectionTypeU;
	public String projectionTypeV;
	public boolean published;
	public int rarity;
	public String resourceFile;

	public static class CountedType {
		public int count;
		public int itemTypeID;
	}

	public CountedType sequenceBinder;

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
