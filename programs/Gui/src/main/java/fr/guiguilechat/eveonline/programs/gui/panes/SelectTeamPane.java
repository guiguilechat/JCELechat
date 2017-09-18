package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.control.ChoiceBox;

public class SelectTeamPane extends ChoiceBox<String> implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public SelectTeamPane(Manager parent) {
		this.parent = parent;
		setOnAction(ev -> parent.setFocusedTeam(getValue()));
		setAccessibleText("select the team to focus on");
	}

	@Override
	public void onNewTeam(String name) {
		getItems().add(name);
	}

	@Override
	public void onDelTeam(String name) {
		getItems().remove(name);
	}

}
