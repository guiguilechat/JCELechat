package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

/**
 *
 */
public class EdogmaAttributes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dogmaAttributes";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EdogmaAttributes> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EdogmaAttributes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EdogmaAttributes.class, Set.of("attributeCategoryID"), Set.of("published"));

	public static final JacksonYamlLHMLoader<EdogmaAttributes> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int attributeCategoryID;
	public int chargeRechargeTimeID; // should be chargeRechargeTimeAttributeID
	public int dataType;
	public BigDecimal defaultValue;
	public String description;
	public Map<String, String> displayName = new LinkedHashMap<>();
	public boolean displayWhenZero;
	public boolean highIsGood;
	public int iconID;
	public int maxAttributeID;
	public int minAttributeID;
	public String name;
	public boolean published;
	public boolean stackable;
	public Map<String, String> tooltipDescription = new LinkedHashMap<>();
	public Map<String, String> tooltipTitle = new LinkedHashMap<>();
	public Integer unitID;

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : name=" + first.name + " published=" + first.published);
	}

}
