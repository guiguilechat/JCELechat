package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.everaw.structure.common.RequiredSkill;
import lombok.Getter;

public class Eclonegrades {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Eclonegrades> loader = new KeyValTimeLoader<>(
			Eclonegrades.class, "staticdata/clonegrades.static");

	public String internalDescription;

	public List<RequiredSkill> skills = new ArrayList<>();

}
