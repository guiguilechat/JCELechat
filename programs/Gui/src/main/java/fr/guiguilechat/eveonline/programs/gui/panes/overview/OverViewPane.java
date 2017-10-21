package fr.guiguilechat.eveonline.programs.gui.panes.overview;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.SelectTeamPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * an overview pane contains a table of eventdata.
 */
public class OverViewPane extends VBox implements EvePane {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OverViewPane.class);

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected SelectTeamPane selectTeamPane;
	protected JobPane jobpane;
	protected ProvisionPane provisionpane;

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public OverViewPane(Manager parent) {
		this.parent = parent;
		selectTeamPane = new SelectTeamPane(parent);
		jobpane = new JobPane(parent);
		provisionpane = new ProvisionPane(parent);
		children = new EvePane[] { selectTeamPane, jobpane, provisionpane };
		TitledPane tpjobs = new TitledPane("jobs", jobpane);
		tpjobs.setExpanded(false);
		tpjobs.expandedProperty().addListener((o, old, now) -> jobpane.setShown(now));
		TitledPane tpprovi = new TitledPane("provisions", provisionpane);
		tpprovi.setExpanded(false);
		tpprovi.expandedProperty().addListener((o, old, now) -> provisionpane.setShown(now));
		getChildren().addAll(selectTeamPane, tpjobs, tpprovi);
	}

}
