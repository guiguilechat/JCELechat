package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;

public class ShowFrigateTracking {

	public static void main(String[] args) {
		boolean showdetails = false;
		R_get_universe_categories_category_id cat = ESIAccess.INSTANCE.universe.cache.categories(11).get();
		List<ObsObjHolder<R_get_universe_groups_group_id>> groupholders = IntStream.of(cat.groups)
				.mapToObj(gid -> ESIAccess.INSTANCE.universe.cache.groups(gid)).collect(Collectors.toList());
		List<R_get_universe_groups_group_id> groups = groupholders.stream().map(ObsObjHolder::get)
				.collect(Collectors.toList());
		List<ObsObjHolder<R_get_universe_types_type_id>> typeHolders = groups
				.stream()
				.flatMap(g -> IntStream.of(g.types).mapToObj(tid -> ESIAccess.INSTANCE.universe.cache.types(tid)))
				.collect(Collectors.toList());
		List<R_get_universe_types_type_id> types = typeHolders.stream().map(ObsObjHolder::get).collect(Collectors.toList());
		Collections.sort(types, (t1, t2) -> t1 == null || t2 == null ? 0 : t1.name.compareTo(t2.name));
		System.out.println("name\ttracking20km\ttracking50km\ttracking80km" + (showdetails ? "\tspeed\tsig\torbit" : ""));
		for (R_get_universe_types_type_id t : types) {
			if (t == null || t.radius > 80 || t.dogma_attributes == null) {
				continue;
			}
			float speed = 0;
			float orbit = 0;
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : t.dogma_attributes) {
				if (att.attribute_id == 51) {
					speed = att.value;
				}
				if (att.attribute_id == 416) {
					orbit = att.value;
				}
			}
			if (speed <= 0) {
				// System.err.println("skip " + t.name + " for small speed");
				continue;
			}
			if (orbit <= 0) {
				// System.err.println("skip " + t.name + " for small orbit");
				continue;
			}
			float t20 = speed * orbit / 20000 / 20000 * 40000 / t.radius;
			float t50 = speed * orbit / 50000 / 50000 * 40000 / t.radius;
			float t80 = speed * orbit / 80000 / 80000 * 40000 / t.radius;
			System.out.println(t.name + "\t" + t20 + "\t" + t50 + "\t" + t80
					+ (showdetails ? "\t" + speed + "\t" + t.radius + "\t" + orbit : ""));
		}
	}

}
