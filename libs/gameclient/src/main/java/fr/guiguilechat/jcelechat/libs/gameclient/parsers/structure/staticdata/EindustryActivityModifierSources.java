package fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.attribute.FilteredAttributeReference;
import lombok.Getter;

public class EindustryActivityModifierSources {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<EindustryActivityModifierSources> loader = new KeyValTimeLoader<>(
			EindustryActivityModifierSources.class, "staticdata/industry_activity_modifier_sources.static");

	public static class IndustryActivityModifier {

		public List<FilteredAttributeReference> cost = new ArrayList<>();
		public List<FilteredAttributeReference> material = new ArrayList<>();
		public List<FilteredAttributeReference> time = new ArrayList<>();

	}

	public IndustryActivityModifier copying;
	public IndustryActivityModifier invention;
	public IndustryActivityModifier manufacturing;
	public IndustryActivityModifier reaction;
	@JsonProperty("research_material")
	public IndustryActivityModifier researchMaterial;
	@JsonProperty("research_time")
	public IndustryActivityModifier researchTime;

}
