package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EmetaGroups {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmetaGroups> LOADER = new IntMapLoader<>(
			"metaGroups",
			EmetaGroups.class,
			Set.of("name"));

	//
	// file structure
	//

	public static class ColorRGB {
		public BigDecimal b, g, r;
	}

	public ColorRGB color;
	public HashMap<String, String> description = new HashMap<>();
	public int iconID;
	public String iconSuffix;
	public HashMap<String, String> name = new HashMap<>();

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
