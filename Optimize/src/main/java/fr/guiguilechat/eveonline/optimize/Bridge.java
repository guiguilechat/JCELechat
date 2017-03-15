package fr.guiguilechat.eveonline.optimize;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.elements.Hull;

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


}
