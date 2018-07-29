package fr.guiguilechat.jcelechat.programs.manager;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ManagerV2 extends Application {

	private Stage primaryStage;
	private TabPane mainLayout;
	private DataHandler handler = new DataHandler();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		initRoot();
	}

	private void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ManagerV2.class.getResource("panes/ManagerV2.fxml"));
			mainLayout = loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public TabPane getMainLayout() {
		return mainLayout;
	}
}
