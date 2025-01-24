package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * keep a list of {@link AggregatedHL} , when adding a new one removes those
 * older than {@link #days}. The {@link AggregatedHL} should be added by date
 * increasing.
 */
@Getter
@RequiredArgsConstructor
public class SlidingAverage {

	/**
	 * complete number of days after which we remove old items of the list when
	 * adding a new one.
	 */
	private final int days;

	private final List<AggregatedHL> list = new ArrayList<>();

	public void add(AggregatedHL item) {
		list.add(item);
		list.removeIf(hl -> ChronoUnit.DAYS.between(hl.getDate(), item.getDate()) > days);
	}

	public long volume() {
		return getList().stream().mapToLong(hl -> hl.getVolume()).sum();
	}

	public BigDecimal totalValue() {
		return new BigDecimal(getList().stream().mapToDouble(hl -> hl.getTotalValue().doubleValue()).sum(),
		    AggregatedHL.MC);
	}

}
