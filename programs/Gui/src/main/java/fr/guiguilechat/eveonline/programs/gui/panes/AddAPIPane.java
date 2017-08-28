package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddAPIPane extends HBox {

	protected Manager parent;
	TextField apiID = new TextField();
	TextField apiCode = new TextField();
	Button send = new Button("add");

	public AddAPIPane(Manager parent) {
		this.parent = parent;
		apiID.setPromptText("api key");
		apiCode.setPromptText("api code");
		getChildren().addAll(apiID, apiCode, send);
		send.setOnAction(this::addApi);
	}

	public void addApi(Event e) {
		int id = Integer.parseInt(apiID.getText());
		String code = apiCode.getText();
		APIRoot api = new APIRoot(id, code);
		if (api.account.characters().isEmpty()) {
			// bad api, what do ?
			return;
		}
		parent.settings.apiKeys.put(id, code);
		parent.settings.store();
		apiID.clear();
		apiCode.clear();
		parent.settingsChanged();
	}

}
