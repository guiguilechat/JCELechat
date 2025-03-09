package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class Eskins {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Eskins> loader = new KeyValTimeLoader<>(
			Eskins.class, "staticdata/skins.static");

	public boolean allowCCPDevs;
	public String internalName;
	public boolean isStructureSkin = false;
	public String skinDescription;
	public int skinID;
	public int skinMaterialID;
	public List<Integer> types = new ArrayList<>();
	public boolean visibleSerenity;
	public boolean visibleTranquility;

}
