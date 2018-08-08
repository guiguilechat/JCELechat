package fr.guiguilechat.tools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class JFXTools {

	/**
	 * Make a set from a observablevalue of something we can extract an iterable
	 * from<br />
	 * <p>
	 * example of code :
	 *
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	ObservableValue<Map<Date, String>> data;
	 * 	ObservableSet<Date> dates = makeSet(data, Map::keySet);
	 * }
	 * </pre>
	 * </p>
	 *
	 * @param data
	 * @param extractor
	 * @return
	 */
	public static <T, U> ObservableSet<T> makeSet(ObservableValue<U> data, Function<U, ? extends Iterable<T>> extractor) {
		HashSet<T> set = new HashSet<>();
		ObservableSet<T> ret = FXCollections
				.observableSet(set);
		synchronized (data) {
			U value = data.getValue();
			if (value != null) {
				for (T t : extractor.apply(value)) {
					set.add(t);
				}
			}
			data.addListener((ChangeListener<? super U>) (observable, oldValue, newValue) -> {
				if (newValue != null) {
					synchronized (data) {
						HashSet<T> newValues = new HashSet<>();
						for (T t : extractor.apply(data.getValue())) {
							newValues.add(t);
						}
						ret.retainAll(newValues);
						ret.addAll(newValues);
					}
				} else {
					ret.clear();
				}
			});
		}
		return ret;
	}

	static final long[] unitSuffixValue = { 1000000000000l, 1000000000l, 1000000l, 1000l };
	static final String[] unitSuffix = { "T", "B", "M", "k" };

	public static String formatPrice(double value) {
		if (value == Double.MAX_VALUE || value == Double.MIN_VALUE || value == Double.POSITIVE_INFINITY
				|| value == Double.NEGATIVE_INFINITY) {
			return "" + value;
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
		return (rets.length() > 4 ? rets.substring(0, 4).replaceAll("\\.$", "") : rets) + suffix;
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

}
