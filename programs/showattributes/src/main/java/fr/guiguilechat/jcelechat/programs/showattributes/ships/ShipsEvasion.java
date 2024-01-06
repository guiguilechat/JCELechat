package fr.guiguilechat.jcelechat.programs.showattributes.ships;

import fr.guiguilechat.jcelechat.model.sde.types.Ship;

/**
 * show the base evasion for all ships, that is the ratio speed/signature
 */
public class ShipsEvasion {

	public static void main(String[] args) {
		for (Ship s : Ship.METACAT.load().values()) {
			System.out
					.println(s.name + "\t" + s.maxvelocity / s.signatureradius + "\t" + (s.hp + s.armorhp + s.shieldcapacity));
		}
	}

}
