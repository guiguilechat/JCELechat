package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * a list of {@link AggregatedHL} , when adding a new one removes those
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
		return getList().stream().mapToLong(AggregatedHL::getVolume).sum();
	}

	public double totalValue() {
		return getList().stream().mapToDouble(hl -> hl.getTotalValue().doubleValue()).sum();
	}

	public double highest() {
		return getList().stream().mapToDouble(hl -> hl.getHighestPrice().doubleValue()).max().getAsDouble();

	}

	public double lowest() {
		return getList().stream().mapToDouble(hl -> hl.getLowestPrice().doubleValue()).min().getAsDouble();

	}

	public Double averagePrice() {
		long volume = volume();
		return volume == 0 ? null : totalValue() / volume;
	}

	public double averageDailyVolume() {
		return volume() / (days + 1);
	}

	/**
	 * @return a new {@link AggregatedHL} to represent this list. Will return null
	 *           if no item present.
	 */
	public AggregatedHL toAggregatedHL() {
		if (list.isEmpty()) {
			return null;
		}
		return new AggregatedHL(list.get(list.size() - 1).getDate(), volume(), totalValue(), highest(), lowest(),
		    list.get(list.size() - 1).getNbRegions());
	}

}
