package fr.guiguilechat.eveonline.programs.gui.panes.options;

import fr.guiguilechat.eveonline.model.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddAPIPane extends HBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	TextField apiID = new TextField();
	TextField apiCode = new TextField();
	Button send = new Button("add API");

	public AddAPIPane(Manager parent) {
		this.parent = parent;
		setStyle("-fx-border-color: black");
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
		parent.addAPI(id, code);
		apiID.clear();
		apiCode.clear();
	}

}
