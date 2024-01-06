package fr.guiguilechat.jcelechat.programs.showattributes.typeData;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.programs.showattributes.TypeData;

public class TrackingEvasion implements TypeData {

	public static TrackingEvasion INS = new TrackingEvasion();

	protected TrackingEvasion() {
	}

	@Override
	public String apply(R_get_universe_types_type_id type, Map<Integer, Double> attIdToValue,
			Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect) {
		var sig = signatureRadius(attIdToValue);
		var range = orbitRange(attIdToValue);
		if (range == 0) {
			range = turretOptiRange(attIdToValue);
		}
		var velocity = orbitVelocity(attIdToValue);
		// System.err.println("type " + type.name + " sig=" + sig + " range=" +
		// range + " velo=" + velocity);
		return format(velocity * 40000 / sig / range);
	}

	@Override
	public String name() {
		return "tracking evasion";
	}

	@Override
	public String unit() {
		return "rad/s";
	}

}
