package fr.guiguilechat.jcelechat.jcesi.request.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public record RateLimitations(int windowDurationS, int windowTokens) {
	private static final Pattern p = Pattern.compile("^(\\d+)/(\\d+)([hm])$");

	public static RateLimitations parse(String spec) {
		if (spec == null) {
			return null;
		}
		Matcher m = p.matcher(spec);
		if (!m.matches()) {
			return null;
		}
		int tokens = Integer.parseInt(m.group(1));
		int duration = Integer.parseInt(m.group(2));
		return switch (m.group(3)) {
		case "m" -> new RateLimitations(duration * 60, tokens);
		case "h" -> new RateLimitations(duration * 3600, tokens);
		default -> throw new UnsupportedOperationException("can't parse duration base " + m.group(3) + " from " + spec);
		};
	}

}
