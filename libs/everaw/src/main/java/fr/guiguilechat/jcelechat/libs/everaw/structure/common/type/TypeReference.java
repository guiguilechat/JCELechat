package fr.guiguilechat.jcelechat.libs.everaw.structure.common.type;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeReference {

	@JsonProperty("typeID")
	@JsonAlias("type_id")
	public int typeId;

}
