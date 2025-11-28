package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.Material;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ProducingActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;

public class ShowSelfRequiringBP {

	public static void main(String[] args) {
		System.out.println("bpName\tbpId\tbpPublished\tprodName\tprodId\tprodPublished");
		Eblueprints.LOADER.yaml().load().entrySet().forEach(e -> showBp(e.getKey(), e.getValue()));
	}

	private static void showBp(Integer key, Eblueprints value) {
		value.activities().forEach(act -> showAct(key, value, act));
	}

	private static void showAct(Integer key, Eblueprints value, ActivityDetails act) {
		if (act instanceof ProducingActivityDetails pad
				&& pad.materials != null && !pad.materials.isEmpty()
				&& pad.products != null && !pad.products.isEmpty()) {
			Set<Integer> productIds = pad.products.stream().map(p -> p.typeID).collect(Collectors.toSet());
			for (Material m : act.materials) {
				if (productIds.contains(m.typeID)) {
					Etypes bpType = Etypes.LOADER.yaml().get(key);
					Etypes prodType = Etypes.LOADER.yaml().get(m.typeID);
					System.out.println(bpType.enName() + "\t" + key + "\t" + bpType.published + "\t" + prodType.enName()
							+ "\t" + m.typeID + "\t" + prodType.published);
				}
			}
		}
	}

}