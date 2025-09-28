package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class Etypes {

	private static final Logger logger = LoggerFactory.getLogger(Etypes.class);

	//
	// SDE loading
	//

	public static final String SDE_FILE = "types";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Etypes> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Etypes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Etypes.class, Set.of("groupID"));

	public static final JacksonYamlLHMLoader<Etypes> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public BigDecimal basePrice;
	public BigDecimal capacity;
	/** key is language short, like "en" */
	public HashMap<String, String> description = new HashMap<>();
	public int factionID;
	public int graphicID;
	public int groupID;
	public int iconID;
	public int marketGroupID;
	public BigDecimal mass;
	public int metaGroupID;
	public HashMap<String, String> name = new HashMap<>();
	public int portionSize;
	public boolean published;
	public int raceID;
	public BigDecimal radius;
	public int soundID;
	public Integer variationParentTypeID;
	public BigDecimal volume;


	public String enName() {
		return name == null ? null : name.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	//

	private static final HashMap<Integer, String> ERROR_NAMES = new HashMap<>();

	public static String getName(int typeId) {
		var type = LOADER.load().get(typeId);
		if (type != null) {
			return type.enName();
		}
		synchronized (ERROR_NAMES) {
			return ERROR_NAMES.computeIfAbsent(typeId, i -> {
				logger.warn("can't load type id " + i, new Exception());
				return "missingType_" + i;
			});
		}
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " description=" + first.enDescription());
	}

}
