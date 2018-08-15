package fr.guiguilechat.tools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.LongPredicate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableLongValue;
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

	/**
	 * extract an Observable variable from another one with the help of an
	 * extractor function
	 *
	 * @param variable
	 * @param extractor
	 * @return a new simpleobjectProperty
	 */
	public static <T, U> ObjectProperty<T> extractObservable(ObservableValue<U> variable,
			Function<U, ObservableValue<T>> extractor) {
		SimpleObjectProperty<T> ret = new SimpleObjectProperty<>();
		synchronized (variable) {
			if (variable.getValue() != null) {
				ret.bind(extractor.apply(variable.getValue()));
			}
			variable.addListener((ChangeListener<U>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(extractor.apply(newValue));
				}
			});
		}
		return ret;
	}

	/**
	 * extract a observable double variable from an observable variable with the
	 * help of an extractor function
	 *
	 * @param variable
	 * @param extractor
	 * @return
	 */
	public static <U> DoubleProperty extractDouble(ObservableValue<U> variable,
			Function<U, ObservableDoubleValue> extractor) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		synchronized (variable) {
			if (variable.getValue() != null) {
				ret.bind(extractor.apply(variable.getValue()));
			}
			variable.addListener((ChangeListener<U>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(extractor.apply(newValue));
				}
			});
		}
		return ret;
	}

	/**
	 * extract a observable long variable from an observable variable with the
	 * help of an extractor function
	 *
	 * @param variable
	 * @param extractor
	 * @return
	 */
	public static <U> LongProperty extractLong(ObservableValue<U> variable,
			Function<U, ObservableLongValue> extractor) {
		SimpleLongProperty ret = new SimpleLongProperty();
		synchronized (variable) {
			if (variable.getValue() != null) {
				ret.bind(extractor.apply(variable.getValue()));
			}
			variable.addListener((ChangeListener<U>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(extractor.apply(newValue));
				}
			});
		}
		return ret;
	}

	/**
	 * ensure that the property always holds a double value, and return a property
	 * bound to that double value
	 *
	 * @param prop
	 * @return
	 */
	public static DoubleProperty convertDouble(StringProperty prop, DoublePredicate... predicates) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		ChangeListener<String> l = new ChangeListener<>() {

			boolean recursive = false;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!recursive) {
					recursive = true;
					double newdouble = 0;
					boolean accepted = true;
					try {
						newdouble = Double.parseDouble(newValue);
						if (predicates != null) {
							for (DoublePredicate p : predicates) {
								accepted &= p.test(newdouble);
							}
						}
					} catch (NumberFormatException | NullPointerException e) {
						accepted = false;
					}
					if (accepted) {
						ret.set(newdouble);
					} else {
						prop.setValue(oldValue);
					}
					recursive = false;
				}
			}
		};
		l.changed(prop, null, prop.get());
		prop.addListener(l);
		return ret;
	}

	/**
	 * ensure that the property always holds a double value, and return a property
	 * bound to that double value
	 *
	 * @param prop
	 *          the string property to observe
	 * @param predicates
	 *          optional predicates on long to accept, eg l->l>=0 for positive
	 *          longs
	 *
	 * @return
	 */
	public static LongProperty convertLong(StringProperty prop, LongPredicate... predicates) {
		SimpleLongProperty ret = new SimpleLongProperty();
		ChangeListener<String> l = new ChangeListener<>() {

			boolean recursive = false;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!recursive) {
					recursive = true;
					long newlong = 0;
					boolean accepted = true;
					try {
						newlong = Long.parseLong(newValue);
						if (predicates != null) {
							for( LongPredicate p : predicates) {
								accepted &= p.test(newlong);
							}
						}
					} catch (NumberFormatException | NullPointerException e) {
						accepted = false;
						prop.setValue(oldValue);
					}
					if (accepted) {
						ret.set(newlong);
					} else {
						prop.setValue(oldValue);
					}
					recursive = false;
				}
			}
		};
		l.changed(prop, null, prop.get());
		prop.addListener(l);
		return ret;
	}
}
