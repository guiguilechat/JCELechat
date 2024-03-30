package fr.guiguilechat.tools;

import java.time.Instant;
import java.util.LinkedList;

public class FormatTools {


	static final long[] unitSuffixValue = { 1000000000000l, 1000000000l, 1000000l, 1000l };
	static final String[] unitSuffix = { "T", "B", "M", "k" };

	/**
	 * format a price into unit, with suffix up to 10^12 and maximum 4
	 * numbers<br />
	 * eg 1.345 e10 should be translated to 13.4B
	 *
	 * @param value
	 * @return
	 */
	public static String formatPrice(double value) {
		if (value == Double.MAX_VALUE || value == Double.MIN_VALUE || value == Double.POSITIVE_INFINITY
				|| value == Double.NEGATIVE_INFINITY) {
			return "" + value;
		}
		if (value < 0) {
			return "-"+formatPrice(-value);
		}
		double prefix = value;
		String suffix = null;
		for (int i = 0; i < unitSuffix.length && suffix == null; i++) {
			if (value >= unitSuffixValue[i]) {
				prefix = value / unitSuffixValue[i];
				suffix = unitSuffix[i];
			}
		}
		if (suffix == null) {
			suffix = "";
		}
		String rets = "" + prefix;
		if (rets.endsWith(".0")) {
			rets=rets.substring(0, rets.length()-2);
		}
		return (rets.length() > 5 ? rets.substring(0, 5).replaceAll("\\.$", "") : rets) + suffix;
	}

	public static String formatDurationSeconds(long seconds) {
		if (seconds == 0) {
			return "0s";
		}
		LinkedList<String> strings = new LinkedList<>();
		if (seconds % 60 != 0) {
			strings.add(0, "" + seconds % 60 + "s");
		}
		seconds /= 60;
		if (seconds > 0) {
			if (seconds % 60 != 0) {
				strings.add(0, "" + seconds % 60 + "m");
			}
			seconds /= 60;
			if (seconds > 0) {
				if (seconds % 24 != 0) {
					strings.add(0, "" + seconds % 24 + "h");
				}
				seconds /= 24;
				if (seconds > 0) {
					strings.add(0, "" + seconds + "D");
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * @param daysAdded days added to source
	 * @param source    the source of the delay
	 */
	public static String formatDelay(Instant source) {
		long endS = source.getEpochSecond();
		long startS = Instant.now().getEpochSecond();
		long delay = endS - startS;
		boolean minus = delay < 0;
		if (minus) {
			delay *= -1;
		}
		int seconds = (int) (delay % 60);
		delay /= 60;

		int minutes = (int) (delay % 60);
		delay /= 60;

		int hours = (int) (delay % 24);
		delay /= 24;

		int days = (int) (delay % 365);
		delay /= 365;

		StringBuilder sb = new StringBuilder();
		if (minus) {
			sb.append("-");
		}
		if (delay > 0) {
			sb.append(delay).append("y");
		}
		if (delay < 10) {
			if (days > 0) {
				sb.append(days).append("d");
			}
			if (days < 30) {

				if (hours > 0) {
					sb.append(hours).append("h");
				}
				if (minutes > 0) {
					sb.append(minutes).append("min");
				}
				if (hours == 0 && seconds > 0) {
					sb.append(seconds).append("s");
				}
			}
		}
		return sb.toString();
	}
}
