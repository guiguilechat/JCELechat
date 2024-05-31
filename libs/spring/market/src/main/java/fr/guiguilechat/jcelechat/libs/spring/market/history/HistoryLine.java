package fr.guiguilechat.jcelechat.libs.spring.market.history;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
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
@Table(name = "esi_market_historyline", indexes = { @Index(columnList = "history_req_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class HistoryLine {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private HistoryReq historyReq;

	/**
	 * average number
	 */
	private double average;

	private Instant date;
	/**
	 * highest number
	 */
	private double highest;
	/**
	 * lowest number
	 */
	private double lowest;
	/**
	 * Total number of orders happened that day
	 */
	private long orderCount;
	/**
	 * Total
	 */
	private long volume;

	public static HistoryLine of(HistoryReq req, R_get_markets_region_id_history line) {
		return builder()
				.historyReq(req)
				.average(line.average)
				.date(DateTimeFormatter.ISO_DATE.parse(line.date, LocalDate::from).atStartOfDay()
						.toInstant(ZoneOffset.UTC))
				.highest(line.highest)
				.lowest(line.lowest)
				.orderCount(line.order_count)
				.volume(line.volume)
				.build();
	}

}
