package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
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
		public float value;

	}

	public static class Eeffects{

		public int effectID;
		public boolean isDefault;

	}

	public EAttributes[] dogmaAttributes;

	public Eeffects[] dogmaEffects;

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/typeDogma.yaml");

	private static Map<Integer, EtypeDogma> cache;

	@SuppressWarnings("unchecked")
	public static synchronized Map<Integer, EtypeDogma> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "dogmaAttributes".equals(s)).findAny().isPresent()) {
								node.setType(EtypeDogma.class);
							}
						}
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = SDECache.yaml(cons);
			try {
				cache = Collections
						.unmodifiableMap((Map<Integer, EtypeDogma>) yaml.loadAs(new FileReader(FILE), LinkedHashMap.class));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	public static void main(String[] args) {
		load();
	}

}
