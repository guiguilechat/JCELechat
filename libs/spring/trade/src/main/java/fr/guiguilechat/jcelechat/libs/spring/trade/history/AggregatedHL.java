package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
	private final BigDecimal totalValue;

	/**
	 * highest price of an exchange
	 */
	private final BigDecimal highestPrice;

	/**
	 * lowest price of an exchange
	 */
	private final BigDecimal lowestPrice;

	private static final MathContext MC = new MathContext(4, RoundingMode.HALF_UP);

	@Getter(lazy = true)
	private final BigDecimal averagePrice = totalValue == null ? null
	    : new BigDecimal(totalValue.doubleValue() / volume, MC);

	/**
	 * Total number of orders placed that day
	 */
	private final long orderCount;

	private final int nbRegions;

	public static AggregatedHL convert(Object[] line) {
		return new AggregatedHL(
		    (Instant) line[0],
		    ((Number) line[1]).longValue(),
		    new BigDecimal(((Number) line[2]).doubleValue()),
		    new BigDecimal(((Number) line[3]).doubleValue(), MC),
		    new BigDecimal(((Number) line[4]).doubleValue(), MC),
		    ((Number) line[5]).longValue(),
		    ((Number) line[6]).intValue());
	}

}