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

/**
 * represents a log file in Documents/Eve/logs/gamelogs
 * @author
 *
 */
public class GameLog {

	private static final String ENDHEADER = "------------------------------------------------------------";
	private static final String LISTENER_START = "  Listener: ";
	private static final String SESSION_START = "  Session Started: ";

	private String listener;

	public String listener() {
		return listener;
	}


	private static final DateTimeFormatter dateloader = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss", Locale.UK);
	private static final ZoneId UTC = ZoneId.of("UTC");

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
		br.readLine();
		for (String str = br.readLine(); !ENDHEADER.equals(str); str = br.readLine()) {
			if (str.startsWith(LISTENER_START)) {
				target.listener = str.substring(LISTENER_START.length());
			} else if (str.startsWith(SESSION_START)) {
				String date = str.substring(SESSION_START.length());
				target.sessionstart = LocalDateTime.parse(date, dateloader).atZone(UTC);
			}
		}

	}

}
