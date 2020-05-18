package fr.guiguilechat.jcelechat.jcesi.disconnected.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.TypeData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public abstract class Neutralizer implements TypeData {

	protected Neutralizer() {
	}


	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		// https://esi.evetech.net/latest/dogma/effects/6756
		if (effectId2effect.containsKey(6756)
				// https://esi.evetech.net/latest/dogma/effects/6756
				|| effectId2effect.containsKey(6691)
				// https://esi.evetech.net/latest/dogma/effects/6691
				) {
			return format(qtty(attIdToValue));
		}
		// System.err.println("type " + type.name + " has no neutralizer");
		return null;
	}

	protected abstract double qtty(Map<Integer, Double> attIdToValue);

	public static Neutralizer STR = new Neutralizer() {

		@Override
		public String unit() {
			return "GJ/s";
		}

		@Override
		public String name() {
			return "Neut Strength";
		}

		@Override
		protected double qtty(Map<Integer, Double> attIdToValue) {
			var qtty = neutralizerAmount(attIdToValue);
			var delay = neutralizerDelay(attIdToValue);
			return 1000 * qtty / delay;
		}

	};

	public static Neutralizer OPT = new Neutralizer() {

		@Override
		public String unit() {
			return "m";
		}

		@Override
		public String name() {
			return "Neut Optimal";
		}

		@Override
		protected double qtty(Map<Integer, Double> attIdToValue) {
			return neutralizerOptimal(attIdToValue);
		}

	};

	public static Neutralizer FALL = new Neutralizer() {

		@Override
		public String unit() {
			return "m";
		}

		@Override
		public String name() {
			return "Neut Falloff";
		}

		@Override
		protected double qtty(Map<Integer, Double> attIdToValue) {
			return NeutralizerFallOff(attIdToValue);
		}

	};

}
