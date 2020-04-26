package fr.guiguilechat.jcelechat.model.sde.loaders;

import java.util.HashSet;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Dogma;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Universe;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.AttributeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;

public class ESILoader {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ESILoader.class);

	public static TypeHierarchy load() {
		TypeHierarchy ret = new TypeHierarchy();
		// first preload everything
		Universe universe = ESIStatic.INSTANCE.cache.universe;
		Dogma dogma = ESIStatic.INSTANCE.cache.dogma;
		ObsMapHolder<Integer, R_get_universe_categories_category_id> catMap = universe.categories()
				.mapItems(catid -> universe.categories(catid)).toMap(h -> h.get().category_id, h -> h.get());
		ObsMapHolder<Integer, R_get_universe_groups_group_id> groupMap = universe.groups()
				.mapItems(groupID -> universe.groups(groupID)).toMap(h -> h.get().group_id, h -> h.get());
		ObsMapHolder<Integer, R_get_universe_types_type_id> typeMap = universe.types()
				.mapItems(typeID -> universe.types(typeID)).toMap(h -> h.get().type_id, h -> h.get());
		ObsMapHolder<Integer, R_get_dogma_attributes_attribute_id> attMap = dogma.attributes()
				.mapItems(typeID -> dogma.attributes(typeID)).toMap(h -> h.get().attribute_id, h -> h.get());

		// categories

		for (Entry<Integer, R_get_universe_categories_category_id> e : catMap.get().entrySet()) {
			CatDetails det = new CatDetails();
			det.id = e.getKey();
			det.name = e.getValue().name;
			det.published = e.getValue().published;
			ret.catID2Details.put(e.getKey(), det);
			ret.catID2GroupIDs.put(det.id, new HashSet<>());
		}

		// groups

		for (Entry<Integer, R_get_universe_groups_group_id> e : groupMap.get().entrySet()) {
			GroupDetails det = new GroupDetails();
			det.id = e.getKey();
			det.catID = e.getValue().category_id;
			det.name = e.getValue().name;
			det.published = e.getValue().published;
			ret.groupID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.put(det.id, new HashSet<>());
			ret.catID2GroupIDs.computeIfAbsent(e.getValue().category_id, i -> new HashSet<>()).add(e.getKey());
		}

		// types and their attributes

		HashSet<Integer> floatAttributeIds = new HashSet<>();
		HashSet<Integer> allAttributesIds = new HashSet<>();

		for (Entry<Integer, R_get_universe_types_type_id> e : typeMap.get().entrySet()) {
			TypeDetails det = new TypeDetails();
			det.name = e.getValue().name;
			det.id = e.getKey();
			// TODO
			// det.basePrice = e.getValue().;
			det.marketGroupID = e.getValue().market_group_id;
			det.groupID = e.getValue().group_id;
			det.mass = e.getValue().mass;
			det.published = e.getValue().published;
			det.volume = e.getValue().volume;
			ret.typeID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.computeIfAbsent(det.groupID, i -> new HashSet<>()).add(e.getKey());
			if (e.getValue().dogma_attributes != null) {
				for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes attribute : e.getValue().dogma_attributes) {
					if (attribute.attribute_id == 161) {
						det.volume = attribute.value;
					} else {
						int attId = attribute.attribute_id;
						float floatValue = attribute.value;
						det.definition.put(attId, floatValue);
						if ((int) floatValue != floatValue) {
							floatAttributeIds.add(attId);
						}
						allAttributesIds.add(attId);
					}
				}
			}
		}

		// attributes

		for (int attId : allAttributesIds) {
			R_get_dogma_attributes_attribute_id eattr = attMap.get(attId);
			AttributeDetails det = new AttributeDetails();
			det.defaultValue = eattr.default_value;
			det.description = eattr.description;
			det.hasFloat = floatAttributeIds.contains(attId);
			det.highIsGood = eattr.high_is_good;
			det.id = attId;
			det.name = eattr.name;
			det.published = eattr.published;
			det.stackable = eattr.stackable;
			ret.attID2Details.put(attId, det);
		}

		return ret;
	}

}
