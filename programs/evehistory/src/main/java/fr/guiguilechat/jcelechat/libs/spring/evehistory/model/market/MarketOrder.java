package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/** a market order from CCP */
@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MarketOrder {

	/**
	 * CCP's order_id
	 */
	@Id
	private Long orderId;

	/**
	 * first inserted line that referred to that order
	 */
	@OneToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private MarketFetchLine firstLine;

	/**
	 * last analyzed line that referred to that order.
	 */
	@OneToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private MarketFetchLine lastLine;
}
