package fr.guiguilechat.jcelechat.libs.spring.market.model;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiMarketLine")
@Table(name = "esi_market_line", indexes = { @Index(columnList = "region_region_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Line {

	@Id
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
		setId(getOrder().order_id);
		if (getIssuedDate() == null) {
			if (getOrder().issued != null) {
				setIssuedDate(ESITools.convertDate(getOrder().issued).toInstant());
			}
			getOrder().issued = null;
		}
	}

	public boolean isValid() {
		return getOrder().volume_remain != 0 && getOrder().duration != 0;
	}

}
