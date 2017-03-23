package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.retrieval.sde.bsd.EdgmAttributeTypes;
import fr.guiguilechat.eveonline.database.retrieval.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EtypeIDs;

public class ShowDecryptorData {

	public static class Decryptor {
		int maxrun = 0;
		double probmult = 1.0;
		int id = 0;
		int me = 0;
		int te = 0;
		String name;

		@Override
		public String toString() {
			return name + " id" + id + " me" + me + " te" + te + " mult" + probmult + " run" + maxrun;
		}
	}

	public static void main(String[] args) {
		for (Decryptor dec : getDecryptors(EdgmTypeAttributes.loadByTypeIDAttributeID(),
				EdgmAttributeTypes.loadByAttributeID())) {
			System.err.println(dec);
		}
	}

	public static Decryptor[] getDecryptors(HashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> typeAttributes,
			HashMap<Integer, EdgmAttributeTypes> attributes) {
		HashSet<Integer> groups = new HashSet<>();
		groups.add(1304);

		ArrayList<Decryptor> decryptors = new ArrayList<>();

		int maxRunAttID = 0, probMultAttID = 0, MEAttID = 0, TEAttID = 0;

		for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
			if (groups.contains(e.getValue().groupID) && e.getValue().published) {
				int itemId = e.getKey();
				Decryptor dec = new Decryptor();
				decryptors.add(dec);
				dec.id = itemId;
				dec.name = e.getValue().name.get("en");
				HashMap<Integer, EdgmTypeAttributes> itemAttributes = typeAttributes.get(itemId);
				if (itemAttributes != null)
					for (Entry<Integer, EdgmTypeAttributes> e2 : itemAttributes.entrySet()) {
						int attId = e2.getKey();
						EdgmTypeAttributes att = e2.getValue();
						if (attId != maxRunAttID && attId != probMultAttID && attId != MEAttID && attId != TEAttID) {
							String attName = attributes.get(attId).attributeName;
							if (attName.contains("MaxRun")) {
								maxRunAttID = attId;
							} else if (attName.contains("Multiplier")) {
								probMultAttID = attId;
							} else if (attName.contains("MEModifier")) {
								MEAttID = attId;
							} else if (attName.contains("TEModifier")) {
								TEAttID = attId;
							} else {
								System.err.println(
										"can't translate attribute id " + attId + " with name " + attName + " in a decryptor attribute");
							}
						}
						if (attId == maxRunAttID) {
							dec.maxrun = (int) att.valueFloat;
						} else if (attId == probMultAttID) {
							dec.probmult = (int) att.valueFloat;
						} else if (attId == MEAttID) {
							dec.me = (int) att.valueFloat;
						} else if (attId == TEAttID) {
							dec.te = (int) att.valueFloat;
						} else {
							System.err.println("unknown attribute " + attId);
						}
					}
			}
		}
		return decryptors.toArray(new Decryptor[] {});
	}

}
