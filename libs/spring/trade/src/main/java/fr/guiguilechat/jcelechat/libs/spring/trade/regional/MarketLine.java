package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.io.Serializable;
import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_markets_region_id_orders_range;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity(name = "EsiTradeMarketLine")
@Table(name = "esi_trade_market_line", indexes = {
    @Index(columnList = "fetch_resource_id"),
		@Index(columnList = "locationId"),
		@Index(columnList = "typeId, isBuyOrder")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketLine extends AFetchedListElement<MarketLine, MarketRegion> implements Serializable {

	/**
	 * The solar system this order was placed
	 */
	@ManyToOne
	private SolarSystem solarSystem;

	@ManyToOne
	private Type type;


	/**
	 * duration integer
	 */
	private int duration;

	/**
	 * is_buy_order boolean
	 */
	private boolean isBuyOrder;

	/**
	 * conversion of the order's issued string to an instant.
	 */
	private Instant issued;

	/**
	 * location_id integer
	 */
	private long locationId;

	/**
	 * min_volume integer
	 */
	private int minVolume;

	/**
	 * order_id integer
	 */
	private long orderId;

	/**
	 * price number
	 */
	private double price;

	/**
	 * range string
	 */
	@Enumerated(EnumType.STRING)
	private get_markets_region_id_orders_range range;

	/**
	 * volume_remain integer
	 */
	private int volumeRemain;

	/**
	 * volume_total integer
	 */
	private int volumeTotal;

	public boolean isValid() {
		return volumeRemain != 0 && duration != 0;
	}

	public MarketLine update(R_get_markets_region_id_orders order) {
		setDuration(order.duration);
		setBuyOrder(order.is_buy_order);
		setIssued(ESITools.fieldInstant(order.issued));
		setLocationId(order.location_id);
		setMinVolume(order.min_volume);
		setOrderId(order.order_id);
		setPrice(order.price);
		setRange(order.range);
		setVolumeRemain(order.volume_remain);
		setVolumeTotal(order.volume_total);
		return this;
	}

	public static MarketLine of(R_get_markets_region_id_orders order, MarketRegion region, SolarSystem solarSystem,
	    Type type) {
		MarketLine ret = new MarketLine().update(order);
		ret.setFetchResource(region);
		ret.setSolarSystem(solarSystem);
		ret.setType(type);
		return ret;
	}

}
