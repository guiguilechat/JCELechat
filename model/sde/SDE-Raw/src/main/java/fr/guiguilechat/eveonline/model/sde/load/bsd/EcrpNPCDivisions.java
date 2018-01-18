package fr.guiguilechat.eveonline.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.eveonline.model.sde.load.SDECache;

public class EcrpNPCDivisions {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/crpNPCDivisions.yaml");
	private static ArrayList<EcrpNPCDivisions> cache;

	@SuppressWarnings("unchecked")
	public static ArrayList<EcrpNPCDivisions> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EcrpNPCDivisions.class);
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = yaml.loadAs(new FileReader(FILE), ArrayList.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	public static HashMap<Integer, String> loadById() {
		HashMap<Integer, String> ret = new HashMap<>();
		for (EcrpNPCDivisions e : load()) {
			ret.put(e.divisionID, e.divisionName);
		}
		return ret;
	}

	public String description;
	public int divisionID;
	public String divisionName;
	public String leaderType;

}
