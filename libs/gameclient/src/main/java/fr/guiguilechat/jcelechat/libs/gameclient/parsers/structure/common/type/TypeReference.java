package fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.type;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeReference {

	@JsonProperty("typeID")
	@JsonAlias("type_id")
	public int typeId;

}
