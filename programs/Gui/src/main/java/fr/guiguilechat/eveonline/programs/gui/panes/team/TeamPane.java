package fr.guiguilechat.eveonline.programs.gui.panes.team;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.layout.VBox;

public class TeamPane extends VBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] subPanes;

	@Override
	public EvePane[] subEvePanes() {
		return subPanes;
	}

	protected ListTeamPane listTeam;
	protected ModifTeamPane addTeam;
	protected TeamSystemManager teamSystem;

	public TeamPane(Manager parent) {
		this.parent = parent;
		listTeam = new ListTeamPane(parent);
		addTeam = new ModifTeamPane(parent);
		teamSystem = new TeamSystemManager(parent);
		subPanes = new EvePane[] { listTeam, addTeam, teamSystem };
		getChildren().addAll(listTeam, addTeam, teamSystem);
	}

}
