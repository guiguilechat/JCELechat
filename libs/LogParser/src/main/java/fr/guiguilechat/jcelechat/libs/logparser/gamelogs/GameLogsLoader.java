package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.libs.logparser.LogsLoader;

/**
 * detects new gamelog in the gamelogs folder.
 * <ul>
 * <li>list the existing log files, potentially with a filter to avoid loading
 * some files, with {@link #apply(FileFilter, Consumer)}. This will iterate over
 * the log files and pass them to the consumer</li>
 * <li>listen for log files for a specific listener only using
 * {@link #follow(String, Consumer)}. In that case new lines will be sent to the
 * consumer</li>
 * <li>listen to all log files using {@link #follow(BiConsumer)}. New lines
 * added to the log will be transmitted when they are correct.</li>
 * </ul>
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
		observe();
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
		try {
			WatchService watcher = null;
			Path dir = null;
			watcher = FileSystems.getDefault().newWatchService();
			dir = Path.of(baseDir.getAbsolutePath());
			dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
			while (isObserving && watcher != null) {
				WatchKey key;
				key = watcher.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}
					@SuppressWarnings("unchecked")
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path filename = ev.context();
					File path = dir.resolve(filename).toFile().getAbsoluteFile();
					GameLog log = loadedFiles.get(path);
					if (log == null && path.getName().endsWith(".txt")) {
						log = new GameLog(path);
						loadedFiles.put(path, log);
						LogLine lastLine = log.emptyBuffer();
						transmit(log.listener(), lastLine);
					}
					if (log != null) {
						LogLine line;
						while ((line = log.readLine()) != null) {
							transmit(log.listener(), line);
						}
					}
				}
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
				Thread.yield();
			}
		} catch (Exception e) {
			logger.debug("exiting oberve", e);
		} finally {
			loadedFiles.clear();
			logger.debug("exiting Gamelogs observation");
		}
	}

	protected void transmit(String listener, LogLine line) {
		if (listener == null || line == null) {
			return;
		}
		synchronized (accountFollowers) {
			for (Consumer<LogLine> c : accountFollowers.computeIfAbsent(listener, s -> Collections.emptyList())) {
				c.accept(line);
			}
		}
		synchronized (allFollowers) {
			for (BiConsumer<String, LogLine> b : allFollowers) {
				b.accept(listener, line);
			}
		}
	}

	private HashMap<String, List<Consumer<LogLine>>> accountFollowers = new HashMap<>();

	public void follow(String listener, Consumer<LogLine> cons) {
		synchronized (accountFollowers) {
			accountFollowers.computeIfAbsent(listener, l -> new ArrayList<>()).add(cons);
		}
	}

	private ArrayList<BiConsumer<String, LogLine>> allFollowers = new ArrayList<>();

	public void follow(BiConsumer<String, LogLine> cons) {
		synchronized (allFollowers) {
			allFollowers.add(cons);
		}
	}

	protected File[] list(FileFilter filter) {
		return baseDir.listFiles(f -> f.getName().endsWith(".txt") && (filter == null || filter.accept(f)));
	}

	public void apply(FileFilter filter, Consumer<GameLog> c) {
		for (File f : list(filter)) {
			try {
				c.accept(new GameLog(f));
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
	}

	public static void main(String[] args) {
		Set<String> names = Stream.of(args).collect(Collectors.toSet());
		System.err.println("showing logs for " + names);
		GameLogsLoader gl = new LogsLoader().gameLogs();
		for (String name : names) {
			gl.follow(name, line -> {
				System.out.println(line.type + "\t" + line.details);
			});
		}
		while (true) {
			Thread.yield();
		}
	}

}
