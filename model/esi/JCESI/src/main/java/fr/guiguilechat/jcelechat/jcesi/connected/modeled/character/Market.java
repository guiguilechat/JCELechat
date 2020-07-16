package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIModel;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.filter;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_markets_region_id_orders_range;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsCollectionHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Market {

	private final ESIAccount con;

	public Market(ESIAccount con) {
		this.con = con;
	}

	//
	// market orders of the character
	//

	private ObsMapHolder<Integer, Long> cachedSOs = null;

	private ObsMapHolder<Integer, Long> cachedBOs = null;

	private void makeBOSOs() {
		if (cachedSOs == null || cachedBOs == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedSOs == null || cachedBOs == null) {
					ObservableMap<Integer, Long> underlyingsos = FXCollections.observableMap(new HashMap<>());
					ObsMapHolderImpl<Integer, Long> newmarketSOs = new ObsMapHolderImpl<>(underlyingsos);
					ObservableMap<Integer, Long> underlyingbos = FXCollections.observableMap(new HashMap<>());
					ObsMapHolderImpl<Integer, Long> newmarketBOs = new ObsMapHolderImpl<>(underlyingbos);
					getOrders().follow((map) -> {
						HashMap<Integer, Long> newMapsos = new HashMap<>();
						HashMap<Integer, Long> newMapbos = new HashMap<>();
						for (R_get_characters_character_id_orders v : map) {
							if (!v.is_buy_order) {
								newMapsos.put(v.type_id, newMapsos.getOrDefault(v.type_id, 0l) + v.volume_remain);
							} else {
								newMapbos.put(v.type_id, newMapbos.getOrDefault(v.type_id, 0l) + v.volume_remain);
							}
						}
						synchronized (underlyingsos) {
							underlyingsos.keySet().retainAll(newMapsos.keySet());
							underlyingsos.putAll(newMapsos);
						}
						newmarketSOs.dataReceived();
						synchronized (underlyingbos) {
							underlyingbos.keySet().retainAll(newMapbos.keySet());
							underlyingbos.putAll(newMapbos);
						}
						newmarketBOs.dataReceived();
					});
					cachedSOs = newmarketSOs;
					cachedBOs = newmarketBOs;
				}
			});
		}
	}

	public ObsMapHolder<Integer, Long> getSOs() {
		makeBOSOs();
		return cachedSOs;
	}

	public ObsMapHolder<Integer, Long> getBOs() {
		makeBOSOs();
		return cachedBOs;
	}

	public ObsListHolder<R_get_characters_character_id_orders> getOrders() {
		return con.raw.cache.characters.orders(con.characterId());
	}

	private ObsSetHolder<Long> cachedOrderIds = null;

	public ObsSetHolder<Long> getOrderIds() {
		if (cachedOrderIds == null) {
			ObsListHolder<R_get_characters_character_id_orders> orders = getOrders();
			LockWatchDog.BARKER.syncExecute(orders, () -> {
				if (cachedOrderIds == null) {
					cachedOrderIds = orders.mapItems(order -> order.order_id).distinct();
				}
			});
		}
		return cachedOrderIds;
	}

	//
	// public market orders on structures
	//

	private final HashMap<Integer, ObsCollectionHolder<R_get_markets_region_id_orders, ?, ?>> cachedRegionalPublicOrders = new HashMap<>();

	public ObsCollectionHolder<R_get_markets_region_id_orders, ?, ?> getRegionalPublicOrders(int regionId) {
		ObsCollectionHolder<R_get_markets_region_id_orders, ?, ?> ret = cachedRegionalPublicOrders.get(regionId);
		if (ret == null) {
			synchronized (cachedRegionalPublicOrders) {
				ret = cachedRegionalPublicOrders.get(regionId);
				if (ret == null) {
					Set<Integer> allowedConstels = IntStream
							.of(ESIModel.INSTANCE.universe.cache.regions(regionId).get().constellations).boxed()
							.collect(Collectors.toSet());

					ObsSetHolder<Long> structIdInRegion = con.universe.publicStructures(filter.market)
							.filter(null,
									stru -> allowedConstels
									.contains(ESIModel.INSTANCE.universe.cache.systems(stru.solar_system_id).get().constellation_id))
							.keys();
					ObsCollectionHolder<R_get_markets_region_id_orders, ?, ?> publicStructureOrders = structIdInRegion
							.flatten(structid -> {
								int system_id = con.universe.location(structid).system().system_id;
								return con.raw.cache.markets.structures(structid)
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
