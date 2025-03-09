package fr.guiguilechat.jcelechat.libs.everaw.structure.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsumedMaterial {

	public int quantity;

	@JsonProperty("typeID")
	public int typeId;

}
