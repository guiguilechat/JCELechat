package fr.guiguilechat.eveonline.optimize;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.Module;

public class Bridge {

	public final EveDatabase database;

	protected final Hull[] hulls;

	public Bridge(EveDatabase database) {
		this.database = database;
		hulls = database.getHulls().values().toArray(new Hull[] {});
	}

	public Hull[] getHulls() {
		return hulls;
	}

	public Hull getEveHull(int internalHudId) {
		return hulls[internalHudId];
	}

	protected int[] hullSpeed;

	public int[] getHullSpeed() {
		if (hullSpeed == null) {
			hullSpeed = database.getHulls().values().stream().mapToInt(h -> h.attributes.velocity).toArray();
		}
		return hullSpeed;
	}

	protected Module[] highModules;

	public Module[] getHighModules() {
		if (highModules == null) {
			highModules = database.getModules().values().stream().filter(m -> m.attributes.slot.equals("high"))
					.toArray(Module[]::new);
		}
		return highModules;
	}

	protected Module[] mediumModules;

	public Module[] getMediumModules() {
		if (mediumModules == null) {
			mediumModules = database.getModules().values().stream().filter(m -> m.attributes.slot.equals("medium"))
					.toArray(Module[]::new);
		}
		return highModules;
	}

	protected Module[] lowModules;

	public Module[] getLowModules() {
		if (lowModules == null) {
			lowModules = database.getModules().values().stream().filter(m -> m.attributes.slot.equals("low"))
					.toArray(Module[]::new);
		}
		return highModules;
	}


}
