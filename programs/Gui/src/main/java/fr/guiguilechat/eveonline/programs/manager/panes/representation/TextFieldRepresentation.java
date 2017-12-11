package fr.guiguilechat.eveonline.programs.manager.panes.representation;

import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import javafx.scene.layout.Region;

/**
 * represents a field in data into a textfield. Allow to update the data when
 * the textField is modified ( see {@link #updateValue()}
 *
 * @param <T>
 *          the type of the data contained in the textfield. The
 *          {@link TypedField} provided in the constructor must already have the
 *          parsing method
 */
public class TextFieldRepresentation<T> extends Representation<T> {

	protected final TypedField<T> field;

	public TypedField<T> getField() {
		return field;
	}

	@Override
	public Region getRegion() {
		return field;
	}

	public TextFieldRepresentation(TypedField<T> field, Supplier<T> get, Consumer<T> set) {
		super(get, set);
		this.field = field;
	}

	public static TextFieldRepresentation<Integer> intField(Supplier<Integer> get, Consumer<Integer> set) {
		return new TextFieldRepresentation<>(TypedField.intField(get.get()), get, set);
	}

	public static TextFieldRepresentation<Integer> positivIntField(Supplier<Integer> get, Consumer<Integer> set) {
		return new TextFieldRepresentation<>(TypedField.positivIntField(get.get()), get, set);
	}

	public static TextFieldRepresentation<Double> positivDecimal(Supplier<Double> get, Consumer<Double> set) {
		return new TextFieldRepresentation<>(TypedField.positivDecimal(get.get()), get, set);
	}

	public TextFieldRepresentation<T> withScrollAdd(ScrollAdd<?> scrollAdd) {
		getField().setOnScroll(scrollAdd);
		return this;
	}

	@Override
	public T getRepresentation() {
		return field.getValue();
	}

}
