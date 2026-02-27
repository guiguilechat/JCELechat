package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "JcelechatTradeOrderDeletion")
@Table(name = "jcelechat_trade_orderdeletion", indexes = {
		@Index(columnList = "dateMax, dateMin"),
		@Index(columnList = "typeId, dateMax, dateMin"),
})
@Getter
@Setter
@ToString
public class OrderDeletion {

	// order info, copied

	@Id
	private long orderId;

	private int typeId;

	private boolean isBuyOrder;

	private double price;

	private int volume;

	private long locationId;

	private int solarSystemId;

	private int regionId;

	// fetch info

	private Instant dateMax;

	private Instant dateMin;

	/** true iff the expire date of the order is between datemin and datemax */
	private boolean canExpire;

}
