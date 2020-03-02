package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.ArrayList;

public class ALocation {

	public int id;

	public boolean isWormhole = false;

	/** set to true when this system is reachable from Jita via gates. */
	public boolean isKS = false;

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
		return name + " (" + id + ")";
	}

}
