package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

public class SelectTeamPane extends ChoiceBox<String> implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public SelectTeamPane(Manager parent) {
		this.parent = parent;
		setMaxWidth(Double.MAX_VALUE);
		setOnAction(this::changeEvt);
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

	@Override
	public void onFocusedTeam(String teamName) {
		oldValue = teamName;
		setValue(teamName);
	}

	@Override
	public void onRenameTeam(String old, String now) {
		if (old.equals(getValue())) {
			setValue(now);
		}
		getItems().remove(old);
		getItems().add(now);
	}

	protected String oldValue = null;

	public void changeEvt(ActionEvent ev) {
		String value = getValue();
		if (value == oldValue || value != null && value.equals(oldValue)) {
			return;
		}
		parent.setFocusedTeam(getValue());
		oldValue = value;
	}

}
