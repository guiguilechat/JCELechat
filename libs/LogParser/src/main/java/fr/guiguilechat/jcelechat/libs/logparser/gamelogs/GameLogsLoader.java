package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * detects new gamelog in the gamelogs folder
 *
 * @author
 *
 */
public class GameLogsLoader {

	private static final Logger logger = LoggerFactory.getLogger(GameLogsLoader.class);

	private File baseDir;

	public GameLogsLoader(File dir) {
		baseDir = dir;
		logger.debug("gamlogsloader dir=" + dir.getAbsolutePath());
		if (!dir.exists()) {
			logger.warn("file " + dir.getAbsolutePath() + " does not exist");
		} else if (!dir.isDirectory()) {
			logger.warn("file " + dir.getAbsolutePath() + " is not directory");
		}
	}

	String recentPrefixToday = DateTimeFormatter.ofPattern("YYYYMMdd").format(LocalDateTime.now());
	String recentPrefixYesterday = DateTimeFormatter.ofPattern("YYYYMMdd")
			.format(LocalDateTime.now().minus(1, ChronoUnit.DAYS));

	FilenameFilter fnf = (dir, name) -> name.endsWith(".txt")
			&& (name.startsWith(recentPrefixToday) || name.startsWith(recentPrefixYesterday));

	public File[] listRecentFile() {
		return baseDir.listFiles(fnf);
	}

	private boolean isObserving = false;

	public synchronized void observe() {
		if (isObserving) {
			return;
		}
		isObserving = true;
		new Thread(this::observeLoop).start();
	}

	private HashMap<File, GameLog> loadedFiles = new HashMap<>();

	protected void observeLoop() {
		while (isObserving) {
			synchronized (this) {
				File[] files = listRecentFile();
				Arrays.sort(files, Comparator.comparing(file -> file.lastModified()));
				for (File file : files) {
					if (!loadedFiles.containsKey(file)) {
						try {
							GameLog gl = new GameLog(file);
							loadedFiles.put(file, gl);
							handleNewGamelog(gl);
						} catch (IOException e) {
							throw new UnsupportedOperationException("catch this", e);
						}
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new UnsupportedOperationException("catch this", e);
				}
			}
		}
	}

	protected void handleNewGamelog(GameLog gl) {
		logger.info("loaded new gamelog " + gl.listener() + " start " + gl.sessionStart());
	}

}
