package fr.guiguilechat.eveonline.programs.gui.panes.options;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;

public class OptionPane extends VBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected ListApiTableView listApi;
	protected AddAPIPane addApi;
	protected ListTeamPane listTeam;
	protected ModifTeamPane addTeam;
	protected TeamSystemManager teamSystem;
	protected Tab tabAPI, tabTeams;
	protected TabPane tabs;

	protected EvePane[] subPanes;

	@Override
	public EvePane[] subEvePanes() {
		return subPanes;
	}

	public OptionPane(Manager parent) {
		this.parent = parent;
		listApi = new ListApiTableView(parent);
		addApi = new AddAPIPane(parent);
		listTeam = new ListTeamPane(parent);
		addTeam = new ModifTeamPane(parent);
		teamSystem = new TeamSystemManager(parent);
		tabAPI = new Tab("api keys", new VBox(listApi, addApi));
		tabTeams = new Tab("teams", new VBox(listTeam, addTeam, teamSystem));
		subPanes = new EvePane[] { listApi, addApi, listTeam, addTeam, teamSystem };
		tabs = new TabPane(tabAPI, tabTeams);
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		getChildren().add(tabs);
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		for (Tab t : new Tab[] { tabAPI, tabTeams }) {
			if(t.isSelected()) {
				return t.getContent()==child;
			}
		}
		return false;
	}

}
