package fr.guiguilechat.jcelechat.programs.guimutaplasmids;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids.Muta1MN;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids.MutaDisrupt;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids.MutaScram;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids.MutaWeb;
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
	private Map<MutaStr, Integer> strIDs = new HashMap<>();

	protected MutaplasmidFamily(Stream<? extends Item> allowedItems, Object[][] data) {

		this.allowedItems = Collections.unmodifiableSet(allowedItems.collect(Collectors.toSet()));
		// System.err.println("allowed items are " + this.allowedItems);
		minmults = new HashMap<>();
		maxmults = new HashMap<>();
		strengths = new HashSet<>();
		modifiedAttributes = new HashSet<>();
		for (int i = 0; i < data[0].length; i++) {
			int id = (Integer) data[0][i];
			strIDs.put(MutaStr.values()[i], id);
		}
		List<Object[]> lines = Arrays.asList(data).subList(1, data.length);
		for (Object[] line : lines) {
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
		private HashMap<Attribute, Double> lowRangeValues = new HashMap<>();

		public HashMap<Attribute, Double> lowRangeValues() {
			return lowRangeValues;
		}

		private HashMap<Attribute, Double> highRangeValues = new HashMap<>();

		public HashMap<Attribute, Double> highRangeValues() {
			return highRangeValues;
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
			return item.name + "-" + strength + " " + lowRangeValues + highRangeValues;
		}

		public ObservableNumberValue probability(Map<Attribute, ObservableDoubleValue> thresholds) {
			ObservableNumberValue ret = new SimpleDoubleProperty(1.0);
			for (Attribute att : lowRangeValues.keySet()) {
				ObservableDoubleValue threshold = thresholds.get(att);
				double max = highRangeValues.get(att);
				double min = lowRangeValues.get(att);
				System.err.println("probability for " + att + " based on min:" + min + " max:" + max);
				if (threshold != null) {
					DoubleBinding attMult;
					if (max == min) {
						attMult = att.getHighIsGood()
								? Bindings.createDoubleBinding(() -> threshold.get() / max >= 1.0 ? 0.0 : 1.0, threshold)
										: Bindings.createDoubleBinding(() -> threshold.get() / max > 1 ? 1.0 : 0.0, threshold);
					} else
						if (att.getHighIsGood()) {
							attMult = Bindings.createDoubleBinding(
									() -> Math.min(1.0, Math.max(0.0, (max - threshold.get()) / (max - min))), threshold);
						} else {
							attMult = Bindings.createDoubleBinding(
									() -> Math.min(1.0, Math.max(0.0, (threshold.get() - min) / (max - min))), threshold);
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
				ret.lowRangeValues().put(a, a.value(item).doubleValue() * minMult(a, str));
				ret.highRangeValues().put(a, a.value(item).doubleValue() * maxMult(a, str));
				System.err.println("" + item.name + "-" + str + "." + a.toString() + " : " + ret.lowRangeValues().get(a) + " - "
						+ ret.highRangeValues().get(a));
			}
		} else {
			for (Attribute a : modifiedAttributes()) {
				ret.lowRangeValues().put(a, a.value(item).doubleValue());
				ret.highRangeValues().put(a, a.value(item).doubleValue());
				System.err.println("" + item.name + "-" + str + "." + a.toString() + " : " + ret.lowRangeValues().get(a) + " - "
						+ ret.highRangeValues().get(a));
			}
		}
		return ret;
	}

	public static final MutaplasmidFamily[] INSTANCES = new MutaplasmidFamily[] { Muta1MN.INSTANCE, MutaWeb.INSTANCE,
			MutaScram.INSTANCE, MutaDisrupt.INSTANCE };

	public static void searchESI() {
		R_get_universe_groups_group_id groups = ESIAccess.INSTANCE.connection.get_universe_groups(1964, null).getOK();
		for (int mutaId : groups.types) {
			R_get_universe_types_type_id type = ESIAccess.INSTANCE.connection.get_universe_types(mutaId, null).getOK();
			System.err.println("" + mutaId + " " + type.name);
		}
	}

}
