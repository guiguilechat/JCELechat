package fr.guiguilechat.jcelechat.programs.showattributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_effects_effect_id_modifiers;
import fr.guiguilechat.jcelechat.model.sde.meta.Unit;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;

public class ShowAttributes {

	public static void main(String[] args) {

		// covetor
		// showEntity(17476);
		// hobgoblin
		// showEntity(2454);
		// 250mm railgun II
		// showEntity(3082);
		// ADC II
		// showEntity(47257);
		// domination heavy statis grappler
		// showEntity(41059);
		// CN web
		// showEntity(17500);

		// agent cruor
		// showEntity(34144);
		// agent dramiel
		// showEntity(34141);
		// base angel escort dramiel
		// showEntity(35689);
		// dire pithum mortifier
		// showEntity(24095);
		// arch gistii outlaw
		// showEntity(16895);
		// serpentis artillery
		// showEntity(23428);

		// diamond arbitrator
		// showEntity(43559);
		// liminal jarognik damavik
		// showEntity(54661);
		// VHMF-32
		// showEntity(54279);
		// irregular frigates
		// showGroup(1568);
		// jarognik rodiva
		// showEntity(54675);
		// irregular frigates, cruisers, etc.
		// showGroups(".*Jarognik.*", 1568, 1664, 1665, 1666, 1667, 4053, 1726);
		// showGroups(null, 1568, 1664, 1665, 1666, 1667, 4053, 1726);
		// invading entities
		// showGroup(4028);
		// tableGroup(4028);
		// starving leshak
		// showEntity(48125);
		// invading raznaborg
		// showGroup(".*(Raznaborg).*", 4028);
		// burners
		// showGroups(".*(Burner).*", 818, 817);

		// rakovene
		// showEntity(52315);
		// showGroup(4030);
		// showEntity(18036);

		//
		// showGroup(1666);
		// showGroup(482, "asteroidgroup");

		// drifter bs
		// showGroup(1310);
		//
		// showEntity(47953);
		// lucid preserver
		// showEntity(48251);
		// fieldweaver tessella
		showEntity(47850);

	}

	public static void showEntity(int typeId, String... filters) {
		showEntity(ESIStatic.INSTANCE.cache.universe.types(typeId).get(), filters);
	}

