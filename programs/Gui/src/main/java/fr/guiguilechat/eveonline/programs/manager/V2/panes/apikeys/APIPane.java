package fr.guiguilechat.eveonline.programs.manager.V2.panes.apikeys;

import fr.guiguilechat.eveonline.programs.manager.V2.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.V2.panes.managed.ManagedTab;
import javafx.scene.control.Tab;

public class APIPane extends ManagedTab {

	public APIPane(DataHandler handler) {
		super(handler);
		getTabs().addAll(new Tab("create", new SSOCreationPane(handler)), new Tab("list", new ListAPI(handler)));
	}

}
