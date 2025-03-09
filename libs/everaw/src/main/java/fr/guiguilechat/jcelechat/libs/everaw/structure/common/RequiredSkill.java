package fr.guiguilechat.jcelechat.libs.everaw.structure.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequiredSkill {

	public int level;

	@JsonProperty("typeID")
	public int typeId;

}