	public static void showEntity(R_get_universe_types_type_id type, String... filters) {
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(type.group_id).get();
		R_get_universe_categories_category_id cat = ESIStatic.INSTANCE.cache.universe.categories(group.category_id).get();
		System.out.println(type.name + "(" + type.type_id + ") group=" + group.name + "(" + group.group_id + ") category="
				+ cat.name + "(" + cat.category_id + ")");
		Set<Integer> usedAttIds = new HashSet<>();
		Map<Integer, Float> attIdToValue = Stream
				.of(type.dogma_attributes != null ? type.dogma_attributes
						: new get_dogma_dynamic_items_type_id_item_id_dogma_attributes[0])
				.collect(Collectors.toMap(att -> att.attribute_id, att -> att.value));

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
				List<String> flags = new ArrayList<>();
				if (e.disallow_auto_repeat) {
					flags.add("disallow_auto_repeat");
				}
				if (e.electronic_chance) {
					flags.add("electronic_chance");
				}
				if (e.is_assistance) {
					flags.add("is_assistance");
				}
				if (e.is_offensive) {
					flags.add("is_offensive");
				}
				if (e.is_warp_safe) {
					flags.add("is_warp_safe");
				}
				if (e.range_chance) {
					flags.add("range_chance");
				}
				if (!flags.isEmpty()) {
					System.out.println("[" + flags.stream().collect(Collectors.joining("|")) + "]");
				} else {
					System.out.println();
				}
				if (e.modifiers != null && e.modifiers.length != 0) {
					for (get_dogma_effects_effect_id_modifiers m : e.modifiers) {
						if (m.modified_attribute_id != 0 && m.modifying_attribute_id != 0) {
							R_get_dogma_attributes_attribute_id modified = ESIStatic.INSTANCE.cache.dogma
									.attributes(m.modified_attribute_id).get();
							usedAttIds.add(m.modifying_attribute_id);
							System.out.println("\t\t\t" + m.func + " : " + m.domain + "." + modified.display_name + " "
									+ getOperator(m.operator) + " " + getAttValue(attIdToValue, m.modifying_attribute_id));
						}
					}
				}
				if (e.range_attribute_id != 0) {
					usedAttIds.add(e.range_attribute_id);
					System.out
					.println("\t\trange(" + e.range_attribute_id + ")=" + printAttValue(attIdToValue, e.range_attribute_id));
				}
				if (e.falloff_attribute_id != 0) {
					usedAttIds.add(e.falloff_attribute_id);
					System.out.println(
							"\t\tfalloff(" + e.falloff_attribute_id + ")=" + printAttValue(attIdToValue, e.falloff_attribute_id));
				}
				if (e.tracking_speed_attribute_id != 0) {
					usedAttIds.add(e.tracking_speed_attribute_id);
					System.out.println("\t\ttracking speed(" + e.tracking_speed_attribute_id + ")="
							+ printAttValue(attIdToValue, e.tracking_speed_attribute_id));
				}
				if (e.duration_attribute_id != 0) {
					usedAttIds.add(e.duration_attribute_id);
					System.out.println(
							"\t\tduration(" + e.duration_attribute_id + ")=" + printAttValue(attIdToValue, e.duration_attribute_id));
				}
				if (e.discharge_attribute_id != 0) {
					usedAttIds.add(e.discharge_attribute_id);
					System.out.println("\t\tdischarge(" + e.discharge_attribute_id + ")="
							+ printAttValue(attIdToValue, e.discharge_attribute_id));
				}
				if (e.pre_expression != 0) {
					System.out.println("\t\tpre=" + e.pre_expression);
				}
				if (e.post_expression != 0) {
					System.out.println("\t\tpost=" + e.post_expression);
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
				return "\t" + dogattr.name + "(" + att.attribute_id + ")=" + printAttValue(attIdToValue, att.attribute_id);
			}).filter(s -> s != null).forEachOrdered(System.out::println);
		}
	}

	public static String printAttValue(Map<Integer, Float> attIdToValue, int attId) {
		R_get_dogma_attributes_attribute_id attribute = ESIStatic.INSTANCE.cache.dogma.attributes(attId).get();
		return getUnit(attribute.unit_id, attIdToValue.getOrDefault(attId, attribute.default_value));
	}

	public static float getAttValue(Map<Integer, Float> attIdToValue, int attId) {
		R_get_dogma_attributes_attribute_id attribute = ESIStatic.INSTANCE.cache.dogma.attributes(attId).get();
		return attIdToValue.getOrDefault(attId, attribute.default_value);
	}

	public static String getUnit(int unit_id, float value) {
		return Unit.load().get(unit_id).value(value);
	}

	public static String getOperator(int opId) {
		switch (opId) {
		case 0:
			return "MULT";
		case 2:
			return "ADD";
		case 7:
			return "MULT";
		default:
			return "?op" + opId;
		}
	}

	public static void showGroup(int groupId, String... filters) {
		R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(groupId).get();
		for (int typeId : group.types) {
			showEntity(typeId, filters);
		}
	}

	public static void showGroups(String nameFilter, int... groupids) {
		IntStream.of(groupids).parallel()
		.flatMap(gid -> IntStream.of(ESIStatic.INSTANCE.cache.universe.groups(gid).get().types))
		.forEach(tid -> ESIStatic.INSTANCE.cache.universe.types(tid));
		for (int groupId : groupids) {
			R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(groupId).get();
			for (int typeId : group.types) {
				R_get_universe_types_type_id type = ESIStatic.INSTANCE.cache.universe.types(typeId).get();
				if (nameFilter != null && !type.name.matches(nameFilter)) {
					continue;
				}
				showEntity(type, (String[]) null);
			}
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
