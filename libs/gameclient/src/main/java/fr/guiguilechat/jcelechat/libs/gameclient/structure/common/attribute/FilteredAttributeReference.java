package fr.guiguilechat.jcelechat.libs.gameclient.structure.common.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilteredAttributeReference extends AttributeReference {

	@JsonProperty("filterID")
	public Integer filterId;

}