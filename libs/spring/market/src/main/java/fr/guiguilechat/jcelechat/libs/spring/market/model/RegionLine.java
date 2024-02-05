package fr.guiguilechat.jcelechat.libs.spring.market.model;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiMarketRegionLine")
@Table(name = "esi_market_regionline", indexes = { @Index(columnList = "region_region_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class RegionLine {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * actual data received from CCP
	 */
	@Embedded
	private R_get_markets_region_id_orders order;

	@ManyToOne
	private ObservedRegion region;

	/**
	 * conversion of the order's issued string to an instant.
	 */
	private Instant issuedDate;

	public void affectFields() {
		if (getIssuedDate() == null) {
			if (getOrder().issued != null) {
				setIssuedDate(ESITools.fieldInstant(getOrder().issued));
			}
			getOrder().issued = null;
		}
	}

	public boolean isValid() {
		return getOrder().volume_remain != 0 && getOrder().duration != 0;
	}

}
