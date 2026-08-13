package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EgraphicMaterialSets {

	//
	// SDE loading
	//

	public static final IntMapLoader<EgraphicMaterialSets> LOADER = new IntMapLoader<>(
			"graphicMaterialSets",
			EgraphicMaterialSets.class);

	//
	// file structure
	//

	public static class Color {
		public BigDecimal a,
				b,
				g,
				r;
	}

	public Color colorHull,
			colorPrimary,
			colorSecondary,
			colorWindow;

	public String custommaterial1, custommaterial2;
	public String description;
	public String material1, material2, material3, material4;
	public String resPathInsert;
	public String sofFactionName;
	public String sofPatternName;
	public String sofRaceHint;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : description=" + first.description);
	}
}
