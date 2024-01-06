package fr.guiguilechat.jcelechat.programs.showattributes.locations;

import java.util.Comparator;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class ShowlowestssKS {

	public static void main(String[] args) {
		System.out.println("system\tregion");
		SolarSystem.load().values().stream().filter(hs -> hs.isKS && hs.truesec == -1 && !"Pochven".equals(hs.region))
		.sorted(Comparator.comparing(ss -> ss.region)).forEach(ss -> System.out.println(ss.name + "\t" + ss.region));

	}

}
