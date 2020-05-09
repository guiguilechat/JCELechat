package fr.guiguilechat.jcelechat.sde.items;

import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.types.ship.Battleship;

public class HighestBSBumper {

	public static void main(String[] args) {

		Map<String, Double> bs2energy = Battleship.METAGROUP.load().entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> getEnergy(e.getValue())));
		bs2energy.entrySet().stream().sorted((e1, e2) -> Double.compare(e1.getValue(), e2.getValue()))
		.forEach(e -> System.out.println("" + e.getKey() + " : " + e.getValue()));
	}

	public static double getEnergy(Battleship bs) {
		double totalMass = bs.mass + 50000000;
		return totalMass * Math.pow(bs.maxvelocity * 150000000 / totalMass, 2);
	}

}
