package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.sde.bsd.EdgmAttributeTypes;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.sde.fsd.EcategoryIDs;
import fr.guiguilechat.eveonline.sde.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.sde.fsd.EtypeIDs;

public class LoadClassesTest {

	public static void main(String[] args) {
		LinkedHashMap<Integer, EcategoryIDs> catids = EcategoryIDs.load();
		LinkedHashMap<Integer, EgroupIDs> groupids = EgroupIDs.load();
		LinkedHashMap<Integer, EtypeIDs> typeids = EtypeIDs.load();
		LinkedHashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> attributes = EdgmTypeAttributes
				.loadByTypeIDAttributeID();
		LinkedHashMap<Integer, EdgmAttributeTypes> atttypes = EdgmAttributeTypes.loadByAttributeID();
	}

}
