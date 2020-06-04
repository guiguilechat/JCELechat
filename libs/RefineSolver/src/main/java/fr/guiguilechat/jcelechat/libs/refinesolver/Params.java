package fr.guiguilechat.jcelechat.libs.refinesolver;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;

public class Params {

	/** default is for Jita IV - 4 */
	public long marketLocation = 60003760;

	public Params withMarketLocation(long value) {
		marketLocation = value;
		return this;
	}

	/**
	 * set the pricing to use. Overrides the {@link #marketLocation} (actually
	 * mutes it)
	 */
	public IPricing market = null;

	public Params withMarket(IPricing value) {
		market = value;
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

	/**
	 * base refine rate for the types that have no specific category or group
	 * refine rate.
	 */
	public double refineRate = 0.5;

	public Params withRefineRate(double value) {
		refineRate = value;
		return this;
	}

	/**
	 * overwrite refine rate for types in a category. overwrites
	 * {@link #refineRate} but is overwritten by {@link #groupRefineRate}
	 */
	public HashMap<Integer, Double> catRefineRate = new HashMap<>();

	public Params withCatRefineRate(Integer catId, double refineRate) {
		catRefineRate.put(catId, refineRate);
		return this;
	}

	/**
	 * overwrite refine rate for types in a group. overwrites {@link #refineRate}
	 * and {@link #catRefineRate}
	 */
	public HashMap<Integer, Double> groupRefineRate = new HashMap<>();

	public Params withGroupRefineRate(Integer groupId, double refineRate) {
		groupRefineRate.put(groupId, refineRate);
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

	public int[] typeLimits = null;

	public Params withTypeLimits(int... value) {
		typeLimits = value;
		return this;
	}

}
