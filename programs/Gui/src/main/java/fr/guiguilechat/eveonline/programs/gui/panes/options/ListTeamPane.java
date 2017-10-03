package fr.guiguilechat.eveonline.programs.gui.panes.options;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.database.apiv2.Account.Character;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.options.ListTeamPane.CharacterTeams;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Present the teams and give availability to assign team to character
 * <p>
 * column (starting 1) is the team ; row(starting 1) is the character.
 * </p>
 */
public class ListTeamPane extends TableView<CharacterTeams> implements EvePane {

	private final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	@SuppressWarnings("serial")
	public static class CharacterTeams extends HashSet<String> {
		public String charName;

		public CharacterTeams(String charName) {
			this.charName = charName;
		}
	}

	ObservableList<CharacterTeams> charteams = FXCollections.observableArrayList();

	HashMap<String, TableColumn<CharacterTeams, CharacterTeams>> team2col = new HashMap<>();

	public ListTeamPane(Manager parent) {
		this.parent = parent;
		setItems(charteams);
		TableColumn<CharacterTeams, String> keyCol = new TableColumn<>("character");
		keyCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(ct.getValue().charName));
		getColumns().add(keyCol);
	}

	@Override
	public void onNewAPI(int key, String code) {
		APIRoot aroot = parent.apis.stream().filter(r -> r.key.keyID == key).findFirst().get();
		for (Character c : aroot.account.characters()) {
			charteams.add(new CharacterTeams(c.name));
			debug("toon " + c.name);
		}
	}

	@Override
	public void onDelAPI(int key) {
		APIRoot aroot = parent.apis.stream().filter(r -> r.key.keyID == key).findFirst().get();
		Set<String> chars = aroot.account.characters().stream().map(c -> c.name).collect(Collectors.toSet());
		charteams.removeIf(ct -> chars.contains(ct.charName));
	}

	@Override
	public void onNewTeam(String teamName) {
		TableColumn<CharacterTeams, CharacterTeams> teamCol = new TableColumn<>(teamName);
		teamCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		teamCol.setCellFactory(param -> new TableCell<CharacterTeams, CharacterTeams>() {
			private final CheckBox cb = new CheckBox();

			@Override
			protected void updateItem(CharacterTeams cteam, boolean empty) {
				super.updateItem(cteam, empty);

				if (cteam == null) {
					setGraphic(null);
					return;
				}

				setGraphic(cb);
				cb.setAllowIndeterminate(false);
				cb.setSelected(cteam.contains(teamName));
				cb.setOnAction(event -> {
					if (cb.isSelected()) {
						parent.add2Team(cteam.charName, teamName);
					} else {
						parent.del2Team(cteam.charName, teamName);
					}
				});
			}
		});
		getColumns().add(teamCol);
		team2col.put(teamName, teamCol);
	}

	@Override
	public void onDelTeam(String name) {
		TableColumn<CharacterTeams, CharacterTeams> teamcol = team2col.get(name);
		getColumns().remove(teamcol);
		team2col.remove(name);
	}

	@Override
	public void onRenameTeam(String old, String now) {
		team2col.put(now, team2col.get(old));
		team2col.remove(old);
	}

	@Override
	public void onAdd2Team(String team, String character) {
		for (CharacterTeams ct : charteams) {
			if (ct.charName.equals(character)) {
				ct.add(team);
				refresh();
			}
		}
		debug("toon " + character + " added to team " + team);
	}

	@Override
	public void onDel2Team(String team, String character) {
		for (CharacterTeams ct : charteams) {
			if (ct.charName.equals(character)) {
				ct.remove(team);
				refresh();
			}
		}
		debug("toon " + character + " removed from team " + team);
	}

}
