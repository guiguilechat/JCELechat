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
})
@Getter
@Setter
@ToString
public class OrderDeletion {

	@Id
	private long orderId;

	private Instant dateMax;

	private Instant dateMin;

}
