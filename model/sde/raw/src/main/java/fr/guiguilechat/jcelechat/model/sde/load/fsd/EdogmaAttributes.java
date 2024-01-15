package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.LoaderOptions;
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

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/fsd/dogmaAttributes.yaml");

	private static Map<Integer, EdogmaAttributes> cache;

	public static synchronized Map<Integer, EdogmaAttributes> load() {
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
	public static LinkedHashMap<Integer, EdogmaAttributes> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter("attributeID"::equals).findAny().isPresent()) {
							node.setType(EdogmaAttributes.class);
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

	public int attributeID;
	public int categoryID;
	public int chargeRechargeTimeID;
	public int dataType;
	public float defaultValue;
	public String description;
	public Map<String, String> displayNameID = new HashMap<>();
	public boolean displayWhenZero;
	public boolean highIsGood;
	public int iconID;
	public int maxAttributeID;
	public String name;
	public boolean published;
	public boolean stackable;
	public Map<String, String> tooltipDescriptionID = new HashMap<>();
	public Map<String, String> tooltipTitleID = new HashMap<>();
	public Integer unitID;

	public static void main(String[] args) {
		System.out.println("loaded " + load().size() + " attributes");
	}

}
