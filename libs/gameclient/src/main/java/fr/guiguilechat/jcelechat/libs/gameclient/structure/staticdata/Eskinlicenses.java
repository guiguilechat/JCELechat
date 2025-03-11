package fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class Eskinlicenses {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Eskinlicenses> loader = new KeyValTimeLoader<>(
			Eskinlicenses.class, "staticdata/skinlicenses.static");

	public int duration;
	public boolean isSingleUse = true;
	public int licenseTypeID;
	public int skinID;

}
