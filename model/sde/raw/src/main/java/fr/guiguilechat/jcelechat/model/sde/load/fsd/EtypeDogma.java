package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EtypeDogma {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EtypeDogma.class);


	public static class EAttributes{

		public int attributeID;
		public Number value;

	}

	public static class Eeffects{

		public int effectID;
		public boolean isDefault;

	}

	public EAttributes[] dogmaAttributes;

	public Eeffects[] dogmaEffects;

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "fsd/typeDogma.yaml");

	private static Map<Integer, EtypeDogma> cache;

	public static synchronized Map<Integer, EtypeDogma> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			try {
				cache = from(new FileInputStream(FILE));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, EtypeDogma> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter("dogmaAttributes"::equals).findAny().isPresent()) {
							node.setType(EtypeDogma.class);
						}
					}
				}
				Construct ret = super.getConstructor(node);
				return ret;
			}
		};
		Yaml yaml = SDECache.yaml(cons);
		return yaml.loadAs(is, LinkedHashMap.class);
	}

	public static void main(String[] args) {
		System.out.println("loaded " + load().size() + " types data");
	}

}
