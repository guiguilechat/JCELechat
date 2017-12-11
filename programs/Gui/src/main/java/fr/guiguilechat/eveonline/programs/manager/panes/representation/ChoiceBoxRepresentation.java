package fr.guiguilechat.eveonline.programs.manager.panes.representation;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;

/**
 * represent a choice among several values by a choicebox
 *
 * @param <T>
 *          the possible elements that are presented.
 */
public class ChoiceBoxRepresentation<T> extends Representation<T> {

	protected final ChoiceBox<T> box;

	public ChoiceBox<T> getBox() {
		return box;
	}

	@Override
	public Region getRegion() {
		return box;
	}

	public ChoiceBoxRepresentation(ChoiceBox<T> box, Supplier<T> get, Consumer<T> set) {
		super(get, set);
		this.box = box;
		box.getSelectionModel().select(get.get());
	}

	public ChoiceBoxRepresentation(Supplier<T> get, Consumer<T> set, Collection<T> values) {
		this(new ChoiceBox<>(FXCollections.observableArrayList(values)), get, set);
	}

	@SuppressWarnings("unchecked")
	public ChoiceBoxRepresentation(Supplier<T> get, Consumer<T> set, T... values) {
		this(new ChoiceBox<>(FXCollections.observableArrayList(values)), get, set);
	}

	@Override
	public T getRepresentation() {
		return box.getValue();
	}

}
