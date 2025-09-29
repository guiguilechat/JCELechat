package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

import java.util.LinkedHashMap;

public abstract class OrbitingCelestial extends Orbiting {

	public static class Attributes {
		public int heightMap1;
		public int heightMap2;
		public boolean population = false;
		public int shaderPreset;
	}
	public Attributes attributes;

	public LinkedHashMap<String, String> uniqueName;

	public String enUniqueName() {
		return uniqueName == null ? null : uniqueName.get("en");
	}

}
