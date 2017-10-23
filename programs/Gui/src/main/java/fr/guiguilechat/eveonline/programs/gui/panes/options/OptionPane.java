package fr.guiguilechat.eveonline.programs.gui.panes.options;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class OptionPane extends VBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	ListApiTableView listApi;
	AddAPIPane addApi;
	ListTeamPane listTeam;
	ModifTeamPane addTeam;
	TeamSystemManager teamSystem;
	TitledPane tpapi, tpteams;

	EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public OptionPane(Manager parent) {
		this.parent = parent;
		listApi = new ListApiTableView(parent);
		addApi = new AddAPIPane(parent);
		listTeam = new ListTeamPane(parent);
		addTeam = new ModifTeamPane(parent);
		teamSystem = new TeamSystemManager(parent);
		tpapi = new TitledPane("api keys", new VBox(listApi, addApi));
		tpapi.setExpanded(false);
		tpteams = new TitledPane("teams", new VBox(listTeam, addTeam, teamSystem));
		tpteams.setExpanded(false);
		children = new EvePane[] { listApi, addApi, listTeam, addTeam, teamSystem };
		getChildren().addAll(tpapi, tpteams);
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		if (tpteams.isExpanded()) {
			if (child == listTeam || child == addTeam || child == teamSystem) {
				return true;
			}
		}
		if (tpapi.isExpanded()) {
			if (child == listApi || child == addApi) {
				return true;
			}
		}
		return false;
	}

}
