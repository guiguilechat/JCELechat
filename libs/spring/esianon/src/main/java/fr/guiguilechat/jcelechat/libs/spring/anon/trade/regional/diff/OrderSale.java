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

@Entity(name = "JcelechatTradeOrderSale")
@Table(name = "jcelechat_trade_ordersale", indexes = {
		@Index(columnList = "orderId, dateMax"),
		@Index(columnList = "typeId, dateMax"),
})
@Getter
@Setter
@ToString
public class OrderSale {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jcelechat_trade_ordersale")
	@SequenceGenerator(name = "jcelechat_trade_ordersale", allocationSize = 1, sequenceName = "jcelechat_trade_ordersale_seq")
	@Id
	private Long id;

	private long orderId;

	/** is the same as the order's typeId */
	private int typeId;

	private Instant dateMax;

	private Instant dateMin;

	/** difference in quantity from the order */
	private int volume;

	/**
	 * this is the price we received the new order with. The sale may have happened
	 * at a different price
	 */
	private double priceEnd;

}
