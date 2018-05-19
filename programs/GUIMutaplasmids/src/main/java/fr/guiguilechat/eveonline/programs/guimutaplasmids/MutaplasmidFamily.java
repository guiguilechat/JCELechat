package fr.guiguilechat.eveonline.programs.guimutaplasmids;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.Item;

/**
 * each mutaplasmid applies to a given list of types.
 */
public abstract class MutaplasmidFamily {

	public final Set<Item> allowedItems;

	public static enum MutaStr {
		DECAYED, GRAVID, UNSTABLE, ABNORMAL;
	}

	public static final MutaStr[] NONANCIL = { MutaStr.DECAYED, MutaStr.GRAVID,
			MutaStr.UNSTABLE };

	public static final MutaStr[] ANCIL = { MutaStr.ABNORMAL };

	private Set<MutaStr> strengths = null;

	public Set<MutaStr> strengths() {
		return strengths;
	}

	private Set<Attribute> modifiedAttributes = null;

	public Set<Attribute> modifiedAttributes() {
		return modifiedAttributes;
	}

	private Map<Attribute, double[]> minmults = null;
	private Map<Attribute, double[]> maxmults = null;

	protected MutaplasmidFamily(Stream<Item> allowedItems, Object[][] data) {

		this.allowedItems = Collections.unmodifiableSet(allowedItems.collect(Collectors.toSet()));
		minmults = new HashMap<>();
		maxmults = new HashMap<>();
		strengths = new HashSet<>();
		modifiedAttributes = new HashSet<>();
		for (Object[] line : data) {
			Attribute att = (Attribute) line[0];
			modifiedAttributes.add(att);
			double[] min = new double[MutaStr.values().length];
			minmults.put(att, min);
			double[] max = new double[MutaStr.values().length];
			maxmults.put(att, max);
			for (int idx = 0; idx < min.length; idx++) {
				min[idx] = ((Number) line[1 + idx * 2]).doubleValue();
				max[idx] = ((Number) line[2 + idx * 2]).doubleValue();
				if (min[idx] != max[idx]) {
					strengths.add(MutaStr.values()[idx]);
				}
			}
		}
	}

	public double minMult(Attribute att, MutaStr str) {
		return minmults.get(att)[str.ordinal()];
	}

	public double maxMult(Attribute att, MutaStr str) {
		return maxmults.get(att)[str.ordinal()];
	}

	public static class ModifiedItem {
		public HashMap<Attribute, Double> minvalues = new HashMap<>();
		public HashMap<Attribute, Double> maxvalues = new HashMap<>();
		public Item item;
		public MutaStr strength;
	}

	// public Stream<ModifiedItem> modifications() {
	//
	// }

	protected ModifiedItem modify(Item item, MutaStr str) {
		ModifiedItem ret = new ModifiedItem();
		ret.item = item;
		ret.strength = str;
		for (Attribute a : modifiedAttributes()) {
			// ret.minvalues.put(a, item.attributeDouble(a));
		}
		return ret;
	}

}
