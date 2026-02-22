package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.io.Serializable;
import java.time.Instant;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "EsiTradeMarketLine")
@Table(name = "esi_trade_market_line", indexes = {
		@Index(columnList = "regionId, id"), // find by region
		@Index(columnList = "locationId, typeId, isBuyOrder"), // find by station
		@Index(columnList = "solarSystemId, typeId, isBuyOrder"), // find by system
		@Index(columnList = "typeId, isBuyOrder") // find globally
})
public class MarketLine extends CommonMarketLine implements Serializable {

	public boolean isValid() {
		return getVolumeRemain() != 0 && getDuration() != 0;
	}

	public static MarketLine of(R_get_markets_region_id_orders order, int regionId, Instant lastModified) {
		var ret = new MarketLine();
		ret.update(order, regionId, lastModified);
		return ret;
	}
}
