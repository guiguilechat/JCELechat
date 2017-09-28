package fr.guiguilechat.eveonline.sde.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.eveonline.sde.cache.SDECache;

public class EagtAgents {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/agtAgents.yaml");

	@SuppressWarnings("unchecked")
	public static ArrayList<EagtAgents> load() {
		SDECache.INSTANCE.donwloadSDE();
		Constructor cons = new Constructor(ArrayList.class) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					node.setType(EagtAgents.class);
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

	public int agentID;
	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;
	public int quality;

}
