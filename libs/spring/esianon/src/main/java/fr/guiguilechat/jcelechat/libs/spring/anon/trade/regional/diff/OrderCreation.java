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
		@Index(columnList = "regionId, typeId"), // find by region
		@Index(columnList = "locationId, typeId, isBuyOrder"), // find by station
		@Index(columnList = "solarSystemId, typeId, isBuyOrder"), // find by system
		@Index(columnList = "typeId, isBuyOrder") // find globally
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

	private double price;

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
