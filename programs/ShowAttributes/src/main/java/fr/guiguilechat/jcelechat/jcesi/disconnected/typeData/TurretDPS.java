package fr.guiguilechat.jcelechat.jcesi.disconnected.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.TypeData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public abstract class TurretDPS implements TypeData {

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		if (effectId2effect.containsKey(10)) {
			var damage = turretDamage(attIdToValue);
			var delay = turretDelay(attIdToValue);
			var mult = turretDamageMult(attIdToValue);
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

	public abstract double turretDamage(Map<Integer, Double> attIdToValue);

	public static final TurretDPS EM = new TurretDPS() {

		@Override
		public String name() {
			return "TurEmDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretEmDamage(attIdToValue);
		}
	};

	public static final TurretDPS TH = new TurretDPS() {

		@Override
		public String name() {
			return "TurThDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretThDamage(attIdToValue);
		}
	};

	public static final TurretDPS KI = new TurretDPS() {

		@Override
		public String name() {
			return "TurKiDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretKiDamage(attIdToValue);
		}
	};

	public static final TurretDPS EX = new TurretDPS() {

		@Override
		public String name() {
			return "TurExDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretExDamage(attIdToValue);
		}
	};

}
