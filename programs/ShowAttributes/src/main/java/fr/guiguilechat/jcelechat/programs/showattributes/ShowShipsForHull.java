package fr.guiguilechat.jcelechat.programs.showattributes;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class ShowShipsForHull {

	public static void main(String[] args) {
		Map<Ship, Double> map = Ship.METACAT.load().values().stream()
				.collect(Collectors.toMap(s -> s, s -> Math.abs(Math.log(s.hp / 2701))));
		map.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue()))
				.forEach(s -> System.out.println(s.getKey().name + "\t" + s.getKey().hp));
	}

}
