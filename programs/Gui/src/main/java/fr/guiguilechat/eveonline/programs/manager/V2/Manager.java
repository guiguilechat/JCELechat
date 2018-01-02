package fr.guiguilechat.eveonline.programs.manager.V2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.stage.Stage;

public class Manager extends Application {

	private static final Logger logger = LoggerFactory.getLogger(Manager.class);

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		launch(args);
	}

	protected DataHandler datahandler = new DataHandler();

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.debug("start manager");
		primaryStage.setTitle("guigui lechat manager");
	}

}
