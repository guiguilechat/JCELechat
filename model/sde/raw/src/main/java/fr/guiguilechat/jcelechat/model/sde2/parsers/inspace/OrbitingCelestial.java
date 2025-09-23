package fr.guiguilechat.jcelechat.model.sde2.parsers.inspace;

public abstract class OrbitingCelestial extends Orbiting {

	public static class Attributes {
		public int heightMap1;
		public int heightMap2;
		public boolean population = false;
		public int shaderPreset;
	}

	public Attributes attributes;

}
