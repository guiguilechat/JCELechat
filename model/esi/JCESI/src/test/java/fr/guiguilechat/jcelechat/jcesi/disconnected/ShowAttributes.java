package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_effects_effect_id_modifiers;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;

public class ShowAttributes {

	public static void main(String[] args) {
		// invading entities
		// showGroup(4028, "armor", "Resonance");
		// tableGroup(4028);

		// hobgoblin
		// showEntity(2454);

		// starving leshak
		// showEntity(48125);

		// agent cruor
		showEntity(34144);

		// rakovene
		// showEntity(52315);
		// showGroup(4030);
		// showEntity(18036);
		// showGroup(482, "asteroidgroup");

		// drifter bs
		// showGroup(1310);
	}

	public static void showEntity(int typeId, String... filters) {
		R_get_universe_types_type_id type = ESIStatic.INSTANCE.cache.universe.types(typeId).get();
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(type.group_id).get();
		R_get_universe_categories_category_id cat = ESIStatic.INSTANCE.cache.universe.categories(group.category_id).get();
		System.out.println(type.name + "(" + typeId + ") group=" + group.name + "(" + group.group_id + ") category="
				+ cat.name + "(" + cat.category_id + ")");
		Set<Integer> usedAttIds = new HashSet<>();
		Map<Integer, Float> attIdToValue = Collections.emptyMap();
		if (type.dogma_attributes != null) {
			attIdToValue = Stream.of(type.dogma_attributes)
					.collect(Collectors.toMap(att -> att.attribute_id, att -> att.value));
		}
		if (type.dogma_effects != null) {
			List<ObsObjHolder<R_get_dogma_effects_effect_id>> effects = Stream.of(type.dogma_effects)
					.map(eff -> ESIStatic.INSTANCE.cache.dogma.effects(eff.effect_id)).collect(Collectors.toList());
			for (ObsObjHolder<R_get_dogma_effects_effect_id> h : effects) {
				R_get_dogma_effects_effect_id e = h.get();
				if (filters != null && filters.length != 0) {
					// if one of the filters does not match the effect name, skip the
					// effect.
					if (Stream.of(filters).filter(filter -> !e.name.toLowerCase().contains(filter.toLowerCase())).findAny()
							.isPresent()) {
						continue;
					}
				}
				System.out
				.print("\t" + e.name + "(" + e.effect_id + ")" + "category=" + e.effect_category + " " + e.description);
				List<String> booleans = new ArrayList<>();
				if (e.disallow_auto_repeat) {
					booleans.add("disallow_auto_repeat");
				}
				if (e.electronic_chance) {
					booleans.add("electronic_chance");
				}
				if (e.is_assistance) {
					booleans.add("is_assistance");
				}
				if (e.is_offensive) {
					booleans.add("is_offensive");
				}
				if (e.is_warp_safe) {
					booleans.add("is_warp_safe");
				}
				if (e.range_chance) {
					booleans.add("range_chance");
				}
				if (!booleans.isEmpty()) {
					System.out.println("[" + booleans.stream().collect(Collectors.joining("|")) + "]");
				} else {
					System.out.println();
				}
				if (e.modifiers != null) {
					for (get_dogma_effects_effect_id_modifiers m : e.modifiers) {
						System.out.println("\t\t" + m.func + "|" + m.domain);
					}
				}
				if (e.range_attribute_id != 0) {
					usedAttIds.add(e.range_attribute_id);
					System.out.println(
							"\t\trange(" + e.range_attribute_id + ")=" + attIdToValue.get(e.range_attribute_id));
				}
				if (e.falloff_attribute_id != 0) {
					usedAttIds.add(e.falloff_attribute_id);
					System.out.println(
							"\t\tfalloff(" + e.falloff_attribute_id + ")=" + attIdToValue.get(e.falloff_attribute_id));
				}
				if (e.tracking_speed_attribute_id != 0) {
					usedAttIds.add(e.tracking_speed_attribute_id);
					System.out.println("\t\ttracking(" + e.tracking_speed_attribute_id + ")="
							+ attIdToValue.get(e.tracking_speed_attribute_id));
				}
				if (e.duration_attribute_id != 0) {
					usedAttIds.add(e.duration_attribute_id);
					System.out.println("\t\tduration(" + e.duration_attribute_id + ")="
							+ attIdToValue.get(e.duration_attribute_id));
				}
				if (e.discharge_attribute_id != 0) {
					usedAttIds.add(e.discharge_attribute_id);
					System.out
					.println("\t\tdischarge(" + e.discharge_attribute_id + ")=" + attIdToValue.get(e.discharge_attribute_id));
				}
			}
		}
		if (type.dogma_attributes != null) {
			Stream.of(type.dogma_attributes).sorted((a1, a2) -> a1.attribute_id - a2.attribute_id).parallel().map(att -> {
				R_get_dogma_attributes_attribute_id dogattr = ESIStatic.INSTANCE.cache.dogma.attributes(att.attribute_id).get();
				if (filters != null) {
					for (String filter : filters) {
						if (filter != null && !dogattr.name.toLowerCase().contains(filter.toLowerCase())) {
							return null;
						}
					}
				}
				if (usedAttIds.contains(att.attribute_id)) {
					return null;
				}
				return "\t" + dogattr.name + "(" + att.attribute_id + ")=" + att.value;
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
				if (attkv != null) {
					System.out.print("\t" + attkv.value);
				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}

}
