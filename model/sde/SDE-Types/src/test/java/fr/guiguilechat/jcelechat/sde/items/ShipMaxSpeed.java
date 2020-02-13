package fr.guiguilechat.jcelechat.sde.items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.ship.AssaultFrigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CovertOps;
import fr.guiguilechat.jcelechat.model.sde.types.ship.ElectronicAttackShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Frigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Interceptor;
import fr.guiguilechat.jcelechat.model.sde.types.ship.LogisticsFrigate;

public class ShipMaxSpeed {

	public static class ShipEval {
		public int microaux, reactors, ancils, overdrives, auxils;
		public double maxSpeed;
		public Ship ship;

		public ShipEval() {
		}

		public ShipEval(Ship ship, int microaux, int reactors, int ancils, int overdrives, int auxils, double maxSpeed) {
			this.ship = ship;
			this.microaux = microaux;
			this.reactors = reactors;
			this.ancils = ancils;
			this.overdrives = overdrives;
			this.auxils = auxils;
			this.maxSpeed = maxSpeed;
		}

		@Override
		public String toString() {
			return ship.name + " reaches " + maxSpeed + "whith " + microaux + " microaux, " + reactors + " reactors, "
					+ ancils
					+ " ancils, " + overdrives + " overdrives, " + auxils + " auxiliaries";
		}
	}

	public static void main(String[] args) {
		java.util.List<Ship> ships = new ArrayList<>();
		ships.addAll(AssaultFrigate.METAGROUP.load().values());
		ships.addAll(Frigate.METAGROUP.load().values());
		ships.addAll(Interceptor.METAGROUP.load().values());
		ships.addAll(LogisticsFrigate.METAGROUP.load().values());
		ships.addAll(CovertOps.METAGROUP.load().values());
		ships.addAll(ElectronicAttackShip.METAGROUP.load().values());

		double[] overdriveMultPerStacking = IntStream.rangeClosed(0, 7)
				.mapToDouble(i -> 1 + 0.125 * Math.exp(-1 * Math.pow(i / 2.67, 2))).toArray();
		// for (double d : overdriveMultPerStacking) {
		// System.err.print(" " + d);
		// }
		// System.err.println();
		double[] auxilMultPerStacking = IntStream.rangeClosed(0, 10)
				.mapToDouble(i -> 1 + 0.0875 * Math.exp(-1 * Math.pow(i / 2.67, 2))).toArray();
		// for (double d : auxilMultPerStacking) {
		// System.err.print(" " + d);
		// }
		// System.err.println();

		List<ShipEval> evals = new ArrayList<>();
		for (Ship s : ships) {
			double pwr = s.PowerOutput * 1.25;
			int microAux = 0, reactors = 0;

			// 118 is when one ancil rig gives 135 power. that's because overdrive are
			// better than auxiliaries for speed.
			for (int i = 0; i < s.LowSlots && pwr <= 118; i++) {
				if (pwr < 104.8387) {
					// add a navy micro auxiliary
					pwr += 16.25;
					microAux++;
				} else {
					// add a navy reactor
					pwr *= 1.155;
					reactors++;
				}
			}
			int ancilRig = 0;
			for (int i = 0; i < (s.TechLevel == 2 ? 2 : 3) && pwr < 135; i++) {
				if (ancilRig * 150 + 150 <= s.UpgradeCapacity) {
					// we can fit a T2
					pwr *= 1.15;
					ancilRig++;
				} else if (ancilRig * 150 + 100 <= s.UpgradeCapacity) {
					// we can fit a T1
					pwr *= 1.1;
					ancilRig++;
				}
			}
			if (pwr < 135) {
				// System.err.println(s.name + " only reaches " + pwr + " powergrid at
				// max");
				continue;
			}
			double speed = s.MaxVelocity * 1.25 * 7.375 * 15000000 / (s.mass + 5000000);
			int overdrives = 0;
			for (int i = microAux + reactors; i < s.LowSlots; i++) {
				speed *= overdriveMultPerStacking[overdrives];
				overdrives++;
			}
			int auxils = 0;
			for (int i = ancilRig; i < (s.TechLevel == 2 ? 2 : 3); i++) {
				// actually we may only be able to fit a T1
				speed *= auxilMultPerStacking[overdrives + auxils];
				auxils++;
			}
			evals.add(new ShipEval(s, microAux, reactors, ancilRig, overdrives, auxils, speed));
		}
		evals.sort((se1, se2) -> (int) Math.signum(se2.maxSpeed - se1.maxSpeed));
		for(ShipEval se : evals) {
			System.err.println(se);
		}
	}

}
