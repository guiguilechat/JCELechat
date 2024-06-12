package fr.guiguilechat.jcelechat.model.sde.loaders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
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
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;


public class ESILoader {

	private static final Logger logger = LoggerFactory.getLogger(ESILoader.class);

	public static TypeHierarchy load() {
		TypeHierarchy ret = new TypeHierarchy();
		// first preload everything
		Universe universe = ESIRawPublic.INSTANCE.cache().universe;
		Dogma dogma = ESIRawPublic.INSTANCE.cache().dogma;

		// types and their attributes
		
		MapHolder<Integer, R_get_universe_types_type_id> typeMap = universe.types()
		    .mapItems(typeID -> universe.types(typeID))
		    .toMap(h -> h.get().type_id, ObjHolder::get);

		HashSet<Integer> floatAttributeIds = new HashSet<>();
		HashSet<Integer> allAttributesIds = new HashSet<>();
		HashSet<Integer> typesGroupIds = new HashSet<>();
		HashSet<Integer> typesAttributeIds = new HashSet<>();

		for (Entry<Integer, R_get_universe_types_type_id> e : typeMap.get().entrySet()) {
			TypeDetails det = new TypeDetails();
			det.name = e.getValue().name;
			det.id = e.getKey();
			// TODO
			// det.basePrice = e.getValue().;
			det.marketGroupID = e.getValue().market_group_id;
			det.groupID = e.getValue().group_id;
			typesGroupIds.add(det.groupID);
			det.mass = e.getValue().mass;
			det.packagedVolume = e.getValue().packaged_volume;
			det.published = e.getValue().published;
			det.portionSize = e.getValue().portion_size;
			det.volume = e.getValue().volume;
			ret.typeID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.computeIfAbsent(det.groupID, i -> new HashSet<>()).add(e.getKey());
			if (e.getValue().dogma_attributes != null) {
				for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes attribute : e.getValue().dogma_attributes) {
					typesAttributeIds.add(attribute.attribute_id);
					if (attribute.attribute_id == 161) {
						det.volume = attribute.value;
					} else if (attribute.attribute_id == 4) {
						det.mass = attribute.value;
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

		Set<Integer> esiMissingAttributeIds = new HashSet<>();

		MapHolder<Integer, R_get_dogma_attributes_attribute_id> attMap = dogma.attributes()
		    .mapList(l -> {
			    Set<Integer> s = new HashSet<>(l);
			    s.addAll(typesAttributeIds);
			    if (s.size() > l.size()) {
				    esiMissingAttributeIds.addAll(typesAttributeIds);
				    esiMissingAttributeIds.removeAll(l);
			    }
			    return new ArrayList<>(s);
		    })
		    .toMap(typeID -> typeID, typeID -> dogma.attributes(typeID)).entries()
		    .toMap(Entry::getKey, e -> e.getValue().get());

		for (int attId : allAttributesIds) {
			R_get_dogma_attributes_attribute_id eattr = attMap.get().get(attId);
			if (eattr == null) {
				throw new NullPointerException("no attribute data for id "+attId);
			}
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

		// groups

		HashSet<Integer> groupsCategoryIds = new HashSet<>();
		Set<Integer> esiMissingGroupIds = new HashSet<>();

		MapHolder<Integer, R_get_universe_groups_group_id> groupMap = universe.groups()
		    .mapList(l -> {
			    Set<Integer> s = new HashSet<>(l);
			    s.addAll(typesGroupIds);
			    if (s.size() > l.size()) {
				    esiMissingGroupIds.addAll(typesGroupIds);
				    esiMissingGroupIds.removeAll(l);
			    }
			    return new ArrayList<>(s);
		    })
		    .mapItems(groupID -> universe.groups(groupID))
		    .toMap(h -> h.get().group_id, ObjHolder::get);

		for (Entry<Integer, R_get_universe_groups_group_id> e : groupMap.get().entrySet()) {
			GroupDetails det = new GroupDetails();
			det.id = e.getKey();
			det.catID = e.getValue().category_id;
			groupsCategoryIds.add(det.catID);
			det.name = e.getValue().name;
			det.published = e.getValue().published;
			ret.groupID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.computeIfAbsent(det.id, i -> new HashSet<>());
			ret.catID2GroupIDs.computeIfAbsent(e.getValue().category_id, i -> new HashSet<>()).add(e.getKey());
		}

		// categories
		Set<Integer> esiMissingCategoryIds = new HashSet<>();

		MapHolder<Integer, R_get_universe_categories_category_id> catMap = universe.categories()
		    .mapList(l -> {
			    Set<Integer> s = new HashSet<>(l);
			    s.addAll(groupsCategoryIds);
			    if (s.size() > l.size()) {
				    esiMissingCategoryIds.addAll(groupsCategoryIds);
				    esiMissingCategoryIds.removeAll(l);
			    }
			    return new ArrayList<>(s);
		    })
		    .mapItems(catid -> universe.categories(catid))
		    .toMap(h -> h.get().category_id, ObjHolder::get);

		for (Entry<Integer, R_get_universe_categories_category_id> e : catMap.get().entrySet()) {
			CatDetails det = new CatDetails();
			det.id = e.getKey();
			det.name = e.getValue().name;
			det.published = e.getValue().published;
			ret.catID2Details.put(e.getKey(), det);
			ret.catID2GroupIDs.computeIfAbsent(det.id, i -> new HashSet<>());
		}

		if (!esiMissingAttributeIds.isEmpty()) {
			logger.warn("missing attributes from esi list : " + esiMissingAttributeIds);
		}
		if (!esiMissingGroupIds.isEmpty()) {
			logger.warn("missing groups from esi list : " + esiMissingGroupIds);
		}
		if (!esiMissingCategoryIds.isEmpty()) {
			logger.warn("missing categories from esi list : " + esiMissingCategoryIds);
		}

		return ret;
	}

}
