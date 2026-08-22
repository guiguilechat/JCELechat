package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EindustryAssemblyLines {

	//
	// SDE loading
	//

	public static final MapIntLoader<EindustryAssemblyLines> LOADER = new MapIntLoader<>(
			"industryAssemblyLines",
			EindustryAssemblyLines.class);

	//
	// file structure
	//

	public int activityID;

	public static class CategoryDetail {

		public int categoryID;
		public BigDecimal costMultiplier;
		public BigDecimal materialMultiplier;
		public BigDecimal timeMultiplier;

	}

	public static class GroupDetail {

		public BigDecimal costMultiplier;
		public int groupID;
		public BigDecimal materialMultiplier;
		public BigDecimal timeMultiplier;

	}

	public static class TypeListDetail {

		public BigDecimal costMultiplier;
		public BigDecimal materialMultiplier;
		public BigDecimal timeMultiplier;
		public int typeListID;

	}

	public BigDecimal baseCostMultiplier;
	public BigDecimal baseMaterialMultiplier;
	public BigDecimal baseTimeMultiplier;
	public String description;
	public List<CategoryDetail> detailsPerCategory = new ArrayList<>();
	public List<GroupDetail> detailsPerGroup = new ArrayList<>();
	public List<TypeListDetail> detailsPerTypeList = new ArrayList<>();
	public int id;
	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : description=" + first.description + " name=" + first.name);
	}

}
