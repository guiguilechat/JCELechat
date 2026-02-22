package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * common structure for MarketLine and the temp table to inherit.
 * this allows to define the temp table with same structure as the main table.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class CommonMarketLine {

	//
	// fields retrieved from ESI
	//

	/**
	 * retrieved from the order_id field
	 */
	@Id
	private long id;

	/**
	 * TTL (in days) of the order after an update/creation
	 */
	private int duration;

	/**
	 * true for buy orders, false for sell orders
	 */
	private boolean isBuyOrder;

	/**
	 * actually last modification by its owner (creation, price update)
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
	 * price number
	 */
	private double price;

	/**
	 * range string
	 */
	private String range;

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

	//
	// fetching information
	//

	/**
	 * the region in which that order was fetched
	 */
	@ColumnDefault("0")
	private int regionId;

	/**
	 * last-modified header of the response when fetching the region orders.
	 */
	private Instant lastModified;

	/**
	 * when fetching orders, the DB query can return completed or immediate ones.
	 */

	public void update(R_get_markets_region_id_orders order, int regionId, Instant lastModified) {
		setDuration(order.duration);
		setId(order.order_id);
		setBuyOrder(order.is_buy_order);
		setIssued(ESIDateTools.fieldInstant(order.issued));
		setLastModified(lastModified);
		setLocationId(order.location_id);
		setMinVolume(order.min_volume);
		setPrice(order.price);
		setRange(order.range.toString());
		setRegionId(regionId);
		setSolarSystemId(order.system_id);
		setTypeId(order.type_id);
		setVolumeRemain(order.volume_remain);
		setVolumeTotal(order.volume_total);
	}

	public static final String CSV_SEP = ",";

	public static final String CSV_HEADER =
			Stream.of(
					"id",
					"duration",
					"is_buy_order",
					"issued",
					"last_modified",
					"location_id",
					"min_volume",
					"price",
					"range",
					"region_id",
					"solar_system_id",
					"type_id",
					"volume_remain",
					"volume_total"
			//
			)
					.collect(Collectors.joining(CSV_SEP));

	/**
	 * convert to csv to send through PG copy function
	 *
	 * @return
	 */
	public String csv() {
		return Stream.of(
				Long.toString(getId()),
				Integer.toString(getDuration()),
				Boolean.toString(isBuyOrder()),
				convertPGInstant(getIssued()),
				convertPGInstant(getLastModified()),
				Long.toString(getLocationId()),
				Integer.toString(getMinVolume()),
				Double.toString(getPrice()),
				getRange().toString(),
				Integer.toString(getRegionId()),
				Integer.toString(getSolarSystemId()),
				Integer.toString(getTypeId()),
				Integer.toString(getVolumeRemain()),
				Integer.toString(getVolumeTotal())
		//
		).collect(Collectors.joining(CSV_SEP));
	}

	private static final DateTimeFormatter PG_INSTANT_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");

	protected static String convertPGInstant(Instant source) {
		return PG_INSTANT_FORMATER.format(source.atOffset(ZoneOffset.UTC));
	}

}
