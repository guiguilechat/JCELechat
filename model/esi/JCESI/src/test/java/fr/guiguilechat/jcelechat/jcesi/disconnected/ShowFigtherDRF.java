package fr.guiguilechat.jcelechat.jcesi.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;

public class ShowFigtherDRF {

	public static void main(String[] args) {
		int ATDRF = 2231;
		int ABDRF = 2127;
		int FIGHTERCAT = 87;
		R_get_universe_categories_category_id cat = ESIStatic.INSTANCE.get_universe_categories(FIGHTERCAT, null).getOK();
		for (var gid : cat.groups) {
			R_get_universe_groups_group_id group = ESIStatic.INSTANCE.get_universe_groups(gid, null).getOK();
			for (var eid : group.types) {
				R_get_universe_types_type_id type = ESIStatic.INSTANCE.get_universe_types(eid, null).getOK();
				if (type.dogma_attributes != null) {
					boolean named = false;
					for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : type.dogma_attributes) {
						if (att.attribute_id == ATDRF || att.attribute_id == ABDRF) {
							if (!named) {
								System.out.println(type.name + "\t(" + group.name + ")");
								named = true;
							}
						}
						if (att.attribute_id == ATDRF) {
							System.out.println("\tATDRF=" + Math.log(att.value) / Math.log(5.5) + "\t(" + att.value + ")");
						}
						if (att.attribute_id == ABDRF) {
							System.out.println("\tABDRF=" + Math.log(att.value) / Math.log(5.5) + "\t(" + att.value + ")");
						}
					}
				}
			}
		}
	}

}
