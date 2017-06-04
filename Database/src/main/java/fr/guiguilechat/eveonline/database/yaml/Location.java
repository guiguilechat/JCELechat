package fr.guiguilechat.eveonline.database.yaml;

public class Location {

	public String name;

	/** the parent constellation if this is a system ; null otherwise */
	public String parentConstelation = null;

	/**
	 * the parent region if this is a system or a constellation ; null otherwise
	 */
	public String parentRegion = null;

	/** eve id for this location */
	public int locationID;

	/**
	 *
	 * @return 1 for region, 2 for constellation, 3 for system
	 */
	public int getLocationType() {
		return locationID / 10000000;
	}

	/** max sec of all systems found in this. for a system, its security */
	public double maxSec = -1.0;

	/**
	 * min security of all systems found in this. for a system, its security
	 */
	public double minSec = 1.0;

	/**
	 *
	 * @return true iff at least one system has high security (>.45)
	 */
	public boolean hasHighSec() {
		return maxSec > 0.45;
	}

	/**
	 * @return true iff at least one system has null sec (<=0)
	 */
	public boolean hasNullSec() {
		return minSec <= 0;
	}

	public String[] adjacentSystems = {};
	public String[] adjacentRegions = {};
	public String[] adjacentConstels = {};

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() == Location.class) {
			return name.equals(((Location) obj).name);
		}
		return false;
	}

}
