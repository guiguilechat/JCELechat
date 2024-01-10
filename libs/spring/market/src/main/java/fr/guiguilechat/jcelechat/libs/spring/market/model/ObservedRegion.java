package fr.guiguilechat.jcelechat.libs.spring.market.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiMarketObservedRegion")
@Table(name = "esi_market_observed_region")
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

	private String lastEtag;

}
