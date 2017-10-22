package fr.guiguilechat.eveonline.model.database.yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Hull extends Type {


	public HullAttributes attributes = new HullAttributes();

	public ArrayList<String> effects = new ArrayList<>();

	public LinkedHashMap<String, Object> traits = new LinkedHashMap<>();

}
