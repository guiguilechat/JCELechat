package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.Comparator;
import java.util.List;

public class ListPochvenSystems {

	public static void main(String[] args) {
		List<SolarSystem> pochvens = SolarSystem.load().values().stream()
		    .filter(s -> s.isPochven)
		    .sorted(Comparator.comparing(ALocation::name))
		    .toList();
		List<SolarSystem> nonPochvens = SolarSystem.load().values().stream()
		    .filter(s -> s.isKS && !s.isPochven)
		    .sorted(Comparator.comparing(ALocation::name))
		    .toList();
		for (SolarSystem ss : pochvens) {
			System.out.print("\t" + ss.name());
		}
		System.out.println();
		for (SolarSystem ks : nonPochvens) {
			System.out.print(ks.name());
			for (SolarSystem ps : pochvens) {
				System.out.print("\t" + ps.centerDistanceAu(ks));
			}
			System.out.println();
		}
	}

}
