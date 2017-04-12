package fr.guiguilechat.eveonline.database.yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Hull {

	public String name;
	public String group;

	public HullAttributes attributes = new HullAttributes();

	public ArrayList<String> effects = new ArrayList<>();

	public LinkedHashMap<String, Object> traits = new LinkedHashMap<>();

}
