package fr.guiguilechat.jcelechat.model.sde.main;

import java.util.Comparator;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem;

public class ShowHSLowestRadiusSyst {

	public static void main(String[] args) {
		Entry<String, ESolarSystem> eLowestHS = Universe.load().eve.values().stream()
				.flatMap(r -> r.constellations.values().stream())
				.flatMap(c -> c.systems.entrySet().stream())
				.filter(es -> es.getValue().security > 0.45)
				.sorted(Comparator.comparing(es -> es.getValue().furthestCelestialAU()))
				.findFirst().get();
		System.out.println(
				"lowest HS system is " + eLowestHS.getKey() + " with size : " + eLowestHS.getValue().furthestCelestialAU());

		Entry<String, ESolarSystem> eHighestHS = Universe.load().eve.values().stream()
				.flatMap(r -> r.constellations.values().stream())
				.flatMap(c -> c.systems.entrySet().stream())
				.filter(es -> es.getValue().security > 0.45)
				.sorted(Comparator.comparing(es -> -es.getValue().furthestCelestialAU()))
				.findFirst().get();
		System.out.println(
				"highest HS system is " + eHighestHS.getKey() + " with size : " + eHighestHS.getValue().furthestCelestialAU());

		Entry<String, ESolarSystem> eLowestKS = Universe.load().eve.values().stream()
				.flatMap(r -> r.constellations.values().stream())
				.flatMap(c -> c.systems.entrySet().stream())
				.sorted(Comparator.comparing(es -> es.getValue().furthestCelestialAU()))
				.findFirst().get();
		System.out.println(
				"lowest KS system is " + eLowestKS.getKey() + " with size : " + eLowestKS.getValue().furthestCelestialAU());

		Entry<String, ESolarSystem> eHighestKS = Universe.load().eve.values().stream()
				.flatMap(r -> r.constellations.values().stream())
				.flatMap(c -> c.systems.entrySet().stream())
				.sorted(Comparator.comparing(es -> -es.getValue().furthestCelestialAU()))
				.findFirst().get();
		System.out
				.println(
						"highest KS system is " + eHighestKS.getKey() + " with size : "
								+ eHighestKS.getValue().furthestCelestialAU());

		Entry<String, ESolarSystem> eLowestWS = Universe.load().wormhole.values().stream()
				.flatMap(r -> r.constellations.values().stream())
				.flatMap(c -> c.systems.entrySet().stream())
				.sorted(Comparator.comparing(es -> es.getValue().furthestCelestialAU()))
				.findFirst().get();
		System.out.println(
				"lowest WS system is " + eLowestWS.getKey() + " with size : " + eLowestWS.getValue().furthestCelestialAU());

		Entry<String, ESolarSystem> eHighestWS = Universe.load().wormhole.values().stream()
				.flatMap(r -> r.constellations.values().stream())
				.flatMap(c -> c.systems.entrySet().stream())
				.sorted(Comparator.comparing(es -> -es.getValue().furthestCelestialAU()))
				.findFirst().get();
		System.out
				.println(
						"highest WS system is " + eHighestWS.getKey() + " with size : "
								+ eHighestWS.getValue().furthestCelestialAU());
	}

}
