package fr.guiguilechat.eveonline.programs.gui.panes.team;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ModifTeamPane extends HBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	TextField addTeamField = new TextField();
	Button addTeamButton = new Button("add");
	ChoiceBox<String> delTeamBox = new ChoiceBox<>();
	Button delTeamButton = new Button("delete");
	ChoiceBox<String> copyTeamBox = new ChoiceBox<>();
	TextField copyTeamField = new TextField();
	Button copyTeamBtn = new Button("copy");
	ChoiceBox<String> renameTeamBox = new ChoiceBox<>();
	TextField renameTeamField = new TextField();
	Button renameTeamBtn = new Button("rename");

	public ModifTeamPane(Manager parent) {
		this.parent = parent;
		setStyle("-fx-border-color: black");
		addTeamField.setPromptText("name");
		addTeamField.setMaxWidth(60);
		addTeamButton.setOnAction(this::addTeam);
		copyTeamField.setPromptText("name");
		copyTeamField.setMaxWidth(60);
		copyTeamBtn.setOnAction(this::copyTeam);
		delTeamButton.setOnAction(this::delTeam);
		renameTeamField.setPromptText("name");
		renameTeamField.setMaxWidth(60);
		renameTeamBtn.setOnAction(this::renameTeam);
		getChildren().addAll(addTeamField, addTeamButton, new Label("   "), delTeamBox, delTeamButton, new Label("   "),
				copyTeamBox, copyTeamField, copyTeamBtn, new Label("   "), renameTeamBox, renameTeamField, renameTeamBtn);
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

	public void renameTeam(Event e) {
		String oldName = renameTeamBox.getValue();
		String newName = renameTeamField.getText();
		if (parent().renameTeam(oldName, newName)) {
			renameTeamField.setText("");
		}
	}

	public void copyTeam(Event e) {
		String oldName = copyTeamBox.getValue();
		String newName = copyTeamField.getText();
		if (parent().copyTeam(oldName, newName)) {
			copyTeamField.setText("");
		}

	}

	@Override
	public void onNewTeam(String name) {
		delTeamBox.getItems().add(name);
		renameTeamBox.getItems().add(name);
		copyTeamBox.getItems().add(name);
	}

	@Override
	public void onDelTeam(String name) {
		delTeamBox.getItems().remove(name);
		renameTeamBox.getItems().remove(name);
		copyTeamBox.getItems().remove(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRenameTeam(String old, String now) {
		for (ChoiceBox<String> c : new ChoiceBox[] { copyTeamBox, renameTeamBox, delTeamBox }) {
			if (old.equals(c.getValue())) {
				c.setValue(now);
			}
			c.getItems().remove(old);
			c.getItems().add(now);
		}
	}

}
