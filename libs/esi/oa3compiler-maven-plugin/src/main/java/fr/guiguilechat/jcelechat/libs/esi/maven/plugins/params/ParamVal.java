package fr.guiguilechat.jcelechat.libs.esi.maven.plugins.params;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ParamVal {

	public String eval;

	public String value;

	public String evaluate() {
		if (value != null) {
			return value;
		}
		if (eval != null) {
			if ("compatibilitydate".equals(eval)) {
				return Instant.now().atOffset(ZoneOffset.UTC).minus(11, ChronoUnit.HOURS)
						.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
			}
		}
		throw new UnsupportedOperationException("can't evaluate " + eval);
	}

}
