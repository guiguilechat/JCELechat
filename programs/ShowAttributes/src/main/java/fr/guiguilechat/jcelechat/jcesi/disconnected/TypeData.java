package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public interface TypeData {

	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect);

	public String name();

	public String unit();

	public static final DecimalFormat nf = new DecimalFormat("#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	public default String format(Number n) {
		return nf.format(n);
	}

	//
	// tank
	//

	public default double shieldHP(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(263, 0.0);
	}

	public default double hullHP(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(9, 0.0);
	}

	public default double armorHP(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(265, 0.0);
	}

	public default double shieldEmResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(271, 1.0);
	}

	public default double shieldThResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(274, 1.0);
	}

	public default double shieldKiResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(273, 1.0);
	}

	public default double shieldExResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(272, 1.0);
	}

	public default double armorEmResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(267, 1.0);
	}

	public default double armorThResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(270, 1.0);
	}

	public default double armorKiResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(269, 1.0);
	}

	public default double armorExResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(268, 1.0);
	}

	public default double hullEmResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(113, 1.0);
	}

	public default double hullThResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(110, 1.0);
	}

	public default double hullKiResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(109, 1.0);
	}

	public default double hullExResonnance(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(111, 1.0);
	}

	//
	// navigation
	//

	/**
	 *
	 * @param attIdToValue
	 * @return the signature radius, in m
	 */
	public default double signatureRadius(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(552, 1.0);
	}

	/**
	 *
	 * @param attIdToValue
	 * @return the orbit velocity, in m/s
	 */
	public default double orbitVelocity(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/508
		// https://esi.evetech.net/v1/dogma/attributes/37
		return Math.max(attIdToValue.getOrDefault(508, 0.0), attIdToValue.getOrDefault(37, 0.0));
	}

	/**
	 *
	 * @param attIdToValue
	 * @return the orbit range, in m
	 */
	public default double orbitRange(Map<Integer, Double> attIdToValue) {
		if (attIdToValue.containsKey(416)) {
			return attIdToValue.get(416);
		}
		if (attIdToValue.containsKey(2786)) {
			return attIdToValue.get(2786);
		}
		return 0.0;
	}

	//
	// turret
	//

	public default double turretDamageMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(64, 1.0);
	}

	public default double turretDamageMaxBonusMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(2734, 0.0);
	}

	/**
	 *
	 * @param attIdToValue
	 * @return the delay between shots in ms
	 */
	public default double turretDelay(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(51, 0.0);
	}

	public default double turretEmDamage(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(114, 0.0);
	}

	public default double turretThDamage(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(118, 0.0);
	}

	public default double turretKiDamage(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(117, 0.0);
	}

	public default double turretExDamage(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(116, 0.0);
	}

	public default double turretOptiRange(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/54
		return attIdToValue.getOrDefault(54, 0.0);
	}

	//
	// missile
	//

	public default double missileDamageMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(212, 1.0);
	}

	/**
	 *
	 * @param attIdToValue
	 * @return the delay between missiles, in ms
	 */
	public default double missileDelay(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(506, 20.0);
	}

	public default int missileId(Map<Integer, Double> attIdToValue) {
		return (int) (double) attIdToValue.getOrDefault(507, 0.0);
	}

	public default double missileVelocityMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(645, 1.0);
	}

	public default double missileTimeMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(646, 1.0);
	}

	public default double missileRadiusMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(858, 1.0);
	}

	public default double missileExVeloMult(Map<Integer, Double> attIdToValue) {
		return attIdToValue.getOrDefault(859, 1.0);
	}

	public default double missileAttribute(int missileId, int attributeId, double defaultValue) {
		R_get_universe_types_type_id type = ESIStatic.INSTANCE.cache.universe.types(missileId).get();
		for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : type.dogma_attributes) {
			if (att.attribute_id == attributeId) {
				return att.value;
			}
		}
		return defaultValue;
	}

	public default double missileEmDamage(Map<Integer, Double> attIdToValue) {
		int missId = missileId(attIdToValue);
		if (missId != 0) {
			return missileAttribute(missId, 114, 0.0);
		}
		return 0.0;
	}

	public default double missileThDamage(Map<Integer, Double> attIdToValue) {
		int missId = missileId(attIdToValue);
		if (missId != 0) {
			return missileAttribute(missId, 118, 0.0);
		}
		return 0.0;
	}

	public default double missileKiDamage(Map<Integer, Double> attIdToValue) {
		int missId = missileId(attIdToValue);
		if (missId != 0) {
			return missileAttribute(missId, 117, 0.0);
		}
		return 0.0;
	}

	public default double missileExDamage(Map<Integer, Double> attIdToValue) {
		int missId = missileId(attIdToValue);
		if (missId != 0) {
			return missileAttribute(missId, 116, 0.0);
		}
		return 0.0;
	}

	//
	// neutralizer
	//

	/**
	 *
	 * @param attIdToValue
	 * @return the amount remove per cycle, in GJ
	 */
	public default double neutralizerAmount(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/97
		return attIdToValue.getOrDefault(97, 0.0);
	}

	/**
	 *
	 * @param attIdToValue
	 * @return the neutralizer delay, in ms
	 */
	public default double neutralizerDelay(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/942
		// https://esi.evetech.net/v1/dogma/attributes/2519
		return Math.max(attIdToValue.getOrDefault(2519, 0.0), attIdToValue.getOrDefault(942, 0.0));
	}

	public default double neutralizerOptimal(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/98
		// https://esi.evetech.net/v1/dogma/attributes/2520
		return Math.max(attIdToValue.getOrDefault(2520, 0.0), attIdToValue.getOrDefault(98, 0.0));
	}

	public default double NeutralizerFallOff(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/2521
		return attIdToValue.getOrDefault(2521, 0.0);
	}

	//
	// point or scramble
	//

	public default double pointRange(Map<Integer, Double> attIdToValue) {
		// https://esi.evetech.net/v1/dogma/attributes/103
		// https://esi.evetech.net/v1/dogma/attributes/2507
		return Math.max(attIdToValue.getOrDefault(103, 0.0), attIdToValue.getOrDefault(2507, 0.0));
	}

}
