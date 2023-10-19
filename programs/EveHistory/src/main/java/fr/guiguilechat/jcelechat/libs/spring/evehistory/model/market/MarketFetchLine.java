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
	 * set to true when there is no previous record for that order id (analyzed
	 * value)
	 */
	@Builder.Default
	private boolean created = false;

	/**
	 * set to true when the order does not appear in the next market fetch (analyzed
	 * value)
	 */
	@Builder.Default
	private boolean last = false;

	/**
	 * set to true when the remaining volume has been updated since last update
	 */
	@Builder.Default
	private boolean volumeChg = false;

	/**
	 * set to true when the price has been updated since last update
	 */
	@Builder.Default
	private boolean priceChg = false;

	/**
	 * set to the previous market line (analyzed value)
	 */
	@OneToOne
	private MarketFetchLine previous;

	/**
	 * conversion of the order's issued string to an instant.
	 */
	private Instant issuedDate;

}
