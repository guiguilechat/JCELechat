package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.SelectTeamPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class ProvisionPane extends BorderPane implements EvePane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProvisionPane.class);

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected TabPane tabs;
	protected Tab orderedtab, lpstoretab, bpotab;

	protected ProvisionLPStorePane lpstore;
	protected ProvisionOrderedPane ordered;
	protected ProvisionBlueprint blueprint;

	protected SelectTeamPane selectTeam;

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public ProvisionPane(Manager parent) {
		this.parent = parent;
		selectTeam = new SelectTeamPane(parent);
		setTop(selectTeam);

		lpstore = new ProvisionLPStorePane(parent);
		ordered = new ProvisionOrderedPane(parent);
		blueprint = new ProvisionBlueprint(parent);
		orderedtab = new Tab("ordered", ordered);
		lpstoretab = new Tab("lpstore", lpstore);
		bpotab = new Tab("bpos", blueprint);
		tabs = new TabPane(orderedtab, lpstoretab, bpotab);
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabs.getSelectionModel().selectedItemProperty().addListener((ov, old, now) -> {
			if (old != null) {
				((EvePane) old.getContent()).propagateIsShown(false);
			}
			if (now != null) {
				((EvePane) now.getContent()).propagateIsShown(true);
			}
		});
		setCenter(tabs);
		children = new EvePane[] { selectTeam, lpstore, ordered, blueprint };
	}

	protected EvePane TP2Pane(TitledPane tp) {
		return tp == null ? null : (EvePane) tp.getContent();
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		return tabs.getSelectionModel().getSelectedItem().getContent() == child;
	}

}
