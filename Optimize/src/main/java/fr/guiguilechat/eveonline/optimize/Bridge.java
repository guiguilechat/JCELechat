package fr.guiguilechat.eveonline.optimize;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;

public class Bridge {

	public final Database database;

	protected final Hull[] hulls;

	/** for each hull i in the bridge, the corresponding item id for Eve */
	protected final int[] hullsIds;

	public Bridge(Database database) {
		this.database = database;
		hulls = database.hulls.values().toArray(new Hull[] {});
		hullsIds = database.hulls.keySet().stream().mapToInt(i -> i).toArray();
	}

	public Hull[] getHulls() {
		return hulls;
	}

	public int getEveHullId(int internalHudId) {
		return hullsIds[internalHudId];
	}

	protected int[] hullSpeed;

	public int[] getHullSpeed() {
		if (hullSpeed == null) {
			hullSpeed = database.hulls.values().stream().mapToInt(h -> h.attributes.velocity).toArray();
		}
		return hullSpeed;
	}

	protected Module[] highModules;

	public Module[] getHighModules() {
		if (highModules == null) {
			highModules = database.modules.values().stream().filter(m -> m.attributes.slot.equals("high"))
					.toArray(Module[]::new);
		}
		return highModules;
	}

	protected Module[] mediumModules;

	public Module[] getMediumModules() {
		if (mediumModules == null) {
			mediumModules = database.modules.values().stream().filter(m -> m.attributes.slot.equals("medium"))
					.toArray(Module[]::new);
		}
		return highModules;
	}

	protected Module[] lowModules;

	public Module[] getLowModules() {
		if (lowModules == null) {
			lowModules = database.modules.values().stream().filter(m -> m.attributes.slot.equals("low"))
					.toArray(Module[]::new);
		}
		return highModules;
	}


}
