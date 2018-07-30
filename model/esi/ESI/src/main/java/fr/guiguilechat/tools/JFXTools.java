package fr.guiguilechat.tools;

import java.util.HashSet;
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

}
