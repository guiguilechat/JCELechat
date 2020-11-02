package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.libs.logparser.gamelogs.types.CombatLine;

public class LogLine {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LogLine.class);

	public static enum LOGTYPE {
		question, bounty, hint, warning, combat {
			@Override
			public LogLine parse(ZonedDateTime time, String details) {
				return new CombatLine(time, combat, details);
			}
		},
		None, notify, info, unknown;

		public LogLine parse(ZonedDateTime time, String details) {
			return new LogLine(time, this, details);
		}
	}

	public final ZonedDateTime time;
	public final LOGTYPE type;
	public final String details;

	public LogLine(ZonedDateTime time, LOGTYPE type, String details) {
		this.time = time;
		this.type = type;
		this.details = details;
	}

	private static final Pattern LINEPATTERN = Pattern.compile("\\[ (.{19}) \\] \\((.*?)\\) (.*)");

	/**
	 * attempt to parse a line
	 *
	 * @param line
	 *          the line to parse, can be null
	 * @return
	 */
	public static LogLine of(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		Matcher m = LINEPATTERN.matcher(line);
		if (m.matches()) {
			ZonedDateTime time = LocalDateTime.parse(m.group(1), GameLog.dateParser).atZone(GameLog.UTC);
			LOGTYPE type = LOGTYPE.valueOf(m.group(2));
			String details = m.group(3);
			return type.parse(time, details);
		} else {
			return null;
		}
	}



}