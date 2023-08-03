package fr.guiguilechat.jcelechat.programs.notiftoons;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.programs.notiftoons.NotifToonsSettings.Ssokey;
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
		accounts = new ArrayList<>();
		for (Ssokey devkey : settings.getSsokeys()) {
			for (String refreshtoken : devkey.getRefreshTokens()) {
				ESIAccount acc = new ESIAccount(refreshtoken, devkey.getBase64());
				try {
					logger.info("added connection for " + acc.name());
					accounts.add(acc);
				} catch (Exception e) {
					logger.error("while checking access b64=" + devkey.getBase64() + " refreshtoken=" + refreshtoken, e);
				}
			}
		}
	}

	protected BorderPane mainLayout = new BorderPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.info("start notiftoons");
		primaryStage.setTitle("guigui lechat toons notifications");
		mainLayout.setCenter(new ActivityPane(accounts).build());
		Scene scene = new Scene(mainLayout, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
