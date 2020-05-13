package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.EHP;
import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.MissileDPS;
import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.Name;
import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.OrbitRange;
import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.TrackingEvasion;
import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.TurretDPS;
import fr.guiguilechat.jcelechat.jcesi.disconnected.typeData.TurretMaxDPS;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;

public class ShowData {

	public static void main(String[] args) {
		TypeData[] data = { Name.INS,
				//
				// Id.INS,
				//
				EHP.EM, EHP.TH, EHP.KI, EHP.EX, OrbitRange.INS, TrackingEvasion.INS,
				//
				TurretDPS.EM, TurretDPS.TH, TurretDPS.KI, TurretDPS.EX,
				//
				TurretMaxDPS.EM, TurretMaxDPS.TH, TurretMaxDPS.KI, TurretMaxDPS.EX,
				//
				MissileDPS.EM, MissileDPS.TH, MissileDPS.KI, MissileDPS.EX
		};
		// irregular frigates, destroyers, cruisers, battlecruisers, battleships,
		// capsule, carrier
		// showGroup(".*(Jarognik).*", new int[] { 1568, 1664, 1665, 1666, 1667,
		// 4053, 1726 }, data);
		// irregular frigates
		// showGroup(null, new int[] { 1568 }, data);
		// burner frigates
		// showGroup(".*(Burner).*", new int[] { 818, 817 },data);
		// invading trigs
		showGroup(".*(Raznaborg Damavik).*", new int[] { 4028 }, data);
	}

	public static void showGroup(String nameFilter, int[] groupIds, TypeData... typedatas) {
		// preload the group
		IntStream.of(groupIds).parallel()
		.flatMap(groupId -> IntStream.of(ESIStatic.INSTANCE.cache.universe.groups(groupId).get().types))
		.forEach(i -> ESIStatic.INSTANCE.cache.universe.types(i));
		HashMap<TypeData, List<String>> data2values = new HashMap<>();
		for (TypeData td : typedatas) {
			data2values.put(td, new ArrayList<>());
		}
		int entitynb = 0;
		for (int groupId : groupIds) {
			R_get_universe_groups_group_id group = ESIStatic.INSTANCE.cache.universe.groups(groupId).get();
			for (int typeId : group.types) {
				R_get_universe_types_type_id type = ESIStatic.INSTANCE.cache.universe.types(typeId).get();
				if (nameFilter != null && !type.name.matches(nameFilter)) {
					continue;
				} else {
					addEntityValues(type, data2values, typedatas);
					entitynb++;
				}
			}
		}
		List<TypeData> keptData = Stream.of(typedatas)
				.filter(td -> data2values.get(td).stream().filter(s -> s != null).findAny().isPresent())
				.collect(Collectors.toList());
		StringBuilder sb = null;
		for (TypeData td : keptData) {
			sb = sb == null ? new StringBuilder(td.name()) : sb.append("\t").append(td.name());
		}
		System.out.println(sb);
		for (int i = 0; i < entitynb; i++) {
			sb = null;
			for (TypeData td : keptData) {
				String data = data2values.get(td).get(i);
				if (data == null) {
					data = ".";
				}
				sb = sb == null ? new StringBuilder(data) : sb.append("\t").append(data);
			}
			System.out.println(sb);
		}
	}

	public static void addEntityValues(R_get_universe_types_type_id type, Map<TypeData, List<String>> data2values,
			TypeData... typedatas) {
		Map<Integer, Double> attIdToValue = Stream
				.of(type.dogma_attributes != null ? type.dogma_attributes
						: new get_dogma_dynamic_items_type_id_item_id_dogma_attributes[0])
				.collect(Collectors.toMap(att -> att.attribute_id, att -> (double) att.value));
		Map<Integer, get_dogma_dynamic_items_type_id_item_id_dogma_effects> effectId2effect = Stream
				.of(type.dogma_effects == null ? new get_dogma_dynamic_items_type_id_item_id_dogma_effects[] {}
				: type.dogma_effects)
				.collect(Collectors.toMap(effect -> effect.effect_id, e -> e));
		for (TypeData td : typedatas) {
			String data = td.apply(type, attIdToValue, effectId2effect);
			data2values.get(td).add(data);
		}
	}

}
