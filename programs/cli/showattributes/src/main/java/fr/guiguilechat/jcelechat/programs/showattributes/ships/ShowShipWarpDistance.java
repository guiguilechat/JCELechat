package fr.guiguilechat.jcelechat.programs.showattributes.ships;

import java.util.Comparator;

import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class ShowShipWarpDistance {

	public static void main(String[] args) {
		Ship.METACAT.load().values().stream().map(ShipEval::new).sorted(Comparator.comparing(se -> se.range))
				.forEach(se -> System.out.println(se.ship.name + "\t" + se.range));

	}

	public static class ShipEval {
		public final double range;
		public final Ship ship;

		public ShipEval(Ship ship) {
			this.ship = ship;
			range = ship.capacitorcapacity / ship.mass.doubleValue() / ship.warpcapacitorneed;
		}
	}

}
