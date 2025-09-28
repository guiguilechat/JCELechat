package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EmapSolarSystems extends InSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapSolarSystems";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapSolarSystems> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapSolarSystems> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmapSolarSystems.class, Set.of("constellationID"));

	public static final JacksonYamlLHMLoader<EmapSolarSystems> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public boolean border;
	public int constellationID;
	public boolean corridor;
	public List<Integer> disallowedAnchorCategories = new ArrayList<>();
	public List<Integer> disallowedAnchorGroups = new ArrayList<>();
	public int factionID;
	public boolean fringe;
	public boolean hub;
	public boolean international;
	public BigDecimal luminosity;
	public List<Integer> planetIDs = new ArrayList<>();
	public BigDecimal radius;
	public int regionID;
	public boolean regional;
	public String securityClass;
	public BigDecimal securityStatus;
	public int starID;
	public List<Integer> stargateIDs = new ArrayList<>();
	public String visualEffect;
	public int wormholeClassID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : regionID=" + first.regionID + " name=" + first.enName() + " position=" + first.position);
	}
}
