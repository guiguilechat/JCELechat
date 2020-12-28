package fr.guiguilechat.jcelechat.programs.showattributes;

import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldBoostAmplifier;

public class ShowSBAEffect {

	public static void main(String[] args) {
		System.out.println("name\tmeta level\tcpu\teffect\teffect / CPU");
		ShieldBoostAmplifier.METAGROUP.load().values().stream()
		.sorted((s1, s2) -> Double.compare(s1.shieldboostmultiplier, s2.shieldboostmultiplier))
				.sorted((s1, s2) -> Integer.compare(s1.metalevelold, s2.metalevelold)).forEachOrdered(sba -> {
			double effect = Math.log(1 + 0.01 * sba.shieldboostmultiplier) / Math.log(1.3);
			System.out.println(
							circle(sba.metalevelold, sba.metagroupid) + " " + sba.name + "\t" + sba.metalevelold + "\t" + sba.cpu
									+ "\t"
							+ String.format("%.3f", effect) + "\t" + String.format("%.3f", effect / sba.cpu));
		});

	}

	public static String circle(int metaLevel, int metaGroup) {
		switch (metaGroup) {
		case 3:
		case 4:
			return ":green_circle:";
		case 5:
			return ":purple_circle:";
		case 6:
			return ":large_blue_circle:";
		default:
			switch (metaLevel) {
			case 0:
				return ":white_circle:";
			case 1:
			case 2:
			case 3:
			case 4:
				return ":yellow_circle:";
			case 5:
				return ":orange_circle:";
			default:
				return "";
			}
		}
	}

}
