package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "EsiTradeMarketRegion")
@Table(name = "esi_trade_market_region", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class MarketRegion extends RemoteNumberEntity<Integer, R_get_markets_region_id_orders[]> {

	@ColumnDefault("0")
	private int nbLines = 0;

	private Instant previousLastModified;

	@Override
	public void updateMetaOk(Instant lastModified, Instant expires, String etag) {
		setPreviousLastModified(getLastModified());
		super.updateMetaOk(lastModified, expires, etag);
	}

	// called after the updateMetaOk
	@Override
	public void update(R_get_markets_region_id_orders[] data) {
		nbLines = data == null ? 0 : data.length;
		// no data => wait additional time.
		if (nbLines == 0) {
			setExpires(getExpires().plusSeconds(10 * 60));
			log.debug("  region {} received {} lines, push back expires to {}",
					getId(),
					nbLines,
					getExpires());
		}
	}

}
