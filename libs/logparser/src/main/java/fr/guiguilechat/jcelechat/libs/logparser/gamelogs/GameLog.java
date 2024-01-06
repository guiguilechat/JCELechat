package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

	public final File file;
	private BufferedReader br = null;

	public GameLog(File f) throws IOException {
		file = f;
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

	/**
	 * skip all the lines in the file, but the last one.
	 *
	 * @return trying to parse the last line. can return null if no such a last
	 *         line, or last line is invalid
	 */
	public LogLine emptyBuffer() {
		String last = null;
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				last = line;
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		return LogLine.of(last);
	}

	public LogLine readLine() throws IOException {
		String line = br.readLine();
		LogLine ret = LogLine.of(line);
		if (ret == null && line != null && line.length() > 0) {
			logger.debug("could not parse line [" + line + "] from file " + file);
		}
		return ret;
	}


}
