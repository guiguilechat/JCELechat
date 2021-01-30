package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.Corporation;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import lombok.Getter;

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

	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_corporations_corporation_id_orders> orders = getAcc().connection()
	.cache().corporations.orders(getId()).toMap(o -> o.order_id);


}
