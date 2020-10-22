package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.time.ZonedDateTime;

public class LogLine {

	public final ZonedDateTime time;
	public final String type;
	public final String details;

	public LogLine(ZonedDateTime time, String type, String details) {
		this.time = time;
		this.type = type;
		this.details = details;
	}

	public static LogLine of(String line) {
		return null;
	}
	
	

}