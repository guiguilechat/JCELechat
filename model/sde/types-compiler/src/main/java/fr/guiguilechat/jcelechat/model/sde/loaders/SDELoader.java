package fr.guiguilechat.jcelechat.model.sde.loaders;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.hierarchy.AttributeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Ecategories;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EdogmaAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Egroups;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeDogma;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeDogma.EAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;

public class SDELoader {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SDELoader.class);

	public static TypeHierarchy load() {
		Map<Integer, EdogmaAttributes> attTypes = EdogmaAttributes.LOADER.load();
		TypeHierarchy ret = new TypeHierarchy();
		// categories
		for (Entry<Integer, Ecategories> e : Ecategories.LOADER.load().entrySet()) {
			CatDetails det = new CatDetails();
			det.id = e.getKey();
			det.name = e.getValue().enName();
			det.published = e.getValue().published;
			ret.catID2Details.put(e.getKey(), det);
			ret.catID2GroupIDs.put(det.id, new HashSet<>());
		}
		// groups
		for (Entry<Integer, Egroups> e : Egroups.LOADER.load().entrySet()) {
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
		for (Entry<Integer, Etypes> e : Etypes.LOADER.load().entrySet()) {
			TypeDetails det = new TypeDetails();
			det.name = e.getValue().enName();
			det.id = e.getKey();
			det.basePrice = e.getValue().basePrice;
			det.marketGroupID = e.getValue().marketGroupID;
			det.groupID = e.getValue().groupID;
			det.mass = e.getValue().mass;
			// no packaged volume in sde ?
			det.portionSize = e.getValue().portionSize;
			det.published = e.getValue().published;
			det.volume = e.getValue().volume;
			ret.typeID2Details.put(e.getKey(), det);
			ret.groupID2TypeIDs.computeIfAbsent(e.getValue().groupID, _ -> new HashSet<>()).add(e.getKey());
		}

		// Attributes

		HashSet<Integer> floatAttributeIds = new HashSet<>();
		HashSet<Integer> allAttributesIds = new HashSet<>();
		for (Entry<Integer, EtypeDogma> e : EtypeDogma.LOADER.load().entrySet()) {
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
					float value = tatt.value.floatValue();
					td.definition.put(attId, value);
					// add the attribute to the list of those with a float value
					if ((int) value != value) {
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
