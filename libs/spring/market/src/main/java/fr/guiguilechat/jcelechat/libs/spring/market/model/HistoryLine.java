package fr.guiguilechat.jcelechat.libs.spring.market.model;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
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

@Entity(name = "EsiMarketHistoryLine")
@Table(name = "esi_market_historyline", indexes = { @Index(columnList = "req_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class HistoryLine {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private HistoryReq req;

	@Embedded
	private R_get_markets_region_id_history daily;

	private Instant dateDate;

	public void affectFields() {
		if (dateDate == null) {
			if (getDaily().date != null) {
				setDateDate(ESITools.fieldInstant(getDaily().date));
			}
			getDaily().date = null;
		}
	}

}
