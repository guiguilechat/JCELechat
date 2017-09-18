package fr.guiguilechat.eveonline.programs.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.MenuPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OptionPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OverViewPane;
import fr.guiguilechat.eveonline.programs.gui.panes.SelectTeamPane;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application implements EvePane {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public Manager parent() {
		return this;
	}

	public Settings settings = Settings.load(Settings.class);

	public SelectTeamPane selectTeamPane = new SelectTeamPane(this);

	public final ObservableList<APIRoot> apis = FXCollections.observableArrayList();

	public BorderPane mainLayout = new BorderPane();

	public MenuPane menuHBox = new MenuPane(this);

	public OverViewPane overviewPane = new OverViewPane(this);

	public OptionPane optionPane = new OptionPane(this);

	public EvePane[] children = new EvePane[] { menuHBox, overviewPane, optionPane, selectTeamPane };

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
		primaryStage.setTitle("guigui lechat manager");
		mainLayout.setTop(menuHBox);
		if (!settings.hideDebug) {
			mainLayout.setBottom(debugPane);
		}

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

	//
	// event handling
	//

	@Override
	public void onNewAPI(int key, String code) {
		for (APIRoot ar : apis) {
			if (ar.key.keyID == key) {
				return;
			}
		}
		apis.add(new APIRoot(key, code));
		debug("new api " + key);
	}

	@Override
	public void onDelAPI(int key) {
		for (Iterator<APIRoot> it = apis.iterator(); it.hasNext();) {
			if (it.next().key.keyID == key) {
				it.remove();
			}
		}
	}

	// external calls
	// modification of the settings
	//

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

	public void setFocusedTeam(String name) {
		debug("focusing on team " + name);
		settings.focusedTeam = name;
		settings.store();
		propagateFocusedTeam(name);
	}

	// debug

	protected static class DebugEntry {
		String message;
		Class<? extends EvePane> context;
		Date date;
	}

	protected TableView<DebugEntry> debugPane = new TableView<>();
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		TableColumn<DebugEntry, String> dateCol = new TableColumn<>("date");
		dateCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(dateFormat.format(ct.getValue().date)));
		debugPane.getColumns().add(dateCol);
		TableColumn<DebugEntry, String> messCol = new TableColumn<>("debug");
		messCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(ct.getValue().message));
		debugPane.getColumns().add(messCol);
		TableColumn<DebugEntry, String> ctxtCol = new TableColumn<>("context");
		ctxtCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(ct.getValue().context.getSimpleName()));
		debugPane.getColumns().add(ctxtCol);
		dateCol.setSortType(TableColumn.SortType.DESCENDING);
		debugPane.getSortOrder().add(dateCol);
	}

	public void printDebug(Class<? extends EvePane> clazz, String data) {
		DebugEntry de = new DebugEntry();
		de.message = data;
		de.context = clazz;
		de.date = new Date();
		debugPane.getItems().add(de);
	}

	public void switchDebug() {
		settings.hideDebug = !settings.hideDebug;
		mainLayout.setBottom(settings.hideDebug ? null : debugPane);
		settings.store();
	}

}
