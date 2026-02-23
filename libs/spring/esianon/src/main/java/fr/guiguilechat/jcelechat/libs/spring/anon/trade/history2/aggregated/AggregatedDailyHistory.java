package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated;

import java.time.LocalDate;
import java.time.ZoneOffset;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated.AggregatedDailyHistory.AggregatedDailyTypeKey;
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
 * aggregated value of sales for a type and a day. This should include performed
 * contracts, as well as sales for all regions
 */
@Entity(name = "JcelechatTradeAggregatedHistory")
@Table(name = "jcelechat_trade_aggregatedhistory", indexes = {
		@Index(columnList = "typeId, me, te, copy")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(AggregatedDailyTypeKey.class)
public class AggregatedDailyHistory {

	@Embeddable
	public static record AggregatedDailyTypeKey(int typeId, int me, int te, boolean copy, LocalDate date) {
	}

	/**
	 * average number
	 */
	private double average;

	@Id
	private boolean copy;

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

	@Id
	private int me;

	@Id
	private int te;

	@Id
	private int typeId;

	/**
	 * Total amount of items traded
	 */
	private long volume;

	public AggregatedHL toAggregtedHL() {
		return new AggregatedHL(getDate().atStartOfDay().atOffset(ZoneOffset.UTC).toInstant(),
				getVolume(),
				getVolume() * getAverage(),
				null,
				null,
				0);
	}

}
