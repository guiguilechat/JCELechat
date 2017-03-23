package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.util.HashSet;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EcategoryIDs;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EtypeIDs;

public class ShowDecryptorData {

	public static void main(String[] args) {
		
		

		int categoryid = EcategoryIDs.load().entrySet().stream()
				.filter(e -> e.getValue().name.get("en").equals("Decryptors"))
				.mapToInt(Entry::getKey).findFirst().getAsInt();
		HashSet<Integer> groups = new HashSet<>();
		for (Entry<Integer, EgroupIDs> e : EgroupIDs.load().entrySet()) {
			if (e.getValue().categoryID == categoryid && e.getValue().published) {
				System.err.println("" + e.getKey() + " : " + e.getValue().name.get("en"));
				groups.add(e.getKey());
			}
		}
		System.err.println("items :");
		for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
			if (groups.contains(e.getValue().groupID) && e.getValue().published) {
				System.err.println(e.getKey() + " : " + e.getValue().name.get("en") + " ; " + e.getValue().groupID);
			}
		}
	}

}
