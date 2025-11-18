package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

/**
 *
 */
public class EdogmaAttributes {

	//
	// SDE loading
	//

	public static final IntMapLoader<EdogmaAttributes> LOADER = new IntMapLoader<>(
			"dogmaAttributes",
			EdogmaAttributes.class,
			Set.of("attributeCategoryID"),
			Set.of("published"));

	//
	// file structure
	//

	/** {@link EdogmaAttributeCategories} */
	public int attributeCategoryID;
	/** {@link EdogmaAttributes} */
	public int chargeRechargeTimeID; // should be chargeRechargeTimeAttributeID
	public int dataType;
	public BigDecimal defaultValue;
	public String description;
	public Map<String, String> displayName = new LinkedHashMap<>();
	public boolean displayWhenZero;
	public boolean highIsGood;
	public int iconID;
	/** {@link EdogmaAttributes} */
	public int maxAttributeID;
	/** {@link EdogmaAttributes} */
	public int minAttributeID;
	public String name;
	public boolean published;
	public boolean stackable;
	public Map<String, String> tooltipDescription = new LinkedHashMap<>();
	public Map<String, String> tooltipTitle = new LinkedHashMap<>();
	/** {@link EdogmaUnits} */
	public Integer unitID;

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : name=" + first.name + " published=" + first.published);
	}

}
