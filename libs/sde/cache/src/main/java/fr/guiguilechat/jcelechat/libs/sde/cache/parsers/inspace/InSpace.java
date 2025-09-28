package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

import java.util.LinkedHashMap;

public abstract class InSpace {
	public LinkedHashMap<String, String> name;
	public Position position;

	public String enName() {
		return name == null ? null : name.get("en");
	}

}
