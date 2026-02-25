package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;

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
 * when an order's price was updated
 */
@Entity(name = "JcelechatTradeOrderUpdate")
@Table(name = "jcelechat_trade_orderupdate", indexes = {
		@Index(columnList = "orderId, date"),
		@Index(columnList = "date"),
})
@Getter
@Setter
@ToString
public class OrderUpdate {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jcelechat_trade_orderupdate")
	@SequenceGenerator(name = "jcelechat_trade_orderupdate", allocationSize = 1, sequenceName = "jcelechat_trade_orderupdate_seq")
	@Id
	private Long id;

	private long orderId;

	/** same as order's */
	@ColumnDefault("0")
	private int typeId;

	private double newPrice;

	/** new "issued" */
	private Instant date;

}
