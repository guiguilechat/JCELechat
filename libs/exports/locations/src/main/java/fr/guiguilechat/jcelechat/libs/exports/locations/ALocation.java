package fr.guiguilechat.jcelechat.libs.exports.locations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ALocation {

	public static double AU_IN_M = 1.057e16;

	public BigDecimal centerX, centerY, centerZ;

	public int id;

	public boolean isAbyssal = false;
	public boolean isHidden = false;
	public boolean isJovian = false;
	public boolean isPochven = false;
	/** set to true when this system is reachable from Jita via gates. */
	public boolean isKS = false;
	public boolean isVoid = false;
	public boolean isWormhole = false;

	public List<String> adjacentSystems = new ArrayList<>();

	public List<String> adjacentConstellations = new ArrayList<>();

	public List<String> adjacentRegions = new ArrayList<>();

	public String name;

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && obj.getClass().equals(getClass()) && ((ALocation) obj).id == id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

	public String name() {
		return name == null ? "location:" + id : name;
	}

}
