package fr.guiguilechat.eveonline.programs.manager.panes;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.TeamDescription;
import javafx.collections.MapChangeListener;
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
		parent.settings.teams().addListener((MapChangeListener<String, TeamDescription>) change -> {
			if (change.wasRemoved()) {
				getItems().remove(change.getKey());
			}
			if (change.wasAdded()) {
				getItems().add(change.getKey());
			}
		});
	}

	@Override
	public void onFocusedTeam(String teamName) {
		oldValue = teamName;
		setValue(teamName);
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
