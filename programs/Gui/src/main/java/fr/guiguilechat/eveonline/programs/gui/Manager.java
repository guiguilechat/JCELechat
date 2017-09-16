package fr.guiguilechat.eveonline.programs.gui;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.MenuPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OptionPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OverViewPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application implements EvePane {

	public static void main(String[] args) {
		launch(args);
	}

	public Settings settings = Settings.load(Settings.class);

	public final ObservableList<APIRoot> apis = FXCollections.observableArrayList();

	BorderPane mainLayout = new BorderPane();

	MenuPane menuHBox = new MenuPane(this);

	OverViewPane overviewPane = new OverViewPane(this);

	OptionPane optionPane = new OptionPane(this);

	EvePane[] children = new EvePane[] { menuHBox, overviewPane, optionPane };

	@Override
	public EvePane[] children() {
		return children;
	}

	public void showOptions() {
		mainLayout.setCenter(optionPane);
	}

	public void showOverview() {
		mainLayout.setCenter(overviewPane);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("eve toon manager");
		mainLayout.setTop(menuHBox);

		Scene scene = new Scene(mainLayout, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
		showOverview();
		for (Entry<Integer, String> a : settings.apiKeys.entrySet()) {
			propagateNewAPI(a.getKey(), a.getValue());
		}
		for (String team : settings.teams.keySet()) {
			propagateNewTeam(team);
			for (String charname : settings.teams.get(team)) {
				propagateAdd2Team(team, charname);
			}
		}
	}

	@Override
	public void onNewAPI(int key, String code) {
		for (APIRoot ar : apis) {
			if(ar.key.keyID==key) {
				return;
			}
		}
		apis.add(new APIRoot(key, code));
	}

	@Override
	public void onDelAPI(int key) {
		for (Iterator<APIRoot> it = apis.iterator(); it.hasNext();) {
			if (it.next().key.keyID == key) {
				it.remove();
			}
		}
	}

	// modification of the settings

	// API

	public void removeApi(int keyID) {
		settings.apiKeys.remove(keyID);
		settings.store();
		propagateDelAPI(keyID);
	}

	public void addAPI(int key, String code) {
		settings.apiKeys.put(key, code);
		settings.store();
		propagateNewAPI(key, code);
	}

	// team

	public void addTeam(String name) {
		settings.teams.put(name, new LinkedHashSet<>());
		settings.store();
		propagateNewTeam(name);
	}

	public void delTeam(String name) {
		settings.teams.remove(name);
		settings.store();
		propagateDelTeam(name);

	}

	public void add2Team(String character, String team) {
		settings.teams.get(team).add(character);
		settings.store();
		propagateAdd2Team(team, character);
	}

	public void del2Team(String character, String team) {
		settings.teams.get(team).remove(character);
		settings.store();
		propagateDel2Team(team, character);
	}

}
