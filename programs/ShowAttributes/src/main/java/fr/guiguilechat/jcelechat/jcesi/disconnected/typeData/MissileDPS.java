package fr.guiguilechat.jcelechat.jcesi.disconnected.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.TypeData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public abstract class MissileDPS implements TypeData {

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		if (effectId2effect.containsKey(10)) {
			var damage = missileDamage(attIdToValue);
			var delay = missileDelay(attIdToValue);
			var mult = missileDamageMult(attIdToValue);
			// System.err.println("type " + type.name + " damage=" + damage + "
			// delay=" + delay + " mult=" + mult+" maxmultBonus="+maxMultBonus);
			if (damage == 0 || mult == 0) {
				return "0";
			}
			return formater().format(1000 * damage * mult / delay);
		} else {
			return "0";
		}
	}

	public abstract double missileDamage(Map<Integer, Double> attIdToValue);

	public static final MissileDPS EM = new MissileDPS() {

		@Override
		public String name() {
			return "Miss Em DPS";
		}

		@Override
		public double missileDamage(Map<Integer, Double> attIdToValue) {
			return missileEmDamage(attIdToValue);
		}
	};

	public static final MissileDPS TH = new MissileDPS() {

		@Override
		public String name() {
			return "Miss Th DPS";
		}

		@Override
		public double missileDamage(Map<Integer, Double> attIdToValue) {
			return missileThDamage(attIdToValue);
		}
	};

	public static final MissileDPS KI = new MissileDPS() {

		@Override
		public String name() {
			return "Miss Ki DPS";
		}

		@Override
		public double missileDamage(Map<Integer, Double> attIdToValue) {
			return missileKiDamage(attIdToValue);
		}
	};

	public static final MissileDPS EX = new MissileDPS() {

		@Override
		public String name() {
			return "Miss Ex DPS";
		}

		@Override
		public double missileDamage(Map<Integer, Double> attIdToValue) {
			return missileExDamage(attIdToValue);
		}
	};

}
