package fr.guiguilechat.eveonline.programs.manager.representation;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.scene.layout.Region;

/**
 * representation in gui of an attribute somewhere.
 *
 * @param <T>
 */
public abstract class Representation<T> {

	/**
	 * set the value when the user modified it
	 */
	protected final Consumer<T> setter;

	/**
	 * get the value to show to the user
	 */
	protected final Supplier<T> getter;

	public Consumer<T> getSet() {
		return setter;
	}

	public Supplier<T> getGet() {
		return getter;
	}

	public Representation(Supplier<T> get, Consumer<T> set) {
		this.getter = get;
		this.setter = set;
	}

	/**
	 * get the actual value represented for this. the user may have modified it,
	 * so it can be different from the geter value
	 */
	public abstract T getRepresentation();

	/**
	 * set the actual value represented by this.
	 *
	 * @param value
	 *          value to set.
	 */
	public abstract void setRepresentation(T value);

	public abstract Region getRegion();

	/**
	 * check if the representation is different from attribute, set the attribute
	 * and return true if modfication was made.
	 *
	 * @return
	 */
	public boolean updateValue() {
		T v = getRepresentation();
		T u = getter.get();
		if (v == null && u != null || v != null && !v.equals(u)) {
			setter.accept(v);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * modify the data shown to user to reflect what is in the getter.
	 *
	 * @return true if the data was modified.
	 */
	public boolean reload() {
		T old = getRepresentation();
		T now = getter.get();
		if (now == null) {
			if (old == null) {
				return false;
			} else {
				setRepresentation(now);
				return true;
			}
		} else {
			if (now.equals(old)) {
				return false;
			} else {
				setRepresentation(now);
				return true;
			}
		}
	}

}
