package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MenuPane extends HBox implements EvePane {

	private Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	Button btnOverview = new Button("overview");
	Button btnProvision = new Button("provision");
	Button btnOptions = new Button("options");

	public MenuPane(Manager parent) {
		this.parent = parent;

		btnOptions.setOnAction(e -> this.parent.showOptions());
		btnProvision.setOnAction(e -> this.parent.showProvision());
		btnOverview.setOnAction(e -> this.parent.showOverview());
		getChildren().addAll(btnOverview, btnProvision, btnOptions);
	}

}
