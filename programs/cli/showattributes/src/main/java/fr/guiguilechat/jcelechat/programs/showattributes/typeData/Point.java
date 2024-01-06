package fr.guiguilechat.jcelechat.programs.showattributes.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.programs.showattributes.TypeData;

public abstract class Point implements TypeData {

	protected Point() {
	}

	@Override
	public String unit() {
		return "m";
	}

	public static final Point DISRUPT = new Point() {

		@Override
		public String name() {
			return "warp disruptor";
		}

		@Override
		public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
				Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
			if (effectId2effect.containsKey(563)
					// https://esi.evetech.net/latest/dogma/effects/563
					) {
				return format(pointRange(attIdToValue));
			}
			return null;
		}

	};

	public static final Point SCRAM = new Point() {

		@Override
		public String name() {
			return "warp scrambler";
		}

		@Override
		public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
				Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
			if (effectId2effect.containsKey(5928)
					// https://esi.evetech.net/latest/dogma/effects/5928
					|| effectId2effect.containsKey(6745)
					// https://esi.evetech.net/latest/dogma/effects/6745
					) {
				// https://esi.evetech.net/latest/dogma/effects/5928
				return format(pointRange(attIdToValue));
			}
			return null;
		}

	};

}
