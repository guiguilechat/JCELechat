package fr.guiguilechat.jcelechat.programs;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Dogma;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Universe;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EcategoryIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EdogmaAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;

public class CheckESISDE {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		// first preload everything
		Universe universe = ESIStatic.INSTANCE.cache().universe;
		Dogma dogma = ESIStatic.INSTANCE.cache().dogma;
		ObsMapHolder<Integer, R_get_universe_categories_category_id> catMap = universe.categories()
				.mapItems(catid -> universe.categories(catid)).toMap(h -> h.get().category_id, h -> h.get());
		ObsMapHolder<Integer, R_get_universe_groups_group_id> groupMap = universe.groups()
				.mapItems(groupID -> universe.groups(groupID)).toMap(h -> h.get().group_id, h -> h.get());
		ObsMapHolder<Integer, R_get_universe_types_type_id> typeMap = universe.types()
				.mapItems(typeID -> universe.types(typeID)).toMap(h -> h.get().type_id, h -> h.get());
		ObsMapHolder<Integer, R_get_dogma_attributes_attribute_id> attMap = dogma.attributes()
				.mapItems(typeID -> dogma.attributes(typeID)).toMap(h -> h.get().attribute_id, h -> h.get());
		EcategoryIDs.load();
		EgroupIDs.load();
		EtypeIDs.load();
		EdogmaAttributes.load();

		long postLoad = System.currentTimeMillis();
		System.out.println("loaded in " + (postLoad - start) + "ms");
		int errors = 0;

		for (Entry<Integer, EcategoryIDs> e : EcategoryIDs.load().entrySet()) {
			EcategoryIDs sdeEntry = e.getValue();
			R_get_universe_categories_category_id esiEntry = catMap.get().get(e.getKey());
			if (sdeEntry.published != esiEntry.published) {
				System.out.println("cat=" + e.getKey() + "(" + esiEntry.name + ")" + " esi=" + esiEntry.published + " sde="
						+ sdeEntry.published);
				errors++;
			}
		}
		for (Entry<Integer, EgroupIDs> e : EgroupIDs.load().entrySet()) {
			EgroupIDs sdeEntry = e.getValue();
			R_get_universe_groups_group_id esiEntry = groupMap.get().get(e.getKey());
			if (sdeEntry.published != esiEntry.published) {
				System.out.println("group=" + e.getKey() + "(" + esiEntry.name + ")" + " esi=" + esiEntry.published + " sde="
						+ sdeEntry.published);
				errors++;
			}
		}
		for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
			EtypeIDs sdeEntry = e.getValue();
			R_get_universe_types_type_id esiEntry = typeMap.get().get(e.getKey());
			if (sdeEntry.published != esiEntry.published) {
				System.out.println("type=" + e.getKey() + "(" + esiEntry.name + ")" + " esi=" + esiEntry.published + " sde="
						+ sdeEntry.published);
				errors++;
			}
		}

		Set<Integer> attributeIds = new HashSet<>();
		Map<Integer, EdogmaAttributes> attSDEMap = EdogmaAttributes.load();
		attributeIds.addAll(attSDEMap.keySet());
		attributeIds.addAll(attMap.get().keySet());

		for (int attId : attributeIds) {
			EdogmaAttributes sdeEntry = attSDEMap.get(attId);
			R_get_dogma_attributes_attribute_id esiEntry = attMap.get().get(attId);
			if (sdeEntry == null) {
				System.out.println("attid=" + attId + " null in SDE, name in ESI=" + esiEntry.name);
				errors++;
			}
			if (esiEntry == null) {
				System.out.println("attid=" + attId + " null in ESI, name in SDE=" + sdeEntry.name);
				errors++;
			}
			if (sdeEntry != null && esiEntry != null) {
				if (sdeEntry.published != esiEntry.published) {
					System.out.println(
							"att=" + attId + "(" + esiEntry.name + ")" + " esi=" + esiEntry.published + " sde=" + sdeEntry.published);
					errors++;
				}
				if (!sdeEntry.name.equals(esiEntry.name)) {
					System.out.println("att=" + attId + " esiname=" + esiEntry.name + " sdename=" + sdeEntry.name);
					errors++;
				}
			}
		}
		System.out.println("found " + errors + " errors in " + (System.currentTimeMillis() - postLoad) + " ms");

	}

}
