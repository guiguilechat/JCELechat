package fr.guiguilechat.eveonline.programs.manager.panes.routes;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.scene.control.TabPane;

public class RoutePane extends TabPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public RoutePane(Manager parent) {
		this.parent = parent;
	}

}
