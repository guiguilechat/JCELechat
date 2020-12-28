package fr.guiguilechat.jcelechat.programs.showattributes.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.programs.showattributes.TypeData;

public abstract class TurretDPS implements TypeData {

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		if (// https://esi.evetech.net/latest/dogma/effects/10
				effectId2effect.containsKey(10)
				// https://esi.evetech.net/latest/dogma/effects/6995
				|| effectId2effect.containsKey(6995)) {
			var damage = turretDamage(attIdToValue);
			var delay = turretDelay(attIdToValue);
			var mult = turretDamageMult(attIdToValue);
			// System.err.println("type " + type.name + " damage=" + damage + "
			// delay=" + delay + " mult=" + mult);
			if (damage == 0 || mult == 0) {
				return null;
			}
			return format(1000 * damage * mult / delay);
		} else {
			// System.err.println("type " + type.name + " has no turret effect");
			return null;
		}
	}

	@Override
	public String unit() {
		return "HP/s";
	}

	public abstract double turretDamage(Map<Integer, Double> attIdToValue);

	public static final TurretDPS EM = new TurretDPS() {

		@Override
		public String name() {
			return "Tur Em DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretEmDamage(attIdToValue);
		}
	};

	public static final TurretDPS TH = new TurretDPS() {

		@Override
		public String name() {
			return "Tur Th DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretThDamage(attIdToValue);
		}
	};

	public static final TurretDPS KI = new TurretDPS() {

		@Override
		public String name() {
			return "Tur Ki DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretKiDamage(attIdToValue);
		}
	};

	public static final TurretDPS EX = new TurretDPS() {

		@Override
		public String name() {
			return "Tur Ex DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretExDamage(attIdToValue);
		}
	};

}
