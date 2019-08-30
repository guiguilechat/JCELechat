package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
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
	// market orders
	//

	private ObsMapHolder<Integer, Integer> cachedSOs = null;

	private ObsMapHolder<Integer, Integer> cachedBOs = null;

	private void makeBOSOs() {
		if (cachedSOs == null || cachedBOs == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedSOs == null || cachedBOs == null) {
					ObservableMap<Integer, Integer> underlyingsos = FXCollections.observableMap(new HashMap<>());
					ObsMapHolderImpl<Integer, Integer> newmarketSOs = new ObsMapHolderImpl<>(underlyingsos);
					ObservableMap<Integer, Integer> underlyingbos = FXCollections.observableMap(new HashMap<>());
					ObsMapHolderImpl<Integer, Integer> newmarketBOs = new ObsMapHolderImpl<>(underlyingbos);
					getOrders().follow((map) -> {
						HashMap<Integer, Integer> newMapsos = new HashMap<>();
						HashMap<Integer, Integer> newMapbos = new HashMap<>();
						for (R_get_characters_character_id_orders v : map.values()) {
							if (!v.is_buy_order) {
								newMapsos.put(v.type_id, newMapsos.getOrDefault(v.type_id, 0) + v.volume_remain);
							} else {
								newMapbos.put(v.type_id, newMapbos.getOrDefault(v.type_id, 0) + v.volume_remain);
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

	public ObsMapHolder<Integer, Integer> getSOs() {
		makeBOSOs();
		return cachedSOs;
	}

	public ObsMapHolder<Integer, Integer> getBOs() {
		makeBOSOs();
		return cachedBOs;
	}

	private ObsMapHolder<Long, R_get_characters_character_id_orders> cacheOrders = null;

	public ObsMapHolder<Long, R_get_characters_character_id_orders> getOrders() {
		if (cacheOrders == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cacheOrders == null) {
					cacheOrders = ObsMapHolderImpl.toMap(con.raw.cache.characters.orders(con.characterId()), o -> o.order_id);
				}
			});
		}
		return cacheOrders;
	}

	private ObsSetHolder<Long> cachedOrderIds = null;

	public ObsSetHolder<Long> getOrderIds() {
		if (cachedOrderIds == null) {
			ObsMapHolder<Long, R_get_characters_character_id_orders> orders = getOrders();
			LockWatchDog.BARKER.syncExecute(orders, () -> {
				if (cachedOrderIds == null) {
					cachedOrderIds = orders.values().mapItems(order -> order.order_id).distinct();
				}
			});
		}
		return cachedOrderIds;
	}

}
