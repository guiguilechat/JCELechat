package fr.guiguilechat.jcelechat.programs.notiftoons;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.lelouet.tools.application.yaml.YamlSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NotifToons extends Application {

	private static final Logger logger = LoggerFactory.getLogger(NotifToons.class);

	public static void start(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 10;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		launch(args);
	}

	private NotifToonsSettings settings;

	private List<ESIAccount> accounts = new ArrayList<>();

	public NotifToons() {
		settings = YamlSettings.load(NotifToonsSettings.class);
		accounts = settings.accounts();
	}

	protected BorderPane mainLayout = new BorderPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.info("start notiftoons");
		primaryStage.setTitle("guigui lechat toons notifications");
		ActivityPane ap = new ActivityPane(accounts, settings).build();
		mainLayout.setCenter(ap);
		mainLayout.setTop(new NotifOptionsPane(settings, ap::refreshList).build());
		Scene scene = new Scene(mainLayout, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
