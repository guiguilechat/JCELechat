package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EfighterAbilities {

	//
	// SDE loading
	//

	public static final IntMapLoader<EfighterAbilities> LOADER = new IntMapLoader<>(
			"fighterAbilities",
			EfighterAbilities.class);

	//
	// file structure
	//

	public boolean disallowInHighSec;
	public boolean disallowInLowSec;
	public LinkedHashMap<String, String> displayName = new LinkedHashMap<>();
	public int iconID;
	public String targetMode;
	public LinkedHashMap<String, String> tooltipText = new LinkedHashMap<>();
	public int turretGraphicID;


	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	public String enTooltipText() {
		return tooltipText == null ? null : tooltipText.get("en");
	}


	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : enDisplayName=" + first.enDisplayName() + " iconID=" + first.iconID
				+ " targetMode=" + first.targetMode);
	}

}
