package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.Corporation;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Market {

	private final Corporation corporation;

	public Market(Corporation corporation) {
		this.corporation = corporation;
	}

	public int getId() {
		return corporation.getId();
	}

	public ESIAccount getAcc() {
		return corporation.con;
	}

	private ObsMapHolder<Long, R_get_corporations_corporation_id_orders> cachedOrders = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_orders> getOrders() {
		if (cachedOrders == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedOrders == null) {
					cachedOrders = getAcc().raw.cache.corporations.orders(getId()).toMap(o -> o.order_id);
				}
			});
		}
		return cachedOrders;
	}

}
