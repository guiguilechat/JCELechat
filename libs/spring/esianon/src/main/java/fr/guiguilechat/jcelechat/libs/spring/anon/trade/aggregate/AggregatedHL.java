package fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate;

import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * daily aggregated sales data, with high, low, total value and number of sales.
 * This does not contain type
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

	public AggregatedHL merge(AggregatedHL other) {
		if (!other.getDate().equals(getDate())) {
			throw new RuntimeException(
					"trying to merge two aggregated data with this.date " + getDate() + " and other.date " + other.getDate());
		}
		return new AggregatedHL(getDate(),
				getVolume() + other.getVolume(),
				getTotalValue().doubleValue() + other.getTotalValue().doubleValue(),
				getHighestPrice().doubleValue() > other.getHighestPrice().doubleValue() ? getHighestPrice()
						: other.getHighestPrice(),
						getLowestPrice().doubleValue() < other.getLowestPrice().doubleValue() ? getLowestPrice()
								: other.getLowestPrice(),
								Math.max(getNbRegions(), other.getNbRegions()));
	}

	@Override
	public String toString() {
		return "[" + date + "]";
	}

}