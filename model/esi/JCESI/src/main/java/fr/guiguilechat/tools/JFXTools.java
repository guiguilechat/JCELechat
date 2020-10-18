package fr.guiguilechat.tools;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableLongValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class JFXTools {

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
	public static IntegerProperty convertInt(StringProperty prop, IntPredicate... predicates) {
		SimpleIntegerProperty ret = new SimpleIntegerProperty();
		ChangeListener<String> l = new ChangeListener<>() {

			boolean recursive = false;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!recursive) {
					recursive = true;
					int newint = 0;
					boolean accepted = true;
					try {
						newint = Integer.parseInt(newValue);
						if (predicates != null) {
							for (IntPredicate p : predicates) {
								accepted &= p.test(newint);
							}
						}
					} catch (NumberFormatException | NullPointerException e) {
						accepted = false;
						prop.setValue(oldValue);
					}
					if (accepted) {
						ret.set(newint);
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
	 * apply the existing map to the listener, then register the listener.
	 *
	 * @param map
	 * @param listener
	 */
	public static <U, V> void listenM(ObservableMap<U, V> map, MapChangeListener<U, V> listener) {
		synchronized (map) {
			ObservableMap<U, V> other = FXCollections.observableHashMap();
			other.addListener(listener);
			other.putAll(map.entrySet().stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
			map.addListener(listener);
		}
	}

	/**
	 * wait for the {@link ObservableValue} to be no more null.
	 *
	 * @param map
	 */
	public static <U> void waitO(ObservableValue<U> obs) {
		CountDownLatch latch;
		synchronized (obs) {
			if (obs.getValue() != null) {
				return;
			}
			latch = new CountDownLatch(1);
			obs.addListener(new ChangeListener<U>() {

				@Override
				public void changed(ObservableValue<? extends U> observable, U oldValue, U newValue) {
					obs.removeListener(this);
					latch.countDown();
				}

			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

}
