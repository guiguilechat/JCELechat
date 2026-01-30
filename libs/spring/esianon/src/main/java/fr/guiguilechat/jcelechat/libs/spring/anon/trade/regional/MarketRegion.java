package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiTradeMarketRegion")
@Table(name = "esi_trade_market_region", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarketRegion extends AFetchedList<Integer, R_get_markets_region_id_orders, MarketLine> {

	@OneToOne
	private Region region;

}
