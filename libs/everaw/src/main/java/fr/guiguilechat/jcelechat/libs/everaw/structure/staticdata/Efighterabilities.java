package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class Efighterabilities {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Efighterabilities> loader = new KeyValTimeLoader<>(
			Efighterabilities.class, "staticdata/fighterabilities.static");

	public enum TargetMode {
		itemTargeted,
		pointTargeted,
		untargeted;
	}

	public boolean disallowInHighSec;

	public boolean disallowInLowSec;

	public int displayNameID;

	public int iconID;

	public int tooltipTextID;

	public TargetMode targetMode;

	public int turretGraphicID;

}
