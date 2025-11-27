package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.ArrayList;

public class ALocation {

	public static double AU_IN_M = 1.057e16;

	public int id;

	public boolean isAbyssal = false;
	public boolean isJovian = false;
	/** set to true when this system is reachable from Jita via gates. */
	public boolean isKS = false;

	public boolean isPochven = false;
	public boolean isWormhole = false;

	public Double centerX, centerY, centerZ;
	public Double maxX, maxY, maxZ;
	public Double minX, minY, minZ;

	public ArrayList<String> adjacentSystems = new ArrayList<>();

	public ArrayList<String> adjacentConstellations = new ArrayList<>();

	public ArrayList<String> adjacentRegions = new ArrayList<>();

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

	public double centerDistanceAu(ALocation other) {
		if (other.centerX == null || other.centerY == null || other.centerZ == null
		    || centerX == null || centerY == null || centerZ == null) {
			return Double.NaN;
		}
		return Math.sqrt(
		    Math.pow((other.centerX - centerX) / AU_IN_M, 2)
		        + Math.pow((other.centerY - centerY) / AU_IN_M, 2)
		        + Math.pow((other.centerZ - centerZ) / AU_IN_M, 2));
	}

}
