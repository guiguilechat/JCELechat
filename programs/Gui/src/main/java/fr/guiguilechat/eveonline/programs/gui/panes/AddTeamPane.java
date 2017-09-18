package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddTeamPane extends HBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	TextField addTeamField = new TextField();
	Button addTeamButton = new Button("add team");
	ChoiceBox<String> delTeamBox = new ChoiceBox<>(FXCollections.observableArrayList());
	Button delTeamButton = new Button("del team");

	public AddTeamPane(Manager parent) {
		this.parent = parent;
		addTeamField.setPromptText("team name");
		getChildren().addAll(addTeamField, addTeamButton, new Label("   "), delTeamBox, delTeamButton);
		addTeamButton.setOnAction(this::addTeam);
		delTeamButton.setOnAction(this::delTeam);
	}

	public void addTeam(Event e) {
		String name = addTeamField.getText();
		if (!parent.settings.teams.containsKey(name)) {
			parent.addTeam(name);
			addTeamField.clear();
		}
	}

	public void delTeam(Event e) {
		String name = delTeamBox.getValue();
		if (name != null) {
			parent.delTeam(name);
		}
	}

	@Override
	public void onNewTeam(String name) {
		delTeamBox.getItems().add(name);
	}

	@Override
	public void onDelTeam(String name) {
		delTeamBox.getItems().remove(name);
	}

}
