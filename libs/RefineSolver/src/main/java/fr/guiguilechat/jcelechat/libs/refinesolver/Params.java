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

	public double volumicCost = 0;

	public Params withVolumicCost(double value) {
		volumicCost = value;
		return this;
	}

	public double refineRate = 0.5;

	public Params withRefineRate(double value) {
		refineRate = value;
		return this;
	}

}
