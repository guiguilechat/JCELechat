package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.layout.VBox;

public class OptionPane extends VBox {

	protected Manager parent;
	AddAPIPane addApi;
	ListApi listApi;

	public OptionPane(Manager parent) {
		this.parent = parent;
		addApi = new AddAPIPane(parent);
		listApi = new ListApi(parent);
		getChildren().addAll(listApi, addApi);
	}

	public void settingsChanged() {
		listApi.settingsChanged();
	}

}
