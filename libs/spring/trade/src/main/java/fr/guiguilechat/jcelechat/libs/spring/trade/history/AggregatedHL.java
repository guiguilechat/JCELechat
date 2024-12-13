package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AggregatedHL {

	/**
	 * The date of this historical statistic entry
	 */
	private final Instant date;

	/**
	 * total items that have been exchanged
	 */
	private final long volume;

	/**
	 * total value of total items that have been exchanged
	 */
	private final double totalValue;

	/**
	 * highest price of an exchange
	 */
	private final double highestPrice;

	/**
	 * lowest price of an exchange
	 */
	private final double lowestPrice;

	@Getter(lazy = true)
	private final double averagePrice = totalValue / volume;

	/**
	 * Total number of orders placed that day
	 */
	private final long orderCount;

	public static AggregatedHL convert(Object[] line) {
		return new AggregatedHL(
		    (Instant) line[0],
		    ((Number) line[1]).longValue(),
		    ((Number) line[2]).doubleValue(),
		    ((Number) line[3]).doubleValue(),
		    ((Number) line[4]).doubleValue(),
		    ((Number) line[5]).longValue());
	}

}