package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;

public class ShowAttributes {

	public static void main(String[] args) {
		R_get_universe_types_type_id types = ESIStatic.INSTANCE.get_universe_types(34783, null).getOK();
		Stream.of(types.dogma_attributes).sorted((a1, a2) -> a1.attribute_id - a2.attribute_id).parallel().map(att -> {
			R_get_dogma_attributes_attribute_id dogattr = ESIStatic.INSTANCE.get_dogma_attributes(att.attribute_id, null)
					.getOK();
			return dogattr.name + " (" + att.attribute_id + ") " + att.value;
		}).forEachOrdered(System.err::println);
	}

}
