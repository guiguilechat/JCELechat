package fr.guiguilechat.jcelechat.libs.refinesolver;

import java.util.HashMap;

public class Params {

	/** default is for Jita IV - 4 */
	public long marketLocation = 60003760;

	public Params withMarketLocation(long value) {
		marketLocation = value;
		return this;
	}

	HashMap<Integer, Integer> requiredQuantities = new HashMap<>();

	public Params withQuantity(int typeid, int newqtty) {
		requiredQuantities.put(typeid, newqtty);
		return this;
	}

	/**
	 * volumic price, in isk/m³
	 */
	public double volumicCost = 0;

	/**
	 * set the {@link #volumicCost}
	 *
	 * @param value
	 *          new value to set
	 * @return this
	 */
	public Params withVolumicCost(double value) {
		volumicCost = value;
		return this;
	}

	public double refineRate = 0.5;

	public Params withRefineRate(double value) {
		refineRate = value;
		return this;
	}

	public boolean debug = false;

	public Params withDebug(boolean value) {
		debug = value;
		return this;
	}

	public int maxCommands = 1;

	public Params withMaxCommands(int value) {
		maxCommands = value;
		return this;
	}

	public boolean onlyCompressed = false;

	public Params withOnlyCompressed(boolean value) {
		onlyCompressed = value;
		return this;
	}

	public int[] groupsLimit = null;

	public Params withGroupsLimit(int... value) {
		groupsLimit = value;
		return this;
	}

}
