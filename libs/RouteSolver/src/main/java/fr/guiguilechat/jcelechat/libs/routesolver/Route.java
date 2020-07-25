package fr.guiguilechat.jcelechat.libs.routesolver;

import java.util.ArrayList;

import fr.guiguilechat.jcelechat.model.sde.locations.Constellation;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class Route extends ArrayList<SolarSystem> {

	/**
	 *
	 */
	private static final long serialVersionUID = 3219169347697088813L;

	public String print(boolean constel) {
		Constellation lastConstel = null;
		StringBuilder sb = new StringBuilder();
		int constelMult = 10;
		int constIdx = 0;
		int intraConstIdx = 0;
		for (SolarSystem ss : this) {
			if (ss.constellation() != lastConstel) {
				constIdx++;
				lastConstel = ss.constellation();
				intraConstIdx = 0;
			} else {
				intraConstIdx++;
			}
			String idx = "" + (constelMult * constIdx + intraConstIdx);
			sb.append(idx).append("\t").append(ss.name);
			if (constel) {
				sb.append("\t").append(ss.constellation);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
