package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MenuPane extends HBox {

	private Manager parent;

	Button btnOptions = new Button("Options");
	Button btnOverview = new Button("useless");

	public MenuPane(Manager parent) {
		this.parent = parent;

		btnOptions.setOnAction(e -> this.parent.showOptions());
		btnOverview.setOnAction(e -> this.parent.showOverview());
		getChildren().addAll(btnOverview, btnOptions);
	}

}
