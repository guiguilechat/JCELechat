package fr.guiguilechat.eveonline.programs.manager.panes.team;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;

public class TeamsPane extends BorderPane implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] subPanes;

	@Override
	public EvePane[] subEvePanes() {
		return team2pane.values().toArray(new EvePane[0]);
	}

	protected AddTeamPane addTeam;
	protected TabPane tabs;
	protected Map<String, TeamModifPane> team2pane = new HashMap<>();

	public TeamsPane(Manager parent) {
		this.parent = parent;
		addTeam = new AddTeamPane(parent);
		setTop(addTeam);
		tabs = new TabPane();
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabs.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> {
			if (old != null) {
				((EvePane) old.getContent()).propagateIsShown(false);
			}
			if (now != null && isShown) {
				TeamModifPane tmp = (TeamModifPane) now.getContent();
				parent().setFocusedTeam(tmp.name);
				((EvePane) now.getContent()).propagateIsShown(true);
			}
		});
		setCenter(tabs);
	}

	protected TeamModifPane getTeamPane(String teamName) {
		TeamModifPane ret = team2pane.get(teamName);
		if (ret == null) {
			ret = new TeamModifPane(parent, teamName);
			team2pane.put(teamName, ret);
		}
		return ret;
	}

	@Override
	public void onFocusedTeam(String teamName) {
		for (Tab tab : tabs.getTabs()) {
			TeamModifPane tmp = (TeamModifPane) tab.getContent();
			if (tmp.name.equals(teamName)) {
				tabs.getSelectionModel().select(tab);
			}
		}
	}

	@Override
	public void onNewTeam(String name) {
		tabs.getTabs().add(new Tab(name, getTeamPane(name)));
		tabs.getTabs().sort((t1, t2) -> t1.getText().compareTo(t2.getText()));
	}

	@Override
	public void onDelTeam(String name) {
		TeamModifPane del = team2pane.remove(name);
		if (del != null) {
			tabs.getTabs().removeIf(t -> t.getContent() == del);
		}
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		return tabs.getSelectionModel().getSelectedItem().getContent() == child;
	}

	@Override
	public void onRenameTeam(String old, String now) {
		tabs.getTabs().removeIf(t -> t.getText().equals(old));
		onNewTeam(now);
	}

	protected boolean isShown = false;

	@Override
	public void onIsShown(boolean shown) {
		isShown = true;
	}

}
