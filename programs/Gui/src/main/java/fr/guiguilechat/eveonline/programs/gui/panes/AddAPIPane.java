package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddAPIPane extends HBox {

	protected Manager parent;
	TextField apiKey = new TextField();
	TextField apiCode = new TextField();
	Button send = new Button("add");

	public AddAPIPane(Manager parent) {
		this.parent = parent;
		apiKey.setPromptText("api key");
		apiCode.setPromptText("api code");
		getChildren().addAll(apiKey, apiCode, send);
		send.setOnAction(this::addApi);
	}

	public void addApi(Event e) {
		String key = apiKey.getText();
		String code = apiCode.getText();
		parent.settings.apiKeys.put(key, code);
		parent.settings.store();
		apiKey.clear();
		apiCode.clear();
		parent.settingsChanged();
	}

}
