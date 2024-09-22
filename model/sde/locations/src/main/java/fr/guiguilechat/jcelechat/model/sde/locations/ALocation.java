package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.ArrayList;

public class ALocation {

	public int id;


	public boolean isAbyssal = false;
	public boolean isJovian = false;
	/** set to true when this system is reachable from Jita via gates. */
	public boolean isKS = false;

	public boolean isPochven = false;
	public boolean isWormhole = false;

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
		return name == null ? "region:" + id : name;
	}

}
