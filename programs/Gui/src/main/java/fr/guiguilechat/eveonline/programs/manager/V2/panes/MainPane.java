package fr.guiguilechat.eveonline.programs.manager.V2.panes;

import java.util.Map.Entry;

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import fr.guiguilechat.eveonline.programs.manager.V2.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.V2.panes.apikeys.APIPane;
import fr.guiguilechat.eveonline.programs.manager.V2.panes.managed.ManagedTab;
import javafx.geometry.Side;
import javafx.scene.control.Tab;

public class MainPane extends ManagedTab {

	public MainPane(DataHandler handler) {
		super(handler);
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		setSide(Side.LEFT);
		getTabs().add(new Tab("apis", new APIPane(handler)));

		for (Entry<String, String> c : handler.settings().ssoKeys.entrySet()) {
			handler.apis.add(new ESIConnection(c.getKey(), c.getValue()));
		}
	}

}
