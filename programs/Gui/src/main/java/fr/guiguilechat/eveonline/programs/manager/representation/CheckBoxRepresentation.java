package fr.guiguilechat.eveonline.programs.manager.representation;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Region;

/**
 * represents a boolean through a {@link CheckBox} .
 *
 */
public class CheckBoxRepresentation extends Representation<Boolean> {

	protected final CheckBox box;

	public CheckBox getBox() {
		return box;
	}

	@Override
	public Region getRegion() {
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

	@Override
	public void setRepresentation(Boolean value) {
		if (value != null) {
			box.setSelected(value);
		}
	}

}
