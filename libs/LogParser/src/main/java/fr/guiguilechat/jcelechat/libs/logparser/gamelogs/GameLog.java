package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * represents a log file in Documents/Eve/logs/gamelogs
 * @author
 *
 */
public class GameLog {

	private static final Logger logger = LoggerFactory.getLogger(GameLog.class);

	private static final String ENDHEADER = "------------------------------------------------------------";
	private static final String LISTENER_START = "  Listener: ";
	private static final String SESSION_START = "  Session Started: ";

	private String listener;

	public String listener() {
		return listener;
	}


	static final DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss", Locale.UK);
	static final ZoneId UTC = ZoneId.of("UTC");

	private ZonedDateTime sessionstart;

	public ZonedDateTime sessionStart() {
		return sessionstart;
	}

	private BufferedReader br = null;

	public GameLog(File f) throws IOException {
		br = new BufferedReader(new FileReader(f));
		addHeaders(this, br);
	}

	protected static void addHeaders(GameLog target, BufferedReader br) throws IOException {
		// skip the first ENDHEADER
		br.readLine();
		for (String str = br.readLine(); !ENDHEADER.equals(str); str = br.readLine()) {
			if (str.startsWith(LISTENER_START)) {
				target.listener = str.substring(LISTENER_START.length());
			} else if (str.startsWith(SESSION_START)) {
				String date = str.substring(SESSION_START.length());
				target.sessionstart = LocalDateTime.parse(date, dateParser).atZone(UTC);
			}
		}
	}

	protected List<Consumer<LogLine>> consumers = new ArrayList<>();

	public void follow(Consumer<LogLine> consumer) {
		follow();
		synchronized (consumers) {
			consumers.add(consumer);
		}
	}

	Thread followThread = null;

	protected void follow() {
		if(followThread!=null) {
			return;
		}
		synchronized (this) {
			if(followThread==null) {
				followThread = new Thread(()->{
					while(true) {
						String line = null;
						try {
							line = br.readLine();
						} catch (IOException e) {
						}
						if (line == null) {
							logger.debug("end follow log for " + listener);
							return;
						}
						LogLine logline = LogLine.of(line);
						synchronized (consumers) {
							for (Consumer<LogLine> c : consumers) {
								c.accept(logline);
							}
						}
					}
				});
				followThread.start();
			}
		}
	}

}
