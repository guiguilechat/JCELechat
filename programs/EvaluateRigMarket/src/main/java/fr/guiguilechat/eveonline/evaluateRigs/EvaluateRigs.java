package fr.guiguilechat.eveonline.evaluateRigs;

import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class EvaluateRigs {

	public static void main(String[] args) {
		DataBase db = new YamlDatabase();
		for (Entry<String, Module> e : db.getModules().entrySet()) {
			Module m = e.getValue();
			int id = m.id;
			if (m.isRig() && m.techLevel() == 1) {
				System.err.println("" + id + "\t" + m.name);
				// db.
			}
		}
	}

}
