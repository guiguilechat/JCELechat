package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ObservedRegion {

	@Id
	@Column(unique = true)
	private int regionId;

	@Builder.Default
	private boolean active = true;

	/**
	 * last fetch that resulted in a success.
	 * Annotated to avoid recursive usage
	 */
	@OneToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private MarketFetchResult lastFetchSuccess;

}
