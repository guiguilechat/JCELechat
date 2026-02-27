package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * When an order has had its volume_remain reduced
 * <p>
 * several information of the orders are kept in that table to have faster
 * checks (no need to join)
 * </p>
 */
@Entity(name = "JcelechatTradeOrderSale")
@Table(name = "jcelechat_trade_ordersale", indexes = {
		@Index(columnList = "orderId, date"), // to find the evolution of an order
		@Index(columnList = "typeId, date"), // by type, globally
		@Index(columnList = "locationId, typeId, date"), // by type, by location
//		@Index(columnList = "solarSystemId, typeId, date"), // not really used ? So no index
		@Index(columnList = "regionId, typeId, date"),// by type, by region
})
@Getter
@Setter
@ToString
public class OrderSale {

	/** we need a 1-seq for faster "insert into select" in H2 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jcelechat_trade_ordersale")
	@SequenceGenerator(name = "jcelechat_trade_ordersale", allocationSize = 1, sequenceName = "jcelechat_trade_ordersale_seq")
	@Id
	private Long id;

	// order info, copied

	private long orderId;

	private int typeId;

	private long locationId;

	private int solarSystemId;

	private int regionId;

	// deduced from comparison

	/** <b>estimation</b> of the time the transaction happened */
	private Instant date;

	/** last-modified after the transaction */
	private Instant dateMax;

	/** last-modified before the transaction */
	private Instant dateMin;

	/** difference in quantity from the order */
	private int volume;

	/**
	 * this is the price we received the new order with (so AFTER the transaction).
	 * The sale may have happened
	 * at a different price
	 */
	private double priceAfter;

}
