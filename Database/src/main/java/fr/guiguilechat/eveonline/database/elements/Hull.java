package fr.guiguilechat.eveonline.database.elements;

import java.util.LinkedHashMap;

public class Hull {

	public String name;

	public Fitting fitting = new Fitting();

	public static class Attributes {

		public int velocity;
		public float warpSpeed;
		public float inertiaModifier;

		public int targetRange;
		public int scanRes;
		public int scanStr;
		public String scanType;
		public int maxTargets;

	}

	public Attributes attributes = new Attributes();

	public LinkedHashMap<String, Object> traits = new LinkedHashMap<>();

}
