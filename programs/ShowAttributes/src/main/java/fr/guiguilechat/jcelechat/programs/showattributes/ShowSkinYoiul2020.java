package fr.guiguilechat.jcelechat.programs.showattributes;

import java.util.Comparator;

import fr.guiguilechat.jcelechat.model.sde.types.skins.PermanentSKIN;

public class ShowSkinYoiul2020 {

	public static void main(String[] args) {
		PermanentSKIN.METAGROUP.load().values().stream()
				.filter(sk -> sk.name.contains("Icecloud Investigators") || sk.name.contains("Glacial Drift")
				|| sk.name.contains("Sansha Industrial Livery"))
		.sorted(Comparator.comparing(sk -> sk.name))
		.forEach(sk -> System.out.println(sk.name + "\t\t" + sk.id));
	}

}
