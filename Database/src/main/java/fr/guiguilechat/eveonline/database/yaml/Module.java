package fr.guiguilechat.eveonline.database.yaml;

import java.util.ArrayList;
import java.util.HashMap;

public class Module extends Type {

	public final ModuleAttributes attributes = new ModuleAttributes();

	public ArrayList<String> effects = new ArrayList<>();

	public ModuleActivation activation;

	public boolean isRig() {
		return effects.contains("rigSlot");
	}

	public int techLevel() {
		return attributes.metaLevel == 5 ? 2 : 1;
	}

	public HashMap<String, ?> rawAttributes = new HashMap<>();
}
