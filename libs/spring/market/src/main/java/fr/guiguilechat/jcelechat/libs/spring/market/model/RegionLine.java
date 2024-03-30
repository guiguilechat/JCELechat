package fr.guiguilechat.jcelechat.libs.spring.market.model;

import java.io.Serializable;
import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_markets_region_id_orders_range;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "EsiMarketRegionLine")
@Table(name = "esi_market_regionline", indexes = {
		@Index(columnList = "region_region_id"),
		@Index(columnList = "locationId"),
		@Index(columnList = "typeId, isBuyOrder")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class RegionLine implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private ObservedRegion region;

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
	 * The solar system this order was placed
	 */
	private int systemId;

	/**
	 * type_id integer
	 */
	private int typeId;

	/**
	 * volume_remain integer
	 */
	private int volumeRemain;

	/**
	 * volume_total integer
	 */
	private int volumeTotal;

	public static RegionLine of(ObservedRegion region, R_get_markets_region_id_orders line) {
		return builder()
				.region(region)
				.duration(line.duration)
				.isBuyOrder(line.is_buy_order)
				.issued(ESITools.fieldInstant(line.issued))
				.locationId(line.location_id)
				.minVolume(line.min_volume)
				.orderId(line.order_id)
				.price(line.price)
				.range(line.range)
				.systemId(line.system_id)
				.typeId(line.type_id)
				.volumeRemain(line.volume_remain)
				.volumeTotal(line.volume_total)
				.build();
	}

	public boolean isValid() {
		return volumeRemain != 0 && duration != 0;
	}

}
