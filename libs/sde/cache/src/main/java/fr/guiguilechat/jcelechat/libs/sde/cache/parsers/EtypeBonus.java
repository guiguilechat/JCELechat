package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EtypeBonus {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "typeBonus";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EtypeBonus> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EtypeBonus> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EtypeBonus.class, Set.of("miscBonuses"), Set.of("roleBonuses"), Set.of("types"));

	public static final JacksonYamlLHMLoader<EtypeBonus> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int iconID;

	public static class Bonus {
		public BigDecimal bonus;
		public LinkedHashMap<String, String> bonusText = new LinkedHashMap<>();
		public int importance;
		public int unitID;
	}

	public static class MiscBonus extends Bonus {
		public boolean isPositive;
	}

	public List<MiscBonus> miscBonuses = new ArrayList<>();
	public List<Bonus> roleBonuses = new ArrayList<>();
	/** key is {@link Etypes} */
	public Map<Integer, Bonus[]> types = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		var firstType = first.types.isEmpty() ? new Bonus[] {} : first.types.values().iterator().next();
		Bonus b = firstType.length == 0 ? null : firstType[1];
		System.out.println(
				"first : rolebonuses=" + first.roleBonuses.size() + " types=" + first.types.size() + " bonus[0].bonus="
						+ (b == null ? "ø" : b.bonus));
	}
}
