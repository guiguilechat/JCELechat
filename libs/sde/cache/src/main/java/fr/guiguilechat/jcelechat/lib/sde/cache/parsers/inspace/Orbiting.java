package fr.guiguilechat.jcelechat.lib.sde.cache.parsers.inspace;

import java.math.BigDecimal;

public abstract class Orbiting extends InSpace {

	public int celestialIndex;
	public int orbitID;
	public int orbitIndex;
	public BigDecimal radius;
	public int solarSystemID;

	public static class Statistic {
		public BigDecimal density;
		public BigDecimal eccentricity;
		public BigDecimal escapeVelocity;
		public boolean locked;
		public BigDecimal massDust;
		public BigDecimal massGas;
		public BigDecimal orbitPeriod;
		public BigDecimal orbitRadius;
		public BigDecimal pressure;
		public BigDecimal rotationRate;
		public String spectralClass;
		public BigDecimal surfaceGravity;
		public BigDecimal temperature;
	}
	public Statistic statistics;

	public int typeID;
}
