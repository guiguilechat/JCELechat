package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;

public class ShowBestKinTankInGroup {

	/**
	 * show the kinetic ehps and evghp of rats from the guristas cruisers and
	 * frigates groups. ehp is hullhp/hullKinResonance +shieldhp… and evhp is
	 * ehp*speed/sig
	 */
	public static void main(String[] args) {
		showTankGroup(613);
		showTankGroup(615);
	}

	public static void showTankGroup(int groupId) {
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(groupId).get();
		System.out.println("group " + group.name);
		Map<R_get_universe_types_type_id, Double> map = IntStream.of(group.types)
				.mapToObj(i -> ESIStatic.INSTANCE.cache.universe.types(i).get()).parallel()
				.collect(Collectors.toMap(entity -> entity, ShowBestKinTankInGroup::getKinEVHPType));
		ArrayList<Entry<R_get_universe_types_type_id, Double>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
		for (Entry<R_get_universe_types_type_id, Double> e : list) {
			System.out.println("\t" + e.getKey().name + "\t" + e.getValue());
		}
	}

	public static Double getKinEVHPType(R_get_universe_types_type_id entity) {
		int sig = 20000;
		int speed = 0;
		double shieldKinRes = 1;
		int shieldHP = 0;
		double armorKinRes = 1;
		int armorHP = 0;
		double hullKinRes = 1;
		int hullHP = 0;
		for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : entity.dogma_attributes) {
			switch (att.attribute_id) {
			case 9:
				hullHP = (int) att.value;
				break;
			case 263:
				shieldHP = (int) att.value;
				break;
			case 265:
				armorHP = (int) att.value;
				break;
			case 269:
				armorKinRes = att.value;
				break;
			case 273:
				shieldKinRes = att.value;
				break;
			case 508:
				speed = (int) att.value;
				break;
			case 552:
				sig = (int) att.value;
				break;
			}
		}
		double ehp = shieldHP / shieldKinRes + armorHP / armorKinRes + hullHP / hullKinRes;
		double totalTank = ehp * speed / sig;
		return totalTank;
	}

}
