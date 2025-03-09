package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class Eskinmaterials {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Eskinmaterials> loader = new KeyValTimeLoader<>(
			Eskinmaterials.class, "staticdata/skinmaterials.static");

	public int displayNameID;
	public int materialSetID;
	public int skinMaterialID;

}
