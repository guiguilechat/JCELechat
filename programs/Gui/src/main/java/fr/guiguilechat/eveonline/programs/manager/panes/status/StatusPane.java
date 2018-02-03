package fr.guiguilechat.eveonline.programs.manager.panes.status;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;

/**
 * an overview pane contains a table of eventdata.
 */
public class StatusPane extends BorderPane implements EvePane {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StatusPane.class);

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected JobPane jobpane;
	protected ProvisionPane provisionpane;
	protected ShopPane shoppane;
	TabPane tabs = new TabPane();

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public StatusPane(Manager parent) {
		this.parent = parent;

		jobpane = new JobPane(parent);
		shoppane = new ShopPane(parent);
		provisionpane = new ProvisionPane(parent);
		children = new EvePane[] { jobpane, shoppane, provisionpane };

		Tab tjobs = new Tab("jobs", jobpane);
		Tab tshop = new Tab("shop", shoppane);
		Tab tprovision = new Tab("provision", provisionpane);
		tabs = new TabPane(tjobs, tshop, tprovision);
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabs.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> {
			if (old != null) {
				((EvePane) old.getContent()).propagateIsShown(false);
			}
			if (now != null) {
				((EvePane) now.getContent()).propagateIsShown(true);
			}
		});

		setCenter(tabs);
		// getChildren().addAll(selectTeamPane, tpjobs, tpprovi);
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		return tabs.getSelectionModel().getSelectedItem().getContent() == child;
	}

}
