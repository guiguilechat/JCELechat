package fr.guiguilechat.eveonline.programs.manager.panes.team;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** a Pane dedicated to the management of a team */
public class TeamModifPane extends HBox implements EvePane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TeamModifPane.class);

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public final String name;

	protected VBox updateBox = new VBox(10);
	protected VBox systemsBox = new VBox(5);
	protected VBox toonsBox = new VBox(5);

	public TeamModifPane(Manager parent, String name) {
		this.parent = parent;
		this.name = name;
		BorderPane toonBP = new BorderPane(toonsBox);
		toonBP.setTop(new Label("toons"));
		BorderPane sysBP = new BorderPane(systemsBox);
		sysBP.setTop(new Label("systems"));

		VBox renBox = new VBox();
		TextField renTxt = new TextField();
		renTxt.setPromptText("new name");
		Button renBut = new Button("rename");
		renBut.setOnAction(e -> rename(renTxt));
		Button cpBut = new Button("copy");
		cpBut.setOnAction(e -> copy(renTxt));
		renBox.getChildren().addAll(renTxt, new HBox(renBut, cpBut));

		Button delButton = new Button("delete");
		delButton.setOnAction(e -> parent.delTeam(name));

		updateBox.getChildren().addAll(new Label(), renBox, delButton);

		updateBox.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-radius: 10;");
		toonBP.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-radius: 10;");
		sysBP.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-radius: 10;");
		getChildren().addAll(updateBox, toonBP, sysBP);
	}

	public void update() {
		if (!isShown) {
			return;
		}
		updateSystems();
		updateToons();
	}

	protected void updateSystems() {
		systemsBox.getChildren().clear();
		Set<String> systems = parent.getTeamSystemLimit(name);
		for (String sysName : parent.streamTeamPossibleSystems(name).sorted().toArray(String[]::new)) {
			// System.err.println("system " + sysName + " for team " + name);
			CheckBox cb = new CheckBox(sysName);
			cb.setSelected(systems.contains(sysName));
			cb.setOnAction(e -> {
				if (cb.isSelected()) {
					parent.addTeamSystem(name, sysName);
				} else {
					parent.remTeamSystem(name, sysName);
				}
			});
			systemsBox.getChildren().add(cb);
		}
	}

	protected void updateToons() {
		toonsBox.getChildren().clear();
		Set<String> members = parent.settings.teams.get(name).members;
		for (EveChar c : parent.streamChars().sorted((c1, c2) -> c1.name.compareTo(c2.name)).toArray(EveChar[]::new)) {
			CheckBox cb = new CheckBox(c.name);
			cb.setSelected(members.contains(c.name));
			cb.setOnAction(e -> {
				if (cb.isSelected()) {
					parent.add2Team(c.name, name);
				} else {
					parent.del2Team(c.name, name);
				}
			});
			toonsBox.getChildren().add(cb);
		}
	}

	@Override
	public void onAdd2Team(String team, String character) {
		if (isShown && name.equals(team)) {
			updateSystems();
		}
	}

	@Override
	public void onDel2Team(String team, String character) {
		if (isShown && name.equals(team)) {
			updateSystems();
		}
	}

	protected boolean isShown = false;

	@Override
	public void onIsShown(boolean shown) {
		isShown = shown;
		update();
	}

	/**
	 * rename the team with the text contained in the textfield, and clean the
	 * textfield if correct.
	 *
	 * @param fld
	 */
	protected void rename(TextField fld) {
		String newName = fld.getText();
		if (newName == null || newName.length() == 0) {
			return;
		}
		if (parent.renameTeam(name, newName)) {
			fld.setText(null);
		}
	}

	/**
	 * rename the team with the text contained in the textfield, and clean the
	 * textfield if correct.
	 *
	 * @param fld
	 */
	protected void copy(TextField fld) {
		String newName = fld.getText();
		if (newName == null || newName.length() == 0) {
			return;
		}
		if (parent.copyTeam(name, newName)) {
			fld.setText(null);
		}
	}

}
