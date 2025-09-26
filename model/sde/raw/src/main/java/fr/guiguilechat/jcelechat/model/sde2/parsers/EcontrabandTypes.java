package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EcontrabandTypes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "contrabandTypes";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EcontrabandTypes> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EcontrabandTypes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EcontrabandTypes.class, Set.of("factions"));

	public static final JacksonYamlLHMLoader<EcontrabandTypes> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class FactionContraband {
		public BigDecimal attackMinSec;
		public BigDecimal confiscateMinSec;
		public BigDecimal fineByValue;
		public BigDecimal standingLoss;
	}

	public Map<Integer, FactionContraband> factions = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : factions=" + first.factions.size());
	}
}
