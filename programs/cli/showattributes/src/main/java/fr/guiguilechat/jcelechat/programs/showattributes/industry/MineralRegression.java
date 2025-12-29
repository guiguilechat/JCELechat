package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.exports.industry.Blueprint;
import fr.guiguilechat.jcelechat.libs.exports.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.libs.exports.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.ship.AttackBattlecruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Battleship;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CombatBattlecruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Cruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Destroyer;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Frigate;

public class MineralRegression {

	public static void main(String[] args) {
		Map<Ship, Blueprint> ships = Stream
				.of(AttackBattlecruiser.METAGROUP, Battleship.METAGROUP, CombatBattlecruiser.METAGROUP, Cruiser.METAGROUP,
						Destroyer.METAGROUP, Frigate.METAGROUP)
				.flatMap(mg->mg.load().values().stream())
				.filter(ship->ship.techlevel==1)
				.filter(ship->{
					IndustryUsage iu = IndustryUsage.of(ship.id);
					if (iu == null || iu.productOfManuf == null || iu.productOfManuf.isEmpty()) {
						return false;
					}
					Blueprint bp = Blueprint.of(iu.productOfManuf.iterator().next());
					return bp != null && bp.seeded;
				})
				.collect(
						Collectors.toMap(s -> s, ship -> Blueprint.of(IndustryUsage.of(ship.id).productOfManuf.iterator().next())));

		Attribute[] attributes = new Attribute[] { ShieldCapacity.INSTANCE, ArmorHP.INSTANCE, Hp.INSTANCE };

		HashSet<EveType> mineralsSet = new HashSet<>();
		for (Blueprint bp : ships.values()) {
			for (MaterialReq<?> p : bp.manufacturing.materials) {
				mineralsSet.add(p.type());
			}
		}
		EveType[] minerals = mineralsSet.toArray(EveType[]::new);

		System.out.print("name");
		for (Attribute att : attributes) {
			System.out.print(" \t" + att.getClass().getSimpleName());
		}
		for (EveType min : minerals) {
			System.out.print("\t" + min.name);
		}
		System.out.println();

		for (Entry<Ship, Blueprint> e : ships.entrySet()) {
			Ship ship = e.getKey();
			Blueprint bp = e.getValue();
			System.out.print(ship.name);
			for (Attribute att : attributes) {
				System.out.print("\t" + att.value(ship));
			}
			for (EveType et : minerals) {
				int val = 0;
				for (MaterialReq<?> req : bp.manufacturing.materials) {
					if (req.id == et.id) {
						val = req.quantity;
					}
				}
				System.out.print("\t" + val);
			}
			System.out.println();
		}
	}

}
