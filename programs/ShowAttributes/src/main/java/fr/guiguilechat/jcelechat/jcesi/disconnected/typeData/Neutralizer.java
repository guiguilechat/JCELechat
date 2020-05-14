package fr.guiguilechat.jcelechat.jcesi.disconnected.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.TypeData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public class Neutralizer implements TypeData {

	public static Neutralizer INS = new Neutralizer();

	protected Neutralizer() {
	}

	@Override
	public String unit() {
		return "GJ/s";
	}

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		// https://esi.evetech.net/latest/dogma/effects/6756
		if (effectId2effect.containsKey(6756)) {
			var qtty = neutralizerAmount(attIdToValue);
			var delay = neutralizerDelay(attIdToValue);
			// System.err.println("type " + type.name + " qtty=" + qtty + " delay=" +
			// delay);
			return format(1000 * qtty / delay);
		}
		return null;
	}

	@Override
	public String name() {
		return "Neutralizer";
	}

}
