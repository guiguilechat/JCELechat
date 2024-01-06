package fr.guiguilechat.jcelechat.programs.showattributes.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.programs.showattributes.TypeData;

public abstract class TurretMaxDPS implements TypeData {

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
			var maxMultBonus = turretDamageMaxBonusMult(attIdToValue);
			// System.err.println("type " + type.name + " damage=" + damage + "
			// delay=" + delay + " mult=" + mult+" maxmultBonus="+maxMultBonus);
			if (damage == 0 || mult == 0 || maxMultBonus == 0) {
				return null;
			}
			return format(1000 * damage * mult * maxMultBonus / delay);

		} else {
			return null;
		}
	}

	@Override
	public String unit() {
		return "HP/s";
	}

	public abstract double turretDamage(Map<Integer, Double> attIdToValue);

	public static final TurretMaxDPS EM = new TurretMaxDPS() {

		@Override
		public String name() {
			return "Tur Max Em DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretEmDamage(attIdToValue);
		}
	};

	public static final TurretMaxDPS TH = new TurretMaxDPS() {

		@Override
		public String name() {
			return "Tur Max Th DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretThDamage(attIdToValue);
		}
	};

	public static final TurretMaxDPS KI = new TurretMaxDPS() {

		@Override
		public String name() {
			return "Tur Max Ki DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretKiDamage(attIdToValue);
		}
	};

	public static final TurretMaxDPS EX = new TurretMaxDPS() {

		@Override
		public String name() {
			return "Tur Max Ex DPS";
		}

		@Override
		public double turretDamage(Map<Integer, Double> attIdToValue) {
			return turretExDamage(attIdToValue);
		}
	};

}
