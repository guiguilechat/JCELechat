package fr.guiguilechat.jcelechat.programs.showattributes.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.programs.showattributes.TypeData;

public abstract class MissileDPS implements TypeData {

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		if (// https://esi.evetech.net/latest/dogma/effects/569
				effectId2effect.containsKey(569)) {
			var damage = missileDamage(attIdToValue);
			var delay = missileDelay(attIdToValue);
			var mult = missileDamageMult(attIdToValue);
			// System.err.println("type " + type.name + " damage=" + damage + "
			// delay=" + delay + " mult=" + mult);
			if (damage == 0 || mult == 0) {
				return null;
			}
			return format(1000 * damage * mult / delay);
		} else {
			return null;
		}
	}

	@Override
	public String unit() {
		return "HP/s";
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
