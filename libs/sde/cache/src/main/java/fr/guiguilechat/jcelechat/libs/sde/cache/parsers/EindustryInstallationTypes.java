package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EindustryInstallationTypes {

	//
	// SDE loading
	//

	public static final MapIntLoader<EindustryInstallationTypes> LOADER = new MapIntLoader<>(
			"industryInstallationTypes",
			EindustryInstallationTypes.class);

	//
	// file structure
	//

	public static class AssemblyLine {

		public int assemblyLineID;

	}

	public List<AssemblyLine> assemblyLines = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : assemblyLines.size=" + first.assemblyLines.size());
	}

}
