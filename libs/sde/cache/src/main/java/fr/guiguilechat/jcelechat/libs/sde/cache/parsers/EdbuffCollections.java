package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EdbuffCollections {

	//
	// SDE loading
	//

	public static final IntMapLoader<EdbuffCollections> LOADER = new IntMapLoader<>(
			"dbuffCollections",
			EdbuffCollections.class,
			Set.of("operationName"));

	//
	// file structure
	//

	public String aggregateMode;
	public String developerDescription;
	public Map<String, String> displayName = new LinkedHashMap<>();

	public static class ItemModifier {
		/** {@link EdogmaAttributes} */
		public int dogmaAttributeID;
	}
	public List<ItemModifier> itemModifiers = new ArrayList<>();

	public static class LocationGroupModifiers {
		/** {@link EdogmaAttributes} */
		public int dogmaAttributeID;
		/** {@link Egroups} */
		public int groupID;
	}
	public ArrayList<LocationGroupModifiers> locationGroupModifiers = new ArrayList<>();

	public List<ItemModifier> locationModifiers = new ArrayList<>();

	public static class LocationRequiredSkillModifier {
		/** {@link EdogmaAttributes} */
		public int dogmaAttributeID;
		/** {@link Etypes} */
		public int skillID;
	}
	public List<LocationRequiredSkillModifier> locationRequiredSkillModifiers = new ArrayList<>();

	public String operationName;
	public String showOutputValueInUI;

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : itemodifiers=" + first.itemModifiers.size() + " locationgroupmodifiers="
				+ first.locationGroupModifiers.size());
	}
}
