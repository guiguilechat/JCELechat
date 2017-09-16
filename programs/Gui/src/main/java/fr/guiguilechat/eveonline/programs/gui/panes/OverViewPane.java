package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class OverViewPane extends VBox implements EvePane {

	protected Manager parent;

	TextField tfOverview = new TextField("overview");

	public OverViewPane(Manager parent) {
		this.parent = parent;
		getChildren().add(tfOverview);
	}

	public void settingsChanged() {
	}

}
