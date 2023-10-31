package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market;

import java.time.Instant;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
		@Index(columnList = "fetch_result_id,invalid"),
		@Index(columnList = "order_id"),
		@Index(columnList = "previous_line_id")
})
public class MarketFetchLine {

	@Id
	// using sequence is supposed to be faster for insert batch
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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

	@Builder.Default
	/** set to true when the line has either duration = 0 or volume_remain=0 */
	private boolean invalid = false;

	/**
	 * set to the previous market line for the same order, if not created
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private MarketFetchLine previousLine;

//	/**
//	 * dual of {@link #previousLine}
//	 */
//	@Setter(AccessLevel.NONE)
//	@OneToOne(mappedBy = "previousLine", fetch = FetchType.LAZY)
//	private MarketFetchLine nextLine;

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
	 * true if the order's EOL was within removalFrom and removalTo
	 */
	@Builder.Default
	private boolean eol = false;

	/**
	 * estimate of the removal of this order. If {@link #eol} then we use the
	 * order's EOL. Otherwise avg({@link #removalFrom}, {@link #removalTo})
	 */
	private Instant removalDate;

	/**
	 * quantity exchanged from this order. if this order is created on the fetch,
	 * this quantity
	 * is equal to the difference between the order volume remain and total.
	 * Otherwise it is equal to the reduction in order volume compared to this'
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
