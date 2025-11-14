package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EdogmaEffects {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dogmaEffects";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EdogmaEffects> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EdogmaEffects> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EdogmaEffects.class, Set.of("effectCategoryID"));

	public static final JacksonYamlLHMLoader<EdogmaEffects> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public boolean disallowAutoRepeat;
	/** {@link EdogmaAttributes} */
	public int dischargeAttributeID;
	public Map<String, String> displayName = new LinkedHashMap<>();
	public int distribution;
	/** {@link EdogmaAttributes} */
	public int durationAttributeID;
	public int effectCategoryID;
	public boolean electronicChance;
	/** {@link EdogmaAttributes} */
	public int falloffAttributeID;
	/** {@link EdogmaAttributes} */
	public int fittingUsageChanceAttributeID;
	public String guid;
	public int iconID;
	public boolean isAssistance;
	public boolean isOffensive;
	public boolean isWarpSafe;

	public static class ModifierInfo {
		public String domain;
		public int effectID;
		public String func;
		public int groupID;
		/** {@link EdogmaAttributes} */
		public int modifiedAttributeID;
		/** {@link EdogmaAttributes} */
		public int modifyingAttributeID;
		public int operation;
		/** {@link Etypes} */
		public int skillTypeID;
	}
	public List<ModifierInfo> modifierInfo = new ArrayList<>();

	public String name;
	/** {@link EdogmaAttributes} */
	public int npcActivationChanceAttributeID;
	/** {@link EdogmaAttributes} */
	public int npcUsageChanceAttributeID;
	public boolean propulsionChance;
	public boolean published;
	/** {@link EdogmaAttributes} */
	public int rangeAttributeID;
	public boolean rangeChance;
	/** {@link EdogmaAttributes} */
	public int resistanceAttributeID;
	/** {@link EdogmaAttributes} */
	public int trackingSpeedAttributeID;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}
}
