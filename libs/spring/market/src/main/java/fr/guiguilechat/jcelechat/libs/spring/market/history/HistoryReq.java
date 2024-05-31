package fr.guiguilechat.jcelechat.libs.spring.market.history;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A requirement to observe the history : a type id and a region id.
 */
@Entity(name = "EsiMarketHistoryReq")
@Table(name = "esi_market_historyreq", indexes = { @Index(columnList = "regionId,typeId")
// , @Index(columnList = "next_fetch")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class HistoryReq {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private int regionId;

	private int typeId;

	private String lastEtag;

	/**
	 * after when should we fetch it again ?
	 */
	private Instant nextFetch;

	private Instant lastFetch;

	/**
	 * message of the last error retrieved (so response not in (200, 304)
	 */
	@Column(length = 5000)
	private String lastError;

}
