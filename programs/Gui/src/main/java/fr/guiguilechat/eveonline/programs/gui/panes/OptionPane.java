package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.layout.VBox;

public class OptionPane extends VBox implements EvePane {

	protected Manager parent;

	ListApiTableView listApi;
	AddAPIPane addApi;
	ListTeamPane listTeam;
	AddTeamPane addTeam;

	EvePane[] children;

	@Override
	public EvePane[] children() {
		return children;
	}

	public OptionPane(Manager parent) {
		this.parent = parent;
		listApi = new ListApiTableView(parent);
		addApi = new AddAPIPane(parent);
		listTeam = new ListTeamPane(parent);
		addTeam = new AddTeamPane(parent);
		children = new EvePane[] { listApi, addApi, listTeam, addTeam };
		getChildren().addAll(listApi, addApi, listTeam, addTeam);
	}

}
