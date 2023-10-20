package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market;

import java.time.Instant;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
		@Index(columnList = "fetch_Result_Id"),
		@Index(columnList = "order_id")
})
public class MarketFetchLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private R_get_markets_region_id_orders order;

	@ManyToOne
	private MarketFetchResult fetchResult;

	/**
	 * conversion of the order's issued string to an instant.
	 */
	private Instant issuedDate;

	//
	// analyzed values
	//



	/**
	 * set to the previous market line for the same order, if not created
	 */
	@OneToOne
	private MarketFetchLine previousLine;

	/**
	 * set to the next market line for the same order, if not last
	 */
	@OneToOne(mappedBy = "previousLine")
	private MarketFetchLine nextLine;

	/**
	 * set to true when there is no previous record for that order id
	 */
	@Builder.Default
	private boolean creation = false;

	/**
	 * set to true when the price has been updated since last update
	 */
	@Builder.Default
	private boolean priceChg = false;

	/**
	 * set to true when the order does not appear in the next market fetch
	 */
	@Builder.Default
	private boolean removal = false;

	/**
	 * date before which the order was removed, if removed. It is the last-modified
	 * of the first
	 * fetch result that did not contain that order.
	 */
	private Instant removalTo;

	/**
	 * date after which the order was removed, if removed. It is the last-modified
	 * of that order's fetch result.
	 */
	private Instant removalFrom;

	/**
	 * quantity exchanged from this order. if this order is created on the fetch, it
	 * is equal to the difference between the order volume remain and total.
	 * Otherwise it is equal to the reduction in order volume compared to
	 * {@link #previousLine}.
	 */
	@Builder.Default
	private int sold = 0;

	/**
	 * date after which the sales associated to that order were performed.
	 */
	private Instant soldFrom;

	/**
	 * date before which the sales associated to that order were performed.
	 */
	private Instant soldTo;

}
