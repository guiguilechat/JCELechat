package fr.guiguilechat.jcelechat.programs.manager.panes.apikeys;

import fr.guiguilechat.jcelechat.programs.manager.DataHandler;
import fr.guiguilechat.jcelechat.programs.manager.panes.managed.MTabPane;
import javafx.scene.control.Tab;

public class APIPane extends MTabPane {

	public APIPane(DataHandler handler) {
		super(handler);
		getTabs().addAll(new Tab("create", new SSOCreationPane(handler)), new Tab("list", new ListAPI(handler)));
	}

}
