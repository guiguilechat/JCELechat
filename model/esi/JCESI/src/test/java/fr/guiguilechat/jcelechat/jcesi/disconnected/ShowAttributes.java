package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;

public class ShowAttributes {

	public static void main(String[] args) {
		// invading entities
		// showGroup(4028, "armor", "Resonance");
		// tableGroup(4028);

		// hobgoblin
		// showEntity(2454);

		// starving leshak
		// showEntity(48125);

		// rakovene
		// showEntity(52315);
		// showGroup(4030);
		// showEntity(18036);
		// showGroup(482, "asteroidgroup");

		// drifter bs
		showGroup(1310);
	}

	public static void showEntity(int typeId, String... filters) {
		R_get_universe_types_type_id type = ESIStatic.INSTANCE.cache.universe.types(typeId).get();
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(type.group_id).get();
		R_get_universe_categories_category_id cat = ESIStatic.INSTANCE.cache.universe.categories(group.category_id).get();
		System.out.println(type.name + "(" + typeId + ") group=" + group.name + "(" + group.group_id + ") category="
				+ cat.name + "(" + cat.category_id + ")");
		if (type.dogma_attributes != null) {
			Stream.of(type.dogma_attributes)
			.sorted((a1, a2) -> a1.attribute_id - a2.attribute_id)
			.parallel()
			.map(att -> {
				R_get_dogma_attributes_attribute_id dogattr = ESIStatic.INSTANCE.cache.dogma.attributes(att.attribute_id).get();
				if (filters != null) {
					for (String filter : filters) {
						if (filter != null && !dogattr.name.toLowerCase().contains(filter.toLowerCase())) {
							return null;
						}
					}
				}
				return "\t" + dogattr.name + " (" + att.attribute_id + ") " + att.value;
			}).filter(s -> s != null).forEachOrdered(System.out::println);
		}
	}

	public static void showGroup(int groupId, String... filters) {
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(groupId).get();
		for (int typeId : group.types) {
			showEntity(typeId, filters);
		}
	}

	public static List<R_get_dogma_attributes_attribute_id> listAttributes(List<R_get_universe_types_type_id> types) {
		Set<Integer> attIds = new HashSet<>();
		for (R_get_universe_types_type_id type : types) {
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes attribute : type.dogma_attributes) {
				attIds.add(attribute.attribute_id);
			}
		}
		List<R_get_dogma_attributes_attribute_id> ret = new ArrayList<>();
		attIds.stream().sorted().forEach(attId -> ret.add(ESIStatic.INSTANCE.cache.dogma.attributes(attId).get()));
		return ret;
	}

	public static List<R_get_universe_types_type_id> typesInGroup(int groupId) {
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(groupId).get();
		return IntStream.of(group.types).mapToObj(i -> ESIStatic.INSTANCE.cache.universe.types(i).get())
				.collect(Collectors.toList());
	}

	public static void tableGroup(int groupId) {
		List<R_get_universe_types_type_id> types = typesInGroup(groupId);
		List<R_get_dogma_attributes_attribute_id> atts = listAttributes(types);
		System.out.print("name");
		for (R_get_dogma_attributes_attribute_id att : atts) {
			System.out.print("\t" + att.name);
		}
		System.out.println();
		for (R_get_universe_types_type_id type : types) {
			System.out.print(type.name);
			for (R_get_dogma_attributes_attribute_id att : atts) {
				get_dogma_dynamic_items_type_id_item_id_dogma_attributes attkv = Stream.of(type.dogma_attributes)
						.filter(kv -> kv.attribute_id == att.attribute_id).findFirst().orElse(null);
				if(attkv!=null) {
					System.out.print("\t" + attkv.value);
				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}

}
