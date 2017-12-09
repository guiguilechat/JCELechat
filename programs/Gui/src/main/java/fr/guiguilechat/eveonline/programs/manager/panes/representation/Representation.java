package fr.guiguilechat.eveonline.programs.manager.panes.representation;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * representation in gui of an attribute somewhere.
 *
 * @param <T>
 */
public abstract class Representation<T> {

	protected final Consumer<T> setter;

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

}
