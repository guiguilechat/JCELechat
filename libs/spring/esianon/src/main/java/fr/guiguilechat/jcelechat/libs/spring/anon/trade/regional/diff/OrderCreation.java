package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "JcelechatTradeOrderCreation")
@Table(name = "jcelechat_trade_ordercreation", indexes = {
		@Index(columnList = "typeId, isBuyOrder, dateMax, dateMin"), // find globally
		@Index(columnList = "regionId, typeId, isBuyOrder, dateMax, dateMin"), // find by region
		@Index(columnList = "locationId, typeId, isBuyOrder, dateMax, dateMin"), // find by station
		@Index(columnList = "solarSystemId, typeId, isBuyOrder, dateMax, dateMin"), // find by system
})
@Getter
@Setter
@ToString
public class OrderCreation {

	@Id
	private long orderId;

	// order info at creation

	private long typeId;

	private boolean isBuyOrder;

	/**
	 * price of the order when we created this. this may or may not be same as
	 * initial order price.
	 */
	private double price;

	/**
	 * quantity of items on the order
	 */
	private int volume;

	private int duration;

	// location

	private long locationId;

	private int solarSystemId;

	private int regionId;

	// deduce info on when the order was created.

	private Instant dateMax;

	private Instant dateMin;

}
