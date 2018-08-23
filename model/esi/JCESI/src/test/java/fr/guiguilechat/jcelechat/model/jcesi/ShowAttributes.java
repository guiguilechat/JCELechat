package fr.guiguilechat.jcelechat.model.jcesi;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_type_dogma_attributes_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_universe_types_type_id;

public class ShowAttributes {

	public static void main(String[] args) {
		R_get_universe_types_type_id types = ESIStatic.INSTANCE.get_universe_types(36846, null);
		for (M_get_type_dogma_attributes_2 att : types.dogma_attributes) {
			R_get_dogma_attributes_attribute_id dogattr = ESIStatic.INSTANCE.get_dogma_attributes(att.attribute_id, null);
			System.err.println(dogattr.name + " " + att.value);
		}
	}

}
