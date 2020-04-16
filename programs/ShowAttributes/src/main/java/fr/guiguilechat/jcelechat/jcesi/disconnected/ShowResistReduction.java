package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorCoating;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorPlatingEnergized;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldResistanceAmplifier;

public class ShowResistReduction {

	public static class ItemReduction {

		public static double[] stackMult = { 1.0, 0.869, 0.571, 0.283 };

		EveType type;
		double resist;
		double EHPMultMod1;
		double EHPMultMod2;
		double EHPMultMod3;
		double EHPMultMod4;

		public ItemReduction(EveType type, double resist) {
			// System.err.println("type " + type.name + " resist=" + resist);
			this.type = type;
			this.resist = resist;
			EHPMultMod1 = (100.0 + resist) / (100.0 + resist * 0.8);
			EHPMultMod2 = (100.0 + resist * stackMult[1]) / (100.0 + resist * 0.8 * stackMult[1]);
			EHPMultMod3 = (100.0 + resist * stackMult[2]) / (100.0 + resist * 0.8 * stackMult[2]);
			EHPMultMod4 = (100.0 + resist * stackMult[3]) / (100.0 + resist * 0.8 * stackMult[3]);
		}

		public ItemReduction(ArmorHardener ah) {
			this(ah, Math.min(Math.min(ah.EmDamageResistanceBonus, ah.ThermalDamageResistanceBonus),
					Math.min(ah.KineticDamageResistanceBonus, ah.ExplosiveDamageResistanceBonus)));
		}

		public ItemReduction(ArmorCoating ah) {
			this(ah, 1.25 * Math.min(Math.min(ah.EmDamageResistanceBonus,
					ah.ThermalDamageResistanceBonus),
					Math.min(ah.KineticDamageResistanceBonus, ah.ExplosiveDamageResistanceBonus)));
		}

		public ItemReduction(ArmorPlatingEnergized ah) {
			this(ah, 1.25 * Math.min(Math.min(ah.EmDamageResistanceBonus,
					ah.ThermalDamageResistanceBonus),
					Math.min(ah.KineticDamageResistanceBonus, ah.ExplosiveDamageResistanceBonus)));
		}

		public ItemReduction(ShieldHardener ah) {
			this(ah, Math.min(Math.min(ah.EmDamageResistanceBonus, ah.ThermalDamageResistanceBonus),
					Math.min(ah.KineticDamageResistanceBonus, ah.ExplosiveDamageResistanceBonus)));
		}

		public ItemReduction(ShieldResistanceAmplifier ah) {
			this(ah, 1.25 * Math.min(Math.min(ah.EmDamageResistanceBonus,
					ah.ThermalDamageResistanceBonus),
					Math.min(ah.KineticDamageResistanceBonus, ah.ExplosiveDamageResistanceBonus)));
		}
	}

	public static void main(String[] args) {
		Stream<ItemReduction> ahStream = ArmorHardener.METAGROUP.load().values().stream().map(ItemReduction::new);
		Stream<ItemReduction> acStream = ArmorCoating.METAGROUP.load().values().stream().map(ItemReduction::new);
		Stream<ItemReduction> apeStream = ArmorPlatingEnergized.METAGROUP.load().values().stream().map(ItemReduction::new);
		Stream<ItemReduction> shStream = ShieldHardener.METAGROUP.load().values().stream().map(ItemReduction::new);
		Stream<ItemReduction> sraStream = ShieldResistanceAmplifier.METAGROUP.load().values().stream()
				.map(ItemReduction::new);
		List<ItemReduction> analysis = Stream.of(ahStream, acStream, apeStream, shStream, sraStream).flatMap(
				s -> s)
				.filter(
						a -> a.resist != 0)
				.sorted((a, b) -> Double.compare(a.EHPMultMod1, b.EHPMultMod1))
				.collect(Collectors.toList());
		DecimalFormat nf = new DecimalFormat("#.000", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		System.out.println("type\tresist%\tmod1mult\tmod2mult\tmod3mult\tmod4mult");
		for (ItemReduction a : analysis) {
			System.out.println(a.type.name + "\t" + nf.format(a.resist) + "\t" + nf.format(a.EHPMultMod1) + "\t"
					+ nf.format(a.EHPMultMod2) + "\t"
					+ nf.format(a.EHPMultMod3) + "\t" + nf.format(a.EHPMultMod4));
		}
	}

}
