package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/**
 *
 */
public class EdogmaAttributes {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/dogmaAttributes.yaml");

	private static Map<Integer, EdogmaAttributes> cache;

	@SuppressWarnings("unchecked")
	public static synchronized Map<Integer, EdogmaAttributes> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "attributeID".equals(s)).findAny().isPresent()) {
								node.setType(EdogmaAttributes.class);
							}
						}
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = Collections.unmodifiableMap(yaml.loadAs(new FileReader(FILE), LinkedHashMap.class));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	public int attributeID;
	public int categoryID;
	public int chargeRechargeTimeID;
	public int dataType;
	public float defaultValue;
	public String description;
	public Object displayNameID;
	public boolean highIsGood;
	public int iconID;
	public int maxAttributeID;
	public String name;
	public boolean published;
	public boolean stackable;
	public Object tooltipDescriptionID;
	public Object tooltipTitleID;
	public int unitID;

	public static void main(String[] args) {
		load();
	}

}
