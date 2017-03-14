package fr.guiguilechat.eveonline.database.elements;

import java.util.LinkedHashMap;

public class Hull {

	public String name;

	public Fitting fitting = new Fitting();

	public static class Attributes {
		public int maximumVelocity;
	}

	public Attributes attributes = new Attributes();

	public LinkedHashMap<String, Object> traits = new LinkedHashMap<>();

}
