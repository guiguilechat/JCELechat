package fr.guiguilechat.jcelechat.sde.items;

import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class HighestBSBumper {

	public static void main(String[] args) {

		Map<String, Double> bs2energy = Ship.METACAT.load().entrySet().stream()
				.collect(Collectors.toMap(e -> e.getValue().name, e -> getMomentumMWD(e.getValue())));
		bs2energy.entrySet().stream().sorted((e1, e2) -> -Double.compare(e1.getValue(), e2.getValue()))
		.forEach(e -> System.out.println("" + e.getKey() + " : " + e.getValue()));
	}

	public static double getMomentumMWD(Ship ship) {
		double addedMass = 0;
		double thrust = ship.mass;
		if (ship.medslots > 0) {
			addedMass = 50000000;
			thrust = 150000000;
		}
		double totalMass = ship.mass + addedMass;
		double speed = ship.maxvelocity * thrust / totalMass;
		System.err.println("" + ship.name + " thrust=" + thrust + " totMass=" + totalMass + " mass=" + ship.mass);
		return totalMass * speed;
	}

}
