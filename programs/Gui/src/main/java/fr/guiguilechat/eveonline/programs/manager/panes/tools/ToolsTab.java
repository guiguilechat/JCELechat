package fr.guiguilechat.eveonline.programs.manager.panes.tools;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.burners.BurnerPane;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer.InventerPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ToolsTab extends TabPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		Tab selectedTab = getSelectionModel().getSelectedItem();
		return selectedTab != null && selectedTab.getContent() == child;
	}

	BurnerPane bpane;

	InventerPane invPane;

	public ToolsTab(Manager parent) {
		this.parent = parent;
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		getSelectionModel().selectedItemProperty().addListener((ov, old, now) -> {
			if (shown) {
				if (old != null) {
					((EvePane) old.getContent()).propagateIsShown(false);
				}
				if (now != null) {
					((EvePane) now.getContent()).propagateIsShown(true);
				}
			}
		});
		bpane = new BurnerPane(parent);
		invPane = new InventerPane(parent);
		getTabs().addAll(new Tab("burners", bpane), new Tab("invention", invPane));
		children = new EvePane[] { bpane, invPane };
	}

	protected boolean shown = false;

	@Override
	public void onIsShown(boolean shown) {
		this.shown = shown;
	}

}
