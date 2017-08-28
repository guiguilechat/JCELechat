package fr.guiguilechat.eveonline.programs.gui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.panes.MenuPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OptionPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OverViewPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public Settings settings = Settings.load(Settings.class);

	public final ObservableList<APIRoot> apis = FXCollections.observableArrayList();

	BorderPane mainLayout = new BorderPane();

	MenuPane menuHBox = new MenuPane(this);

	OverViewPane overviewPane = new OverViewPane(this);

	OptionPane optionPane = new OptionPane(this);

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

		Scene scene = new Scene(mainLayout, 640, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
		showOverview();
		settingsChanged();
	}

	public void settingsChanged() {

		// remove all apis that are not exactly present
		Iterator<APIRoot> it = apis.iterator();
		while (it.hasNext()) {
			APIRoot api = it.next();
			String storedCode = settings.apiKeys.get(api.key.keyID);
			if (storedCode == null || !api.key.code.equals(storedCode)) {
				it.remove();
			}
		}
		// add all apis that are not already present
		Set<Integer> missingKeys = new HashSet<>(settings.apiKeys.keySet());
		missingKeys.removeAll(apis.stream().map(r -> r.key.keyID).collect(Collectors.toSet()));
		for (Integer apiID : missingKeys) {
			String code = settings.apiKeys.get(apiID);
			apis.add(new APIRoot(apiID, code));
		}

		optionPane.settingsChanged();
		overviewPane.settingsChanged();
	}

}
