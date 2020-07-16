package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIModel;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;

/**
 *
 * List entities that can attack light drones, and have a signature above the
 * minimum one.
 *
 */
public class ShowCanAttackDrones {

	public static void main(String[] args) {
		R_get_universe_categories_category_id catEntities = ESIModel.INSTANCE.universe.cache.categories(11).get();
		List<ObsObjHolder<R_get_universe_groups_group_id>> groupholders = IntStream.of(catEntities.groups)
				.mapToObj(gid -> ESIModel.INSTANCE.universe.cache.groups(gid)).collect(Collectors.toList());
		List<R_get_universe_groups_group_id> groups = groupholders.stream().map(ObsObjHolder::get)
				.collect(Collectors.toList());
		List<ObsObjHolder<R_get_universe_types_type_id>> typeHolders = groups.stream()
				.flatMap(g -> IntStream.of(g.types).mapToObj(tid -> ESIModel.INSTANCE.universe.cache.types(tid)))
				.collect(Collectors.toList());
		List<R_get_universe_types_type_id> types = typeHolders.stream().map(ObsObjHolder::get).collect(Collectors.toList());
		Collections.sort(types, (t1, t2) -> t1 == null || t2 == null ? 0 : t1.name.compareTo(t2.name));
		System.out.println("name(id)\tsignature\tgroup\tdronesig");
		for (R_get_universe_types_type_id t : types) {
			if (t.dogma_attributes == null) {
				continue;
			}
			float maxdronesize = -1;
			float speed = 0;
			float signature = -1;
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : t.dogma_attributes) {
				if (att.attribute_id == 1855) {
					maxdronesize = att.value;
				}
				if (att.attribute_id == 51) {
					speed = att.value;
				}
				if (att.attribute_id == 552) {
					signature = att.value;
				}
			}
			if (speed > 0 && maxdronesize <= 25 && maxdronesize >= 0 && signature >= 70) {
				var group = ESIModel.INSTANCE.universe.cache.groups(t.group_id).get();
				System.out.println(t.name + "(" + t.type_id + ")" + "\t" + signature + "\t" + group.name + "\t" + maxdronesize);
			}
		}
	}

}
