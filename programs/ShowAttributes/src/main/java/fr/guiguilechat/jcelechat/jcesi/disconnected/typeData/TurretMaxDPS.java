package fr.guiguilechat.jcelechat.jcesi.disconnected.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.TypeData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public abstract class TurretMaxDPS implements TypeData {

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		if (effectId2effect.containsKey(10)) {
			var damage = turretDamage(attIdToValue);
			var delay = turretDelay(attIdToValue);
			var mult = turretDamageMult(attIdToValue);
			var maxMultBonus = turretDamageMaxBonusMult(attIdToValue);
			// System.err.println("type " + type.name + " damage=" + damage + "
			// delay=" + delay + " mult=" + mult+" maxmultBonus="+maxMultBonus);
			if (damage == 0 || mult == 0 || maxMultBonus == 0) {
				return "0";
			}
			return formater().format(1000 * damage * mult * maxMultBonus / delay);

		} else {
			return "0";
		}
	}

	public abstract double turretDamage(Map<Integer, Double> attIdToValue);

	public static final TurretMaxDPS EM = new TurretMaxDPS() {

		@Override
		public String name() {
			return "TurMaxEmDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretEmDamage(attIdToValue);
		}
	};

	public static final TurretMaxDPS TH = new TurretMaxDPS() {

		@Override
		public String name() {
			return "TurMaxThDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretThDamage(attIdToValue);
		}
	};

	public static final TurretMaxDPS KI = new TurretMaxDPS() {

		@Override
		public String name() {
			return "TurMaxKiDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretKiDamage(attIdToValue);
		}
	};

	public static final TurretMaxDPS EX = new TurretMaxDPS() {

		@Override
		public String name() {
			return "TurMaxExDPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretExDamage(attIdToValue);
		}
	};

}
