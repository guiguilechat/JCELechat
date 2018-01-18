package fr.guiguilechat.eveonline.model.sde.locations;

import java.util.ArrayList;

public class ALocation {

	public int id;

	public boolean isWormhole = false;

	public ArrayList<String> adjacentSystems = new ArrayList<>();

	public ArrayList<String> adjacentConstellations = new ArrayList<>();

	public ArrayList<String> adjacentRegions = new ArrayList<>();

}
