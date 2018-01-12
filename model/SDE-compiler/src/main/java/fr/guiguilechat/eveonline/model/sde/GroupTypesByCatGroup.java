package fr.guiguilechat.eveonline.model.sde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.sde.bsd.EdgmAttributeTypes;
import fr.guiguilechat.eveonline.model.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.model.sde.cache.SDECache;
import fr.guiguilechat.eveonline.model.sde.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.model.sde.fsd.EtypeIDs;

public class GroupTypesByCatGroup {

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
		HashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> typeAttributes = EdgmTypeAttributes
				.loadByTypeIDAttributeID();
		HashMap<Integer, EdgmAttributeTypes> attributesTypes = EdgmAttributeTypes.loadByAttributeID();
		LinkedHashMap<Integer, EgroupIDs> groups = EgroupIDs.load();

		LinkedHashMap<Integer, List<Integer>> groupToTypes = new LinkedHashMap<>();
		LinkedHashMap<Integer, EtypeIDs> indexToType = EtypeIDs.load();
		indexToType.forEach((i, e) -> {
			List<Integer> g = groupToTypes.get(e.groupID);
			if (g == null) {
				g = new ArrayList<>();
				groupToTypes.put(e.groupID, g);
			}
			g.add(i);
		});
		HashMap<Integer, EdgmTypeAttributes> emptyTypesMap = new HashMap<>();
		// for each group i, the attributes all elements of this group have
		LinkedHashMap<Integer, Set<Integer>> groupToAttributes = new LinkedHashMap<>();
		for (int groupId : groupToTypes.keySet()) {
			List<Integer> types = groupToTypes.get(groupId);
			// first we get all the attributes existing for a typeId of this group.
			Set<Integer> possibleAttributes = groupToTypes.get(groupId).stream()
					.flatMap(typeId -> typeAttributes
							.getOrDefault(typeId, emptyTypesMap)
							.keySet().stream())
					.collect(Collectors.toSet()) ;
			// second pass, this time removing the elemnts which are not present
			types.forEach(i -> {
				// System.err.println(i + ": " + typeAttributes.get(i));
				possibleAttributes.retainAll(typeAttributes.getOrDefault(i, emptyTypesMap).keySet());
			});
			groupToAttributes.put(groupId, possibleAttributes);
		}
		for (Entry<Integer, Set<Integer>> e : groupToAttributes.entrySet()) {
			System.out.println(groups.get(e.getKey()).enName() + ": ");
			for (Integer i : e.getValue()) {
				EdgmAttributeTypes type = attributesTypes.get(i);
				System.out.println("  " + type.attributeName + " : " + type.description);
			}
		}
	}

}
