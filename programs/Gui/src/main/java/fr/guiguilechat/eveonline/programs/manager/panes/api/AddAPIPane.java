package fr.guiguilechat.eveonline.programs.manager.panes.api;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fr.guiguilechat.eveonline.model.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	Button create = new Button("create a new api");

	public AddAPIPane(Manager parent) {
		this.parent = parent;
		setStyle("-fx-border-color: black");
		apiID.setPromptText("api key");
		apiCode.setPromptText("api code");
		getChildren().addAll(apiID, apiCode, send, new Label(" "), create);
		send.setOnAction(this::addApi);
		create.setOnAction(
				event -> {
					try {
						new Thread(() -> {
							if (Desktop.isDesktopSupported()) {
								try {
									Desktop.getDesktop().browse(new URI("http://community.eveonline.com/support/api-key/update/"));
								} catch (IOException | URISyntaxException e) {
									throw new UnsupportedOperationException("catch this", e);
								}
							}
						}).start();
					} catch (Exception e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				});
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
