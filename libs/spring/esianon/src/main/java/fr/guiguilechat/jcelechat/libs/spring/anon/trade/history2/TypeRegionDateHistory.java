package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.guiguilechat.jcelechat.libs.everef.history.HistoryEntry;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionDateHistory.TypeRegionDateKey;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * region, type, date history retrieved from either ESI or everef
 */
@Entity(name = "EsiTradeTypeRegionDateHistory")
@Table(name = "esi_trade_typeregiondatehistory", indexes = {
		@Index(columnList = "date"),
		@Index(columnList = "extsource,date"),
		@Index(columnList = "typeId,date")
}
		)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(TypeRegionDateKey.class)
public class TypeRegionDateHistory {

	@Embeddable
	public static record TypeRegionDateKey(int regionId, int typeId, LocalDate date) {
	}

	public static final String EVEREF_EXTSOURCE = "everef";

	/**
	 * average number
	 */
	private double average;

	@Id
	private LocalDate date;
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

	@Id
	private int regionId;

	@Id
	private int typeId;

	/**
	 * Total amount of items traded
	 */
	private long volume;

	/** when the data was sourced from outside of ESI, the source. eg: "everef" */
	private String extsource;

	public static TypeRegionDateHistory of(TypeRegionHistory source, R_get_markets_region_id_history line) {
		return builder()
				.average(line.average)
				.date(dateLocalDate(line.date))
				.highest(line.highest)
				.lowest(line.lowest)
				.orderCount(line.order_count)
				.regionId(source.getRegion().getId())
				.typeId(source.getType().getId())
				.volume(line.volume)
				.build();
	}

	static LocalDate dateLocalDate(String date) {
		return DateTimeFormatter.ISO_DATE.parse(date, LocalDate::from);
	}

	public static TypeRegionDateHistory of(LocalDate date, HistoryEntry he) {
		return builder()
				.average(he.getAverage().doubleValue())
				.date(date)
				.extsource(EVEREF_EXTSOURCE)
				.highest(he.getHighest().doubleValue())
				.lowest(he.getLowest().doubleValue())
				.orderCount(he.getOrder_count())
				.regionId(he.getRegion_id())
				.typeId(he.getType_id())
				.volume(he.getVolume())
				.build();
	}

}
