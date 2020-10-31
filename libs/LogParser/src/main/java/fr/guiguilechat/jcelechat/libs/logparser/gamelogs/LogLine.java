package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogLine {

	private static final Logger logger = LoggerFactory.getLogger(LogLine.class);

	public final ZonedDateTime time;
	public final String type;
	public final String details;

	public LogLine(ZonedDateTime time, String type, String details) {
		this.time = time;
		this.type = type;
		this.details = details;
	}

	private static final Pattern LINEPATTERN = Pattern.compile("\\[ (.{19}) \\] \\((.*)\\) (.*)");

	public static LogLine of(String line) {
		Matcher m = LINEPATTERN.matcher(line);
		if (m.matches()) {
			ZonedDateTime time = LocalDateTime.parse(m.group(1), GameLog.dateParser).atZone(GameLog.UTC);
			return new LogLine(time, m.group(2), m.group(3));
		} else {
			logger.debug("line not matching " + line);
		}
		return null;
	}



}