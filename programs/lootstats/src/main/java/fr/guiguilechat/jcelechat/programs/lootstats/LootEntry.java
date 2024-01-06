package fr.guiguilechat.jcelechat.programs.lootstats;

import java.time.LocalDate;
import java.util.HashMap;

public class LootEntry {

	/**
	 * date of loot
	 */
	public LocalDate date;

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