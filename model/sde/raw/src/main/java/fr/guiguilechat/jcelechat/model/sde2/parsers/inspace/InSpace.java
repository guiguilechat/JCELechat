package fr.guiguilechat.jcelechat.model.sde2.parsers.inspace;

import java.util.LinkedHashMap;

public abstract class InSpace {
	public LinkedHashMap<String, String> name;
	public Position position;

	public String enName() {
		return name == null ? null : name.get("en");
	}

}
