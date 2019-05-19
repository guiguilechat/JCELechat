package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;

public class CorpAssetFolder {

	public final R_get_corporations_corporation_id_assets asset;

	public CorpAssetFolder(R_get_corporations_corporation_id_assets asset) {
		this.asset = asset;
	}

	public final HashMap<get_corporations_corporation_id_assets_location_flag, List<CorpAssetFolder>> subFolders = new HashMap<>();

	public void addAsset(CorpAssetFolder child) {
		get_corporations_corporation_id_assets_location_flag flag = child.asset.location_flag;
		List<CorpAssetFolder> list = subFolders.get(flag);
		if (list == null) {
			list = new ArrayList<>();
			subFolders.put(flag, list);
		}
		list.add(child);
	}

}
