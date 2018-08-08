package fr.guiguilechat.jcelechat.programs.soboplace;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SoBoPlace extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = FXMLLoader.load(getClass().getResource("soboplace.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
