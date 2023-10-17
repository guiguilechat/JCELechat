package fr.guiguilechat.jcelechat.libs.spring.evehistory.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MarketFetchResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreatedDate
	@Builder.Default
	private Instant createdDate = Instant.now();

	/** id of the region used for the call */
	private int regionId;

	/** true iff the result was okay OR the last etag was kept */
	@Builder.Default
	private boolean fail = false;

	/** etag of the resource after the fetch if any */
	private String etag;

	@Column(length = 5000)
	/** description of the error if any */
	private String errors;

	/** pages received, if any */
	private Integer pages;

	/** response code retrieved if any */
	private Integer responseCode;

}
