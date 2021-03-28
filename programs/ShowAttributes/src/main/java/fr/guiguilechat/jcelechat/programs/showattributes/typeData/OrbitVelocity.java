package fr.guiguilechat.jcelechat.programs.showattributes.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.programs.showattributes.TypeData;

public class OrbitVelocity implements TypeData {

	public static OrbitVelocity INS = new OrbitVelocity();

	protected OrbitVelocity() {
	}

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		return format(orbitVelocity(attIdToValue));
	}

	@Override
	public String name() {
		return "orbit velocity";
	}

	@Override
	public String unit() {
		return "m/s";
	};



}
