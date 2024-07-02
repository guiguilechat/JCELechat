package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import static fr.lelouet.tools.holders.interfaces.ObjHolder.reduce;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.impl.numbers.DoubleHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupedPrices {

	private final RegionalMarket[] markets;

	private final BiFunction<RegionalMarket, Integer, ListHolder<R_get_markets_region_id_orders>> marketGetter;

	private final Function<List<? extends List<R_get_markets_region_id_orders>>, Double> reducer;

	private final HashMap<Integer, DoubleHolder> cache = new HashMap<>();

	public DoubleHolder get(int typeId) {
		DoubleHolder ret = cache.get(typeId);
		if (ret == null) {
			synchronized (cache) {
				ret = cache.get(typeId);
				if (ret == null) {
					List<ListHolder<R_get_markets_region_id_orders>> list = Stream.of(markets)
					    .map(m -> marketGetter.apply(m, typeId)).toList();
					ret = reduce(list, DoubleHolderImpl::new, reducer);
					cache.put(typeId, ret);
				}
			}
		}
		return ret;
	}

}
