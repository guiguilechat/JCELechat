package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class ElinkWithShip {

	//
	// SDE loading
	//

	public static final MapIntLoader<ElinkWithShip> LOADER = new MapIntLoader<>(
			"linkWithShip",
			ElinkWithShip.class);

	//
	// file structure
	//

	public boolean applyPvpFlag;
	public boolean canRelink;
	public BigDecimal characterEnergyCost;
	public int dbuffPostLinkDuration;
	public Map<Integer, BigDecimal> dbuffs;
	public boolean generateCynoInhibitor;
	public boolean keepDbuffDurationOnLinkBreak;
	public int linkDuration;
	public int linkEffectGraphicIDOverride;
	public int linkableShipTypeListID;
	public int maxLinkRange;
	public boolean omegaOnly;
	public BigDecimal solarsystemInterferenceCost;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : characterEnergyCost=" + first.characterEnergyCost);
	}

}
