package fr.guiguilechat.jcelechat.programs.insuranceeval;

import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_insurance_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;

public class InsuranceEval {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		CacheStatic cache = ESIRawPublic.INSTANCE.cache();
		ListHolder<R_get_insurance_prices> prices = cache.insurance.prices().filter(ins -> ins.levels[0].cost > 0);
		MapHolder<Integer, R_get_insurance_prices> id2Data = prices.toMap(price -> price.type_id);
		MapHolder<Integer, Float> id2Payout0 = prices.toMap(price -> price.type_id, price -> price.levels[0].payout);
		MapHolder<Integer, R_get_universe_types_type_id> id2Type = id2Data.keys()
				.unpackItems(id -> cache.universe.types(id)).toMap(t -> t.type_id);
		Map<Integer, R_get_universe_types_type_id> types = id2Type.get();
		// for (Entry<Integer, R_get_insurance_prices> e : id2Data.entries().get())
		// {
		// R_get_insurance_prices_levels level0 = e.getValue().levels[0];
		// System.out.println(types.get(e.getKey()).name + "\t" + level0.payout /
		// level0.cost + "\t" + level0.cost
		// + "\t" + level0.payout + "\t" + level0.name);
		// }

	}

}
