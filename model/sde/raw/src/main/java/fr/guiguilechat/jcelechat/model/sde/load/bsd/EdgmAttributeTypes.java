package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EdogmaAttributes;

/**
 * @deprecated since ccp remove it. It's now in FSD, use
 *             {@link EdogmaAttributes} instead
 * @author
 *
 */
@Deprecated
public class EdgmAttributeTypes {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/bsd/dgmAttributeTypes.yaml");

	private static List<EdgmAttributeTypes> cache;

	@SuppressWarnings("unchecked")
	public static synchronized List<EdgmAttributeTypes> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EdgmAttributeTypes.class);
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = Collections
						.unmodifiableList((List<EdgmAttributeTypes>) yaml.loadAs(new FileReader(FILE), ArrayList.class));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	private static Map<Integer, EdgmAttributeTypes> cacheById = null;

	public static Map<Integer, EdgmAttributeTypes> loadByAttributeID() {
		if (cacheById == null) {
			List<EdgmAttributeTypes> loaded = load();
			synchronized (loaded) {
				if (cacheById == null) {
					LinkedHashMap<Integer, EdgmAttributeTypes> ret = new LinkedHashMap<>();
					for (EdgmAttributeTypes e : load()) {
						ret.put(e.attributeID, e);
					}
					cacheById = Collections.unmodifiableMap(ret);
				}
			}
		}
		return cacheById;
	}

	public int attributeID;
	public String attributeName;
	public int categoryID;
	public float defaultValue;
	public String description;
	public boolean highIsGood;
	public boolean published;
	public boolean stackable;
	public int iconID;
	public int unitID;
	public String displayName;

}
