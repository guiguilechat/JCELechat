package fr.guiguilechat.eveonline.programs.guimutaplasmids;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.programs.guimutaplasmids.mutaplasmids.Muta1MN;
import fr.guiguilechat.eveonline.programs.guimutaplasmids.mutaplasmids.MutaWeb;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableNumberValue;

/**
 * each mutaplasmid applies to a given list of types.
 */
public abstract class MutaplasmidFamily {

	public final Set<Item> allowedItems;

	public static enum MutaStr {
		DECAYED, GRAVID, UNSTABLE, ABNORMAL;
	}

	public static final MutaStr[] MUTASTR_NONANCIL = { MutaStr.DECAYED, MutaStr.GRAVID, MutaStr.UNSTABLE };

	public static final MutaStr[] MUTASTR_ANCIL = { MutaStr.ABNORMAL };

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

	protected MutaplasmidFamily(Stream<? extends Item> allowedItems, Object[][] data) {

		this.allowedItems = Collections.unmodifiableSet(allowedItems.collect(Collectors.toSet()));
		// System.err.println("allowed items are " + this.allowedItems);
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
		private HashMap<Attribute, Double> minvalues = new HashMap<>();

		public HashMap<Attribute, Double> minvalues() {
			return minvalues;
		}

		private HashMap<Attribute, Double> maxvalues = new HashMap<>();

		public HashMap<Attribute, Double> maxvalues() {
			return maxvalues;
		}

		private Item item;

		public Item item() {
			return item;
		}

		private MutaStr strength;

		public MutaStr strength() {
			return strength;
		}

		@Override
		public String toString() {
			return item.name + "-" + strength + " " + minvalues + maxvalues;
		}

		public ObservableNumberValue probability(Map<Attribute, ObservableDoubleValue> thresholds) {
			ObservableNumberValue ret = new SimpleDoubleProperty(1.0);
			for (Attribute att : minvalues.keySet()) {
				ObservableDoubleValue threshold = thresholds.get(att);
				if (threshold != null) {
					DoubleBinding attMult;
					if (att.getHighIsGood()) {
						attMult = Bindings
								.createDoubleBinding(
										() -> Math.min(1.0,
												Math.max(0.0,
														(maxvalues.get(att) - threshold.get()) / (maxvalues.get(att) - minvalues.get(att)))),
										threshold);
					} else {
						attMult = Bindings
								.createDoubleBinding(
										() -> Math.min(1.0,
												Math.max(0.0,
														(threshold.get() - minvalues.get(att)) / (maxvalues.get(att) - minvalues.get(att)))),
										threshold);
					}
					ret = Bindings.multiply(ret, attMult);
				}
			}
			return ret;
		}

	}

	protected Stream<ModifiedItem> modifications() {
		return Stream.concat(strengths().stream(), Stream.of(new MutaStr[] { null }))
				.flatMap(str -> allowedItems.stream().map(item -> modify(item, str)));
	}

	private List<ModifiedItem> results = null;

	public List<ModifiedItem> results() {
		if (results == null) {
			results = Collections.unmodifiableList(modifications().collect(Collectors.toList()));
			// System.err.println("modified items are " + results);
		}
		return results;
	}

	protected ModifiedItem modify(Item item, MutaStr str) {
		ModifiedItem ret = new ModifiedItem();
		ret.item = item;
		ret.strength = str;
		if (str != null) {
			for (Attribute a : modifiedAttributes()) {
				ret.minvalues.put(a, a.value(item).doubleValue() * minMult(a, str));
				ret.maxvalues.put(a, a.value(item).doubleValue() * maxMult(a, str));
			}
		} else {
			for (Attribute a : modifiedAttributes()) {
				ret.minvalues.put(a, a.value(item).doubleValue());
				ret.maxvalues.put(a, a.value(item).doubleValue());
			}
		}
		return ret;
	}

	public static final MutaplasmidFamily[] INSTANCES = new MutaplasmidFamily[] { Muta1MN.INSTANCE, MutaWeb.INSTANCE };

}
