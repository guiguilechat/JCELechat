package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = {
		@Index(columnList = "orderId, regionId"),
}, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "orderId", "regionId" })
})
public class MarketOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_market_order_id")
	private Long id;

	/**
	 * CCP's order_id
	 */
	private long orderId;

	/**
	 * region id of the order.
	 */
	private long regionId;

	/**
	 * first inserted line that referd to that order
	 */
	@ManyToOne
	private MarketFetchLine firstLine;

	/**
	 * last analyzed line that refered to that order.
	 */
	@ManyToOne
	private MarketFetchLine lastLine;
}
