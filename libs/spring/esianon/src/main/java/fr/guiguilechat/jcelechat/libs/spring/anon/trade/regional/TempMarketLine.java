package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.time.Instant;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "EsiTradeMarketLineTemp")
@Table(name = "esi_trade_market_line_temp", indexes = {
		@Index(columnList = "regionId, id"), // to merge
})
@Getter
@Setter
public class TempMarketLine extends CommonMarketLine {

	public static TempMarketLine of(R_get_markets_region_id_orders order, int regionId, Instant lastModified) {
		var ret = new TempMarketLine();
		ret.update(order, regionId, lastModified);
		return ret;
	}

}
