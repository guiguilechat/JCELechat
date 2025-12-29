package fr.guiguilechat.jcelechat.libs.exports.typecompiler.loaders;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.exports.typecompiler.hierarchy.AttributeDetails;
import fr.guiguilechat.jcelechat.libs.exports.typecompiler.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.libs.exports.typecompiler.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.libs.exports.typecompiler.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.libs.exports.typecompiler.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Ecategories;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaAttributes;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Egroups;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeDogma;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeDogma.EAttributes;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;

public class Sde2Loader {

	public static TypeHierarchy load() {
		Map<Integer, EdogmaAttributes> attTypes = EdogmaAttributes.LOADER.yaml().load();
		TypeHierarchy ret = new TypeHierarchy();
		// categories
		for (Entry<Integer, Ecategories> e : Ecategories.LOADER.yaml().load().entrySet()) {
			CatDetails det = new CatDetails();
			det.id = e.getKey();
			det.name = e.getValue().enName();
			det.published = e.getValue().published;
			ret.catID2Details.put(e.getKey(), det);
			ret.catID2GroupIDs.put(det.id, new HashSet<>());
		}
		// groups
		for (Entry<Integer, Egroups> e : Egroups.LOADER.yaml().load().entrySet()) {
			GroupDetails det = new GroupDetails();
			det.id = e.getKey();
			det.catID = e.getValue().categoryID;
			det.name = e.getValue().enName();
			det.published = e.getValue().published;
			ret.groupID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.put(det.id, new HashSet<>());
			ret.catID2GroupIDs.computeIfAbsent(e.getValue().categoryID, _ -> new HashSet<>()).add(e.getKey());
		}
		// types
		for (Entry<Integer, Etypes> e : Etypes.LOADER.yaml().load().entrySet()) {
			TypeDetails det = new TypeDetails();
			var sourceType = e.getValue();
			det.name = sourceType.enName();
			det.id = e.getKey();
			det.basePrice = sourceType.basePrice;
			det.marketGroupID = e.getValue().marketGroupID;
			det.groupID = e.getValue().groupID;
			det.mass = sourceType.mass;
			// no packaged volume in sde ?
			// det.packagedVolume = BigDecimal.ZERO;
			det.portionSize = sourceType.portionSize;
			det.published = sourceType.published;
			det.radius = sourceType.radius;
			det.volume = sourceType.volume;
			ret.typeID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.computeIfAbsent(sourceType.groupID, _ -> new HashSet<>()).add(e.getKey());
		}

		// Attributes

		HashSet<Integer> floatAttributeIds = new HashSet<>();
		HashSet<Integer> allAttributesIds = new HashSet<>();
		for (Entry<Integer, EtypeDogma> e : EtypeDogma.LOADER.yaml().load().entrySet()) {
			int typeID = e.getKey();
			TypeDetails td = ret.typeID2Details.get(typeID);
			if (td == null) {
				td = new TypeDetails();
				td.name = "unknown_" + typeID;
				ret.typeID2Details.put(typeID, td);
			}
			EtypeDogma etd = e.getValue();
			if (etd.dogmaAttributes != null) {
				for (EAttributes tatt : etd.dogmaAttributes) {
					int attId = tatt.attributeID;
					td.definition.put(attId, tatt.value);
					// add the attribute to the list of those with a float value
					if ((int) tatt.value.doubleValue() != tatt.value.doubleValue()) {
						floatAttributeIds.add(attId);
					}
					allAttributesIds.add(attId);
				}
			}
		}
		for (int attId : allAttributesIds) {
			EdogmaAttributes eattr = attTypes.get(attId);
			AttributeDetails det = new AttributeDetails();
			det.defaultValue = eattr.defaultValue;
			det.description = eattr.description;
			det.hasFloat = floatAttributeIds.contains(attId);
			det.highIsGood = eattr.highIsGood;
			det.id = attId;
			det.name = eattr.name;
			det.published = eattr.published;
			det.stackable = eattr.stackable;
			ret.attID2Details.put(attId, det);
		}
		return ret;
	}

}
