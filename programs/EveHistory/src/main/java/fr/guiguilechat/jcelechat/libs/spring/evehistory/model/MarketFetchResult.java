package fr.guiguilechat.jcelechat.libs.spring.evehistory.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

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

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = {
		@Index(columnList = "failed"),
		@Index(columnList = "analyzed")
})
public class MarketFetchResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreatedDate
	private Instant createdDate;

	/** id of the region used for the call */
	private int regionId;

	/**
	 * true iff the result was an error. no change (304) will not produce an error :
	 * instead etag will not be set.
	 */
	@Builder.Default
	private boolean failed = false;

	/** etag of the resource after the fetch if any */
	private String etag;

	@Column(length = 5000)
	/** description of the error if any */
	private String errors;

	/** response code retrieved if any */
	private Integer responseCode;

	/** pages received, if any */
	private Integer pages;

	/**
	 * number of orders fetched
	 */
	private Integer lineFetched;

	/**
	 * set to true when the data fetched have been analyzed.
	 */
	@Builder.Default
	private boolean analyzed = false;

	/**
	 * number of lines associated with that result after analyzis
	 */
	private Integer lineUpdated;

}
