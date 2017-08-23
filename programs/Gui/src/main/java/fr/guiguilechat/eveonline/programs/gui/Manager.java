package fr.guiguilechat.eveonline.programs.gui;

import fr.guiguilechat.eveonline.programs.gui.panes.MenuPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OptionPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OverViewPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public Settings settings = Settings.load(Settings.class);

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
		optionPane.settingsChanged();
		overviewPane.settingsChanged();
	}

}
