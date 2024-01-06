package fr.guiguilechat.jcelechat.programs.showattributes;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorRepairUnit;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldBooster;

public class ShowArmorShieldReps {

	public static void main(String[] args) {
		Locale uk = Locale.UK;
		System.out.println("name\tHP/GJ\tHP/s");
		Map<String, List<ShieldBooster>> gsbs = ShieldBooster.METAGROUP.load().values().stream()
				.collect(Collectors.groupingBy(sb -> "" + sb.shieldbonus + ":" + sb.capacitorneed + ":" + sb.duration));
		gsbs.values().stream().forEachOrdered(sbs -> {
			Collections.sort(sbs, Comparator.comparing(sb -> sb.name.length()));
			ShieldBooster sb = sbs.iterator().next();
			double rep = sb.shieldbonus;
			double cost = sb.capacitorneed * 0.9;
			double time = sb.duration / 1000;
			String name = formatName(sb.name);
			if (cost != 0 && time != 0 && rep < 1000) {
				List<String> allreps = sbs.stream().map(ar -> ar.name).sorted().collect(Collectors.toList());
				System.out.println(
						name + "\t" + String.format(uk, "%.2f", rep / cost) + "\t" + String.format(uk, "%.2f", rep / time) + "\t"
								+ allreps);
			}
		});
		Map<String, List<ArmorRepairUnit>> garus = ArmorRepairUnit.METAGROUP.load().values().stream().collect(
				Collectors.groupingBy(aru -> "" + aru.armordamageamount + ":" + aru.capacitorneed + ":" + aru.duration));

		garus.values().stream().forEachOrdered(arus -> {
			Collections.sort(arus, Comparator.comparing(aru -> aru.name.length()));
			ArmorRepairUnit aru = arus.iterator().next();
			double rep = aru.armordamageamount;
			double cost = aru.capacitorneed;
			double time = aru.duration * 0.75 / 1000;
			String name = formatName(aru.name);
			if (cost != 0 && time != 0 && rep < 3000) {
				List<String> allreps = arus.stream().map(ar -> ar.name).sorted().collect(Collectors.toList());
				System.out.println(
						name + "\t" + String.format(uk, "%.2f", rep / cost) + "\t" + String.format(uk, "%.2f", rep / time) + "\t"
								+ allreps);
			}
		});
	}

	public static String formatName(String name) {
		return name.replaceAll("Capital ", "C").replaceAll("X-Large ", "XL").replaceAll("Large ", "L")
				.replaceAll("Medium ", "M").replaceAll("Small ", "S")
				//
				.replaceAll("-Type", "T")
				//
				.replaceAll("Armor Repairer", "AR").replaceAll("I-a|ACM|Modified ", "")
				//
				.replaceAll("Shield Booster", "SB").replaceAll("C5-L|Clarity Ward|Modified ", "")
				//
				.replaceAll("Dark Blood", "Blood")
				.replaceAll("Caldari Navy", "Cal")
				.replaceAll("Centum|Centii|Centus", "Cent")
				.replaceAll("Corelum|Coreli", "Core")
				.replaceAll("Domination", "Dom")
				.replaceAll("Gistii |Gistum |Gist ", "G")
				.replaceAll("Pithi |Pithum |Pith ", "P")
				.replaceAll("Shadow Serpentis", "Serp");

	}

}
