package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * average activity of a system inside a period of a day of week and an
 * hour
 */
@RequiredArgsConstructor
@Getter
@Builder
public class PeriodHeat {

	private final int ssId;
	private final int dow;
	private final int h;

	/**
	 * average activity for the periods considered
	 */
	private final double dailyJumps;

	static String dayOfWeek(int dow) {
		return switch (dow) {
		case 1 -> "Mon";
		case 2 -> "Tue";
		case 3 -> "Wed";
		case 4 -> "Thu";
		case 5 -> "Fri";
		case 6 -> "Sat";
		case 7 -> "Sun";
		default -> throw new IllegalArgumentException("Unexpected value: " + dow);
		};
	}

	@Getter(lazy = true)
	private final String dowStr = dayOfWeek(getDow());

}
