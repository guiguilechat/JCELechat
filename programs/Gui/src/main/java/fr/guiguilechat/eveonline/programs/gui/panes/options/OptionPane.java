package fr.guiguilechat.eveonline.programs.gui.panes.options;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.Button;
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

	Button switchDebug = new Button("toggle debug");

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
		children = new EvePane[] { listApi, addApi, listTeam, addTeam, teamSystem };
		getChildren().addAll(listApi, addApi, listTeam, addTeam, teamSystem, switchDebug);

		switchDebug.setOnAction(ev -> parent.switchDebug());
	}

}
