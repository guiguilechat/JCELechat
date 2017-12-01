package fr.guiguilechat.eveonline.programs.manager.panes;

import java.util.function.Function;
import java.util.function.Predicate;

import javafx.scene.control.TextField;

/**
 *
 * A {@link TextField} that only allow the user to specify text values that
 * correspond to a type *
 *
 * @param <T>
 */
public class TypedField<T> extends TextField {

	protected Function<String, T> parser;

	protected Predicate<String> allowedFormat;

	public T getValue() {
		return parser.apply(getText());
	}

	public void setValue(T val) {
		setText("" + val);
	}

	public TypedField(Function<String, T> parser, Predicate<String> format, T value) {
		this.parser = parser;
		this.allowedFormat = format;
		setValue(value);
		textProperty().addListener(this::textModifListen);
	}

	boolean recursiveTest = false;

	protected void textModifListen(Object o, String old, String now) {
		if (recursiveTest) {
			return;
		}
		if (!allowedFormat.test(now)) {
			recursiveTest = true;
			setText(old);
			recursiveTest = false;
		}
	}

	protected static boolean isInt(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}

	public static TypedField<Integer> intField(Integer value) {
		return new TypedField<>(Integer::parseInt, TypedField::isInt, value);
	}

	protected static boolean isPositivInt(String str) {
		return isInt(str) && str.charAt(0) != '-';
	}

	public static TypedField<Integer> positivIntField(Integer value) {
		return new TypedField<>(Integer::parseInt, TypedField::isPositivInt, value);
	}

	/**
	 * check if a string is in the form nnn.nnn with n numbers, eg 10, 2435.124777
	 */
	protected static boolean isPositivDecimal(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int nbDot = 0;
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if (c == '.') {
				nbDot++;
				if (nbDot > 1) {
					return false;
				}
			} else if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}

	public static TypedField<Double> positivDecimal(Double value) {
		return new TypedField<>(Double::parseDouble, TypedField::isPositivDecimal, value);
	}

}
