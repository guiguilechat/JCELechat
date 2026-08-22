package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EskinrSlotsToMaterials.MaterialSlot;

@SuppressWarnings("serial")
public class EskinrSlotsToMaterials extends ArrayList<MaterialSlot> {

	//
	// SDE loading
	//

	public static final IntMapLoader<EskinrSlotsToMaterials> LOADER = new IntMapLoader<>(
			"skinrSlotsToMaterials",
			EskinrSlotsToMaterials.class);

	//
	// file structure
	//

	public static class MaterialSlot {
		public int materialID;
		public int slotID;
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : size=" + first.size() + " [0].materialId=" + first.get(0).materialID);
	}

}
