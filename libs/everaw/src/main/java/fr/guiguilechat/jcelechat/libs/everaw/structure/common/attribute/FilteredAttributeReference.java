package fr.guiguilechat.jcelechat.libs.everaw.structure.common.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilteredAttributeReference extends AttributeReference {

	@JsonProperty("filterID")
	public int filterId;

}