package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.filter;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_markets_region_id_orders_range;
import fr.lelouet.collectionholders.interfaces.collections.ObsCollectionHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import lombok.Getter;

public class Market {

	private final ESIAccount con;

	public Market(ESIAccount con) {
		this.con = con;
	}

	//
	// market orders of the character
	//

	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_characters_character_id_orders> orders = con.connection().cache().characters
	.orders(con.characterId()).toMap(o -> o.order_id);


	@Getter(lazy = true)
	private final ObsMapHolder<Integer, Long> sOs = makeSOs();

	protected ObsMapHolder<Integer, Long> makeSOs() {
		return getOrders().values().filter(order -> !order.is_buy_order).mapMap(l -> l.stream()
				.collect(Collectors.groupingBy(or -> or.type_id, Collectors.summingLong(order -> order.volume_remain))));
	}

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, Long> bOs = makeBOs();

	protected ObsMapHolder<Integer, Long> makeBOs() {
		return getOrders().values().filter(order -> order.is_buy_order).mapMap(l -> l.stream()
				.collect(Collectors.groupingBy(or -> or.type_id, Collectors.summingLong(order -> order.volume_remain))));
	}

	//
	// public market orders on structures
	//

	private final HashMap<Integer, ObsCollectionHolder<R_get_markets_region_id_orders, ?>> cachedRegionalPublicOrders = new HashMap<>();

	public ObsCollectionHolder<R_get_markets_region_id_orders, ?> getRegionalPublicOrders(int regionId) {
		ObsCollectionHolder<R_get_markets_region_id_orders, ?> ret = cachedRegionalPublicOrders.get(regionId);
		if (ret == null) {
			synchronized (cachedRegionalPublicOrders) {
				ret = cachedRegionalPublicOrders.get(regionId);
				if (ret == null) {
					Set<Integer> allowedConstels = IntStream
							.of(ESIAccess.INSTANCE.universe.cache().regions(regionId).get().constellations).boxed()
							.collect(Collectors.toSet());

					ObsSetHolder<Long> structIdInRegion = con.universe.publicStructures(filter.market)
							.filter(null, stru -> allowedConstels
									.contains(ESIAccess.INSTANCE.universe.cache().systems(stru.solar_system_id).get().constellation_id))
							.keys();
					ObsCollectionHolder<R_get_markets_region_id_orders, ?> publicStructureOrders = structIdInRegion
							.flatten(structid -> {
								int system_id = con.universe.location(structid).system().system_id;
								return con.connection().cache().markets.structures(structid)
										.mapItems(orderstruct -> convertStructOrder(orderstruct, system_id));
							});
					ret = publicStructureOrders;
					cachedRegionalPublicOrders.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	protected R_get_markets_region_id_orders convertStructOrder(R_get_markets_structures_structure_id order,
			int system_id) {
		R_get_markets_region_id_orders ret = new R_get_markets_region_id_orders();
		ret.duration = order.duration;
		ret.is_buy_order = order.is_buy_order;
		ret.issued = order.issued;
		ret.location_id = order.location_id;
		ret.min_volume = order.min_volume;
		ret.order_id = order.order_id;
		ret.price = order.price;
		ret.range = get_markets_region_id_orders_range.valueOf(order.range.name());
		ret.system_id = system_id;
		ret.type_id = order.type_id;
		ret.volume_remain = order.volume_remain;
		ret.volume_total = order.volume_total;
		return ret;
	}

}
