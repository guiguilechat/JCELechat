package fr.guiguilechat.eveonline.programs.gui.panes.options;

import java.util.HashMap;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TeamSystemManager extends GridPane implements EvePane {

	private final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public TeamSystemManager(Manager parent) {
		this.parent = parent;
	}

	protected class TeamElements {
		public String name;
		public Label label;
		public ChoiceBox<String> remSystemBox = new ChoiceBox<>();
		public Button remSystemBtn = new Button("rem");
		public ChoiceBox<String> addSystemBox = new ChoiceBox<>();
		public Button addSystemBtn = new Button("add");

		public TeamElements(String name) {
			this.name = name;
			label = new Label(name);
			remSystemBox.setOnAction(this::rem);
			addSystemBox.setOnAction(this::add);
		}

		public void rem(Event e) {
			String system = remSystemBox.getValue();
			if (system != null) {
				if (parent().remTeamSystem(name, system)) {
					remSystemBox.setValue(null);
				}
			}
		}

		public void add(Event e) {
			String system = addSystemBox.getValue();
			if (system != null) {
				if (parent().addTeamSystem(name, system)) {
					addSystemBox.setValue(null);
				}
			}
		}
	}

	protected HashMap<String, TeamElements> cachedTeamElements = new HashMap<>();

	protected TeamElements element(String team) {
		TeamElements ret = cachedTeamElements.get(team);
		if (ret == null) {
			ret = new TeamElements(team);
			cachedTeamElements.put(team, ret);
		}
		return ret;
	}

	/** redraw the full grid */
	public void updateContent() {
		getChildren().clear();
		for (String team : parent.settings.teams.keySet()) {
			addTeam(element(team));
		}
	}

	protected void addTeam(TeamElements team) {
		addRow(0, team.label, team.addSystemBox, team.addSystemBtn, team.remSystemBox, team.remSystemBtn);
	}

	@Override
	public void onDelTeam(String name) {
		TeamElements deleted = cachedTeamElements.remove(name);
		getChildren().removeAll(deleted.label, deleted.addSystemBox, deleted.addSystemBtn, deleted.remSystemBox,
				deleted.remSystemBtn);
	}

	@Override
	public void onNewTeam(String name) {
		TeamElements added = element(name);
		addTeam(added);
	}

	@Override
	public void onAdd2Team(String team, String character) {
		TeamElements element = element(team);
		for (String system : parent().getTeamPossibleSystems(team)) {
			if (parent().getTeamSystemLimit(team).contains(system)) {
				if (!element.remSystemBox.getItems().contains(system)) {
					element.remSystemBox.getItems().add(system);
				}
			} else {
				if (!element.addSystemBox.getItems().contains(system)) {
					element.addSystemBox.getItems().add(system);
				}
			}
		}
		element.addSystemBox.getItems().sort(String::compareTo);
		element.remSystemBox.getItems().sort(String::compareTo);
	}

	@Override
	public void onDel2Team(String team, String character) {
		// TODO Auto-generated method stub
		EvePane.super.onDel2Team(team, character);
	}

	@Override
	public void onAddTeamSystem(String teamName, String sysName) {
		TeamElements element = element(teamName);
		element.addSystemBox.getItems().remove(sysName);
		element.remSystemBox.getItems().add(sysName);
	}

	@Override
	public void onRemTeamSystem(String teamName, String sysName) {
		TeamElements element = element(teamName);
		element.addSystemBox.getItems().add(sysName);
		element.remSystemBox.getItems().remove(sysName);
	}

}
