package fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.category.CategoryReference;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.group.GroupReference;
import lombok.Getter;

public class EindustryAssemblyLines {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<EindustryAssemblyLines> loader = new KeyValTimeLoader<>(
			EindustryAssemblyLines.class, "staticdata/industry_assembly_lines.static");

	public static class CategoryDetail extends CategoryReference {

		public BigDecimal cost_multiplier;
		public BigDecimal material_multiplier;
		public BigDecimal time_multiplier;

	}

	public static class GroupDetail extends GroupReference {

		public BigDecimal cost_multiplier;
		public BigDecimal material_multiplier;
		public BigDecimal time_multiplier;

	}

	public static class TypeListDetail {

		public BigDecimal cost_multiplier;
		public BigDecimal material_multiplier;
		public BigDecimal time_multiplier;
		public int type_list_id;

	}

	public int activity;
	public BigDecimal base_cost_multiplier;
	public BigDecimal base_material_multiplier;
	public BigDecimal base_time_multiplier;
	public String description;
	public List<CategoryDetail> details_per_category = new ArrayList<>();
	public List<GroupDetail> details_per_group = new ArrayList<>();
	public List<TypeListDetail> details_per_type_list = new ArrayList<>();
	public int id;
	public String name;

}
