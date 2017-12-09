package fr.guiguilechat.eveonline.programs.manager.panes.representation;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.scene.control.CheckBox;

/**
 * represents a boolean through a {@link CheckBox} .
 *
 */
public class CheckBoxRepresentation extends Representation<Boolean> {

	protected final CheckBox box;

	public CheckBox getBox() {
		return box;
	}

	public CheckBoxRepresentation(CheckBox box, Supplier<Boolean> get, Consumer<Boolean> set) {
		super(get, set);
		this.box = box;
	}

	public CheckBoxRepresentation(String label, Supplier<Boolean> get, Consumer<Boolean> set) {
		this(new CheckBox(label), get, set);
		box.setSelected(get.get());
	}

	@Override
	public Boolean getRepresentation() {
		return box.isSelected();
	}

}
