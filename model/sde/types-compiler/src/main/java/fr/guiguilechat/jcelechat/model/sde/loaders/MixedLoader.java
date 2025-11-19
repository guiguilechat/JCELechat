package fr.guiguilechat.jcelechat.model.sde.loaders;

import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;

public class MixedLoader {
	public static TypeHierarchy load() {
		var hierarchy_esi = ESILoader.load();
		var hierarchy_sde = Sde2Loader.load();
		for (TypeDetails type_esi : hierarchy_esi.typeID2Details.values()) {
			var type_sde = hierarchy_sde.typeID2Details.get(type_esi.id);
			if (type_sde != null) {
				type_esi.basePrice = type_sde.basePrice;
			}
		}
		return hierarchy_esi;
	}

}
