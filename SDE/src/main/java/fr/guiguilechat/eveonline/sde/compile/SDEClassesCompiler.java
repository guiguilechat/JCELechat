package fr.guiguilechat.eveonline.sde.compile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.sun.codemodel.JCodeModel;

import fr.guiguilechat.eveonline.sde.bsd.EdgmAttributeTypes;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.sde.cache.SDECache;
import fr.guiguilechat.eveonline.sde.fsd.EcategoryIDs;
import fr.guiguilechat.eveonline.sde.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.sde.fsd.EtypeIDs;

@SuppressWarnings("unused")

/** tests of compilation of meta data to classes ? */
public class SDEClassesCompiler {

	protected SDECache sde;

	public SDEClassesCompiler() {
		this(new SDECache());
	}

	public SDEClassesCompiler(SDECache sdeCache) {
		sde = sdeCache;
	}

	public JCodeModel makeModel() {

		JCodeModel cm = new JCodeModel();
		LinkedHashMap<Integer, EcategoryIDs> catids = EcategoryIDs.load();
		LinkedHashMap<Integer, EgroupIDs> groupids = EgroupIDs.load();
		LinkedHashMap<Integer, EtypeIDs> typeids = EtypeIDs.load();
		ArrayList<EdgmTypeAttributes> attributes = EdgmTypeAttributes.load();
		LinkedHashMap<Integer, EdgmAttributeTypes> atttypes = EdgmAttributeTypes.loadByAttributeID();

		// for each group, list all the attributes

		HashMap<Integer, HashSet<Integer>> groupAttributes = new HashMap<>();
		for (EdgmTypeAttributes attribute : attributes) {
			int attId = attribute.attributeID;
			int typeID = attribute.typeID;
			int groupID = typeids.get(typeID).groupID;
			HashSet<Integer> groupAttribute = groupAttributes.get(groupID);
			if (groupAttribute == null) {
				groupAttribute = new HashSet<>();
				groupAttributes.put(groupID, groupAttribute);
			}
			groupAttribute.add(attId);
		}

		// for (Entry<Integer, HashSet<Integer>> e : groupAttributes.entrySet()) {
		// System.err.println(groupids.get(e.getKey()).enName() + " : "
		// + e.getValue().stream().map(i ->
		// atttypes.get(i).attributeName).collect(Collectors.toList()));
		// }

		// then for each cat we find the atributes that are present in every group

		HashMap<Integer, HashSet<Integer>> catAttributes = new HashMap<>();
		for (Entry<Integer, HashSet<Integer>> e : groupAttributes.entrySet()) {
			int catID = groupids.get(e.getKey()).categoryID;
			if (catAttributes.containsKey(catID)) {
				catAttributes.get(catID).retainAll(e.getValue());
			} else {
				catAttributes.put(catID, new HashSet<>(e.getValue()));
			}
		}

		// System.err.println();
		//
		// for (Entry<Integer, HashSet<Integer>> e : catAttributes.entrySet()) {
		// System.err.println(catids.get(e.getKey()).enName() + " : "
		// + e.getValue().stream().map(i ->
		// atttypes.get(i).attributeName).collect(Collectors.toList()));
		// }
		return cm;

	}

	public static String formatName(String name) {
		if (name.charAt(0) <= 'Z' && name.charAt(0) >= 'A' && !name.contains(" ")) {
			return name;
		}
		char[] newName = new char[name.length()];
		int skipped = 0;
		for (int i = 0; i < name.length(); i++) {
			char in = name.charAt(i);
			if (in == ' ') {
				skipped++;
				continue;
			} else {
				if (i - skipped == 0 || name.charAt(i - 1) == ' ') {
					newName[i - skipped] = Character.toUpperCase(in);
				} else {
					newName[i - skipped] = in;
				}
			}
		}
		return new String(newName, 0, name.length() - skipped);
	}

	public static void main(String[] args) {
		new SDEClassesCompiler().makeModel();
	}

}
