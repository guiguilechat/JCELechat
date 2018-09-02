package fr.guiguilechat.jcelechat.programs.insurancefraud;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InsuranceFraud extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL murl = getClass().getResource(getClass().getSimpleName() + ".fxml");
		BorderPane root = FXMLLoader.load(murl);
		Scene scene = new Scene(root);
		primaryStage.setTitle(getClass().getSimpleName());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
