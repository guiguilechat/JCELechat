package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EmapStars {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapStars";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapStars> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapStars> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmapStars.class, Set.of("solarSystemID"));

	public static final JacksonYamlLHMLoader<EmapStars> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public long radius;
	public int solarSystemID;

	public static class StarStatistics {
		public BigDecimal age;
		public BigDecimal life;
		public BigDecimal luminosity;
		public String spectralClass;
		public BigDecimal temperature;

	}

	public StarStatistics statistics;
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemId=" + first.solarSystemID + " typeID=" + first.typeID);
	}
}
