package fr.guiguilechat.jcelechat.model.sde.loaders;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.hierarchy.AttributeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EdgmAttributeTypes;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EdgmTypeAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EcategoryIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;

public class SDELoader {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SDELoader.class);

	public static TypeHierarchy load() {
		LinkedHashMap<Integer, EdgmAttributeTypes> attTypes = EdgmAttributeTypes.loadByAttributeID();
		TypeHierarchy ret = new TypeHierarchy();
		// categories
		for (Entry<Integer, EcategoryIDs> e : EcategoryIDs.load().entrySet()) {
			CatDetails det = new CatDetails();
			det.id = e.getKey();
			det.name = e.getValue().enName();
			det.published = e.getValue().published;
			ret.catID2Details.put(e.getKey(), det);
			ret.catID2GroupIDs.put(det.id, new HashSet<>());
		}
		// groups
		for (Entry<Integer, EgroupIDs> e : EgroupIDs.load().entrySet()) {
			GroupDetails det = new GroupDetails();
			det.id = e.getKey();
			det.catID = e.getValue().categoryID;
			det.name = e.getValue().enName();
			det.published = e.getValue().published;
			ret.groupID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.put(det.id, new HashSet<>());
			ret.catID2GroupIDs.computeIfAbsent(e.getValue().categoryID, i -> new HashSet<>()).add(e.getKey());
		}
		// types
		for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
			TypeDetails det = new TypeDetails();
			det.name = e.getValue().enName();
			det.id = e.getKey();
			det.basePrice = e.getValue().basePrice;
			det.marketGroupID = e.getValue().marketGroupID;
			det.groupID = e.getValue().groupID;
			det.mass = e.getValue().mass;
			det.published = e.getValue().published;
			det.volume = e.getValue().volume;
			ret.typeID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.computeIfAbsent(e.getValue().groupID, i -> new HashSet<>()).add(e.getKey());
		}

		// Attributes

		HashSet<Integer> floatAttributeIds = new HashSet<>();
		HashSet<Integer> allAttributesIds = new HashSet<>();
		for (EdgmTypeAttributes attribute : EdgmTypeAttributes.load()) {
			int attId = attribute.attributeID;
			int typeID = attribute.typeID;
			TypeDetails td = ret.typeID2Details.get(typeID);
			if (td == null) {
				td = new TypeDetails();
				td.name = "unknown_" + typeID;
				ret.typeID2Details.put(typeID, td);
			}
			float floatValue = attribute.valueFloat == 0.0f ? attribute.valueInt : attribute.valueFloat;
			td.definition.put(attId, floatValue);
			// add the attribute to the list of those with a float value
			if ((int) floatValue != floatValue) {
				floatAttributeIds.add(attId);
			}
			allAttributesIds.add(attId);
		}
		for (int attId : allAttributesIds) {
			EdgmAttributeTypes eattr = attTypes.get(attId);
			AttributeDetails det = new AttributeDetails();
			det.categoryID = eattr.categoryID;
			det.defaultValue = eattr.defaultValue;
			det.description = eattr.description;
			det.hasFloat = floatAttributeIds.contains(attId);
			det.highIsGood = eattr.highIsGood;
			det.id = attId;
			det.name = eattr.attributeName;
			det.published = eattr.published;
			det.stackable = eattr.stackable;
			ret.attID2Details.put(attId, det);
		}
		return ret;
	}

}
