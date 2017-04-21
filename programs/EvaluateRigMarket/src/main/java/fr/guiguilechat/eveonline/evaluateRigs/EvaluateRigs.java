package fr.guiguilechat.eveonline.evaluateRigs;

import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.MetaInf;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class EvaluateRigs {

	public static void main(String[] args) {
		DataBase db = new YamlDatabase();
		for (Entry<String, Module> e : db.getModules().entrySet()) {
			Module m = e.getValue();
			if (m.isRig() && m.techLevel() == 1) {
				MetaInf mi = db.getMetaInfs().get(m.name);
				if (mi != null) {
					for (String bpName : mi.productOf) {
						System.err.println("\t" + bpName);
						Blueprint bp = db.getBlueprints().get(bpName);
					}
				}
				else {
					System.err.println("no meta info for " + m.name);
					System.err.println("existing meta inf for " + db.getMetaInfs().keySet());
					System.exit(0);
				}
			}
		}
	}

}
