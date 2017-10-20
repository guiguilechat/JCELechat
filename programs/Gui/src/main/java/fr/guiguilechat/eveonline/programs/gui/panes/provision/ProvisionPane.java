package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.SelectTeamPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ProvisionPane extends BorderPane implements EvePane {

	private static final Logger logger = LoggerFactory.getLogger(ProvisionPane.class);

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected GridPane selectPane = new GridPane();
	protected Accordion accordion;

	protected ProvisionLPStorePane lpstore;
	protected ProvisionOverview overview;

	protected SelectTeamPane selectTeam;

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public ProvisionPane(Manager parent) {
		this.parent = parent;
		selectPane.add(new Label("team :"), 0, 0);
		selectTeam = new SelectTeamPane(parent);
		selectPane.add(selectTeam, 1, 0);
		setTop(selectPane);

		lpstore = new ProvisionLPStorePane(parent);
		overview = new ProvisionOverview(parent);
		TitledPane op = new TitledPane("overview", overview);
		TitledPane lp = new TitledPane("lpstore", lpstore);
		accordion = new Accordion(lp, op);
		accordion.expandedPaneProperty().addListener((ov, old, now) -> {
			if (now == lp) {
				lpstore.load();
			} else if (now == op) {
				overview.load();
			}
		});
		setCenter(accordion);
		children = new EvePane[] { selectTeam, lpstore, overview };
	}

	public void showLPStore() {
		lpstore.load();
		setCenter(lpstore);
	}

	public void showOverview() {
		overview.load();
		setCenter(overview);
	}

}
