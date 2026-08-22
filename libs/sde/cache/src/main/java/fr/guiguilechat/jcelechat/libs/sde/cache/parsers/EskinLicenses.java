package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EskinLicenses {

	//
	// SDE loading
	//

	public static final MapIntLoader<EskinLicenses> LOADER = new MapIntLoader<>(
			"skinLicenses",
			EskinLicenses.class);

	//
	// file structure
	//

	public int duration;
	public boolean isSingleUse;
	public int licenseTypeID;
	public int skinID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : skinID=" + first.skinID);
	}
}
