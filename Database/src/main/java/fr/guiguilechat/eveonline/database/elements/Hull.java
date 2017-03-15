package fr.guiguilechat.eveonline.database.elements;

import java.util.LinkedHashMap;

public class Hull {

	public String name;

	public HullAttributes attributes = new HullAttributes();

	public LinkedHashMap<String, Object> traits = new LinkedHashMap<>();

}
