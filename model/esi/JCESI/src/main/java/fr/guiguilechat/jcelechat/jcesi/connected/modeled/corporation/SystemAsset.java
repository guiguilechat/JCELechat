package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.HashMap;

/**
 * corporation assets in a system. Either we own the root asset(structure) or we
 * rent it
 *
 */
public class SystemAsset {

	/** Structure name -> asset folder of the structures we own */
	public final HashMap<String, CorpAssetFolder> owned = new HashMap<>();

	/** Structure name-> asset folder of the structures we rent */
	public final HashMap<String, CorpAssetFolder> rent = new HashMap<>();

}
