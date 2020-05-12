package fr.guiguilechat.jcelechat.jcesi.disconnected.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.TypeData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public class Name implements TypeData {

	public static Name INS = new Name();

	protected Name() {
	}

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		return type.name;
	}

	@Override
	public String name() {
		return "name";
	}

}
