package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * daily aggregated sales data, with high, low, total value and number of sales.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AggregatedHL {

	public static final MathContext MC = new MathContext(4, RoundingMode.HALF_UP);

	public AggregatedHL(Instant date, long volume,
		double totalValue,
		double highestPrice,
		double lowestPrice,
		long orderCount,
		int nbRegions ) {
		this(date, volume,
		    new BigDecimal(totalValue, MC),
		    new BigDecimal(highestPrice, MC),
		    new BigDecimal(lowestPrice, MC),
		    orderCount,
		    nbRegions);
}

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

	@Getter(lazy = true)
	private final BigDecimal averagePrice = totalValue == null ? null
	    : new BigDecimal(totalValue.doubleValue() / volume, MC);

	/**
	 * Total number of orders placed that day
	 */
	private final long orderCount;

	/** number of regions which had a corresponding trade on that period */
	private final int nbRegions;

}