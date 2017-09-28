package fr.guiguilechat.eveonline.sde.bsd;

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

import fr.guiguilechat.eveonline.sde.cache.SDECache;

public class EcrpNPCDivisions {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/crpNPCDivisions.yaml");

	@SuppressWarnings("unchecked")
	public static ArrayList<EcrpNPCDivisions> load() {
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
			return yaml.loadAs(new FileReader(FILE), ArrayList.class);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
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
