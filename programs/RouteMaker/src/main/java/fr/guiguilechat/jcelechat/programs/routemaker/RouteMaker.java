package fr.guiguilechat.jcelechat.programs.routemaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RouteMaker extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = FXMLLoader.load(getClass().getResource("RouteMaker.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
