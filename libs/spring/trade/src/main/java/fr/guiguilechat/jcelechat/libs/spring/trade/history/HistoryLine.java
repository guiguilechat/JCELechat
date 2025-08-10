package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElementAutoId;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiTradeHistoryLine")
@Table(name = "esi_trade_historyline", indexes = {
		@Index(columnList = "fetch_resource_id"),
		@Index(columnList = "date"),
}
// , uniqueConstraints = {
// @UniqueConstraint(columnNames = { "fetched_resource_id", "date" })
// }
		)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HistoryLine extends AFetchedListElementAutoId<HistoryLine, HistoryReq> {

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
		HistoryLine ret = builder()
				.average(line.average)
				.date(dateInstant(line.date))
				.highest(line.highest)
				.lowest(line.lowest)
				.orderCount(line.order_count)
				.volume(line.volume)
				.build();
		ret.setFetchResource(req);
		return ret;
	}

	static Instant dateInstant(String date) {
		return DateTimeFormatter.ISO_DATE.parse(date, LocalDate::from).atStartOfDay()
				.toInstant(ZoneOffset.UTC);
	}

}
