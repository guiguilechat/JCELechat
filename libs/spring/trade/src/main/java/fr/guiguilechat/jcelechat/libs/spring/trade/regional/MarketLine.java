package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_markets_region_id_orders_range;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
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
		@Index(columnList = "solarSystemId"),
		@Index(columnList = "typeId, isBuyOrder")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketLine extends AFetchedListElement<MarketLine, MarketRegion> implements Serializable {


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

	private int solarSystemId;

	private int typeId;

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
		setSolarSystemId(order.system_id);
		setTypeId(order.type_id);
		setVolumeRemain(order.volume_remain);
		setVolumeTotal(order.volume_total);
		return this;
	}

	public static MarketLine of(R_get_markets_region_id_orders order, MarketRegion region) {
		MarketLine ret = new MarketLine().update(order);
		ret.setFetchResource(region);
		return ret;
	}

	public static final String CSV_SEP = ",";

	public static final String CSV_HEADER = Stream.of(
			"id",
			"duration",
			"is_buy_order",
			"issued",
			"location_id",
			"min_volume",
			"order_id",
			"price",
			"range",
			"solar_system_id",
			"type_id",
			"volume_remain",
			"volume_total",
			"fetch_resource_id"
			//
			)
			.collect(Collectors.joining(CSV_SEP));


	public String csv() {
		return Stream.of(
				Long.toString(getId()),
				Integer.toString(duration),
				Boolean.toString(isBuyOrder),
				convertPGInstant(issued),
				Long.toString(locationId),
				Integer.toString(minVolume),
				Long.toString(orderId),
				Double.toString(price),
				range.name(),
				Integer.toString(solarSystemId),
				Integer.toString(typeId),
				Integer.toString(volumeRemain),
				Integer.toString(volumeTotal),
				Integer.toString(getFetchResource().getId())
				//
				).collect(Collectors.joining(CSV_SEP));
	}

	private static final DateTimeFormatter PG_INSTANT_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");

	protected static String convertPGInstant(Instant source) {
		return PG_INSTANT_FORMATER.format(source.atOffset(ZoneOffset.UTC));
	}
}
