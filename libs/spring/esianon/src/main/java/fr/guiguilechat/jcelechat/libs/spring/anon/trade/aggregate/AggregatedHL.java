package fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate;

import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * daily aggregated sales data, with high, low, total value and number of sales.
 * This does not contain type information.
 */
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
	private final Number volume;

	public long getVolume() {
		return volume.longValue();
	}

	/**
	 * total value of total items that have been exchanged
	 */
	private final Number totalValue;

	/**
	 * highest price of an exchange
	 */
	private final Number highestPrice;

	/**
	 * lowest price of an exchange
	 */
	private final Number lowestPrice;

	@Getter(lazy = true)
	private final Double averagePrice = totalValue == null ? null
			: totalValue.doubleValue() / volume.longValue();

	/** number of regions which had a corresponding trade on that period */
	private final long nbRegions;

	@Override
	public String toString() {
		return "[" + date + "]";
	}

}