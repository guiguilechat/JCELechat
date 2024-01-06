package fr.guiguilechat.jcelechat.libs.logparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.logparser.gamelogs.GameLogsLoader;

/**
 * represent the directory for logs in eve. Allows to get specific log systems
 * <ul>
 * <li>{@link #gameLogs()} to follow gthe gamelogs for a character</li>
 * </ul>
 *
 * @author
 *
 */
public class LogsLoader {

	private File baseDir;

	public LogsLoader(String documentsDir) {
		File rootDir = new File(documentsDir, "EVE");
		baseDir = new File(rootDir, "logs");
	}

	public LogsLoader() {
		this(defaultDocumentsDir());
	}

	protected File gameLogsFile() {
		return baseDir == null ? null : new File(baseDir, "Gamelogs");
	}

	private GameLogsLoader gamelogs = null;

	public GameLogsLoader gameLogs() {
		if (gamelogs == null) {
			gamelogs = new GameLogsLoader(gameLogsFile());
		}
		return gamelogs;
	}

	public static String defaultDocumentsDir() {
		File home = new File(System.getProperty("user.home"));

		if (home.exists() && home.isDirectory()) {
			List<File> possibleDocDir = new ArrayList<>();
			for (File subfile : home.listFiles()) {
				if (subfile.isDirectory() && subfile.getName().contains("Doc")) {
					possibleDocDir.add(subfile);
				}
			}
			if (possibleDocDir.size() == 1) {
				return possibleDocDir.get(0).getAbsolutePath();
			} else {
				System.err.println("can't find doc file among " + possibleDocDir);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		LogsLoader loader = new LogsLoader();
		System.out.println("gamelogs is " + loader.gameLogsFile());
		loader.gameLogs().observe();
		while (true) {
			Thread.yield();
		}
	}
}
