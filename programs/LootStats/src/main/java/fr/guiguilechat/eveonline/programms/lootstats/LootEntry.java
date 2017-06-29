package fr.guiguilechat.eveonline.programms.lootstats;

import java.util.Date;
import java.util.HashMap;

public class LootEntry {

	/**
	 * date of loot
	 */
	public Date date;

	/**
	 * team | base | agent | damsel4
	 */
	public String type;

	/**
	 * ennemy to fight <br />
	 * angel, jaguar, gallente, ..
	 */
	public String race;

	public float sec;

	/**
	 * type id to its number of element looted
	 */
	public HashMap<Integer, Integer> loots = new HashMap<>();

}