package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;

public class Universe {

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(Universe.class);

	private final ESIAccount parent;

	public Universe(ESIAccount esiAccount) {
		parent = esiAccount;
	}

	private HashMap<Long, Location> cachedLocationSystems = new HashMap<>();

	public Location location(long locationid) {
		Location ret = cachedLocationSystems.get(locationid);
		if (ret == null) {
			ret = Location.resolve(parent, locationid);
			cachedLocationSystems.put(locationid, ret);
		}
		return ret;
	}

	public String locationName(long locationid) {
		return location(locationid).name;
	}

}
