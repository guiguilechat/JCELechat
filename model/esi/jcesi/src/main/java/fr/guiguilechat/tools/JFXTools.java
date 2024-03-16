package fr.guiguilechat.tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;

import fr.lelouet.tools.holders.impl.collections.MapHolderImpl;
import fr.lelouet.tools.holders.impl.collections.SetHolderImpl;
import fr.lelouet.tools.holders.interfaces.numbers.BoolHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import fr.lelouet.tools.holders.interfaces.numbers.FloatHolder;
import fr.lelouet.tools.holders.interfaces.numbers.IntHolder;
import fr.lelouet.tools.holders.interfaces.numbers.LongHolder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableFloatValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableLongValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

public class JFXTools {

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

	public static ObservableDoubleValue obs(DoubleHolder h) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		h.follow(ret::set);
		return ret;
	}

	public static ObservableFloatValue obs(FloatHolder h) {
		SimpleFloatProperty ret = new SimpleFloatProperty();
		h.follow(ret::set);
		return ret;
	}

	public static ObservableIntegerValue obs(IntHolder h) {
		SimpleIntegerProperty ret = new SimpleIntegerProperty();
		h.follow(ret::set);
		return ret;
	}

	public static ObservableLongValue obs(LongHolder h) {
		SimpleLongProperty ret = new SimpleLongProperty();
		h.follow(ret::set);
		return ret;
	}

	public static ObservableBooleanValue obs(BoolHolder h) {
		SimpleBooleanProperty ret = new SimpleBooleanProperty();
		h.follow(ret::set);
		return ret;
	}

	public static <U> SetHolderImpl<U> hold(ObservableSet<U> o) {
		SetHolderImpl<U> ret = new SetHolderImpl<>(o);
		synchronized (o) {
			o.addListener((SetChangeListener<U>) ch -> ret.set(new HashSet<>(o)));
		}
		return ret;
	}

	public static <K, V> MapHolderImpl<K, V> hold(ObservableMap<K, V> o) {
		MapHolderImpl<K, V> ret = new MapHolderImpl<>(o);
		synchronized (o) {
			o.addListener((MapChangeListener<K, V>) ch -> ret.set(new HashMap<>(o)));
		}
		return ret;
	}

}
