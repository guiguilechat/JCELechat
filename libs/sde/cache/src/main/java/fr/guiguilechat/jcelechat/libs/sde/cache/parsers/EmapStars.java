package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EmapStars {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapStars> LOADER = new IntMapLoader<>(
			"mapStars",
			EmapStars.class);

	//
	// file structure
	//

	public long radius;
	/** {@link EmapSolarSystems} */
	public int solarSystemID;

	public static class StarStatistics {
		public BigDecimal age;
		public BigDecimal life;
		public BigDecimal luminosity;
		public String spectralClass;
		public BigDecimal temperature;
	}

	public StarStatistics statistics;
	/** {@link Etypes} */
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemId=" + first.solarSystemID + " typeID=" + first.typeID);
	}
}
