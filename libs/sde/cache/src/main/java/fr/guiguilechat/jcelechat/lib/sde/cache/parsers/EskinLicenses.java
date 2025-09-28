package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EskinLicenses {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "skinLicenses";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EskinLicenses> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EskinLicenses> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EskinLicenses.class, Set.of("duration"));

	public static final JacksonYamlLHMLoader<EskinLicenses> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int duration;
	public boolean isSingleUse;
	public int licenseTypeID;
	public int skinID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : skinID=" + first.skinID);
	}
}
