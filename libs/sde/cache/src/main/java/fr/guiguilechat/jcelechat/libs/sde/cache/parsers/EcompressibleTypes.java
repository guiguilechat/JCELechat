package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

/**
 * key is {@link Etypes} . Entries are read : (key) compresses into
 * {@link #compressedTypeID}
 */
public class EcompressibleTypes {

	//
	// SDE loading
	//

	public static final MapIntLoader<EcompressibleTypes> LOADER = new MapIntLoader<>(
			"compressibleTypes",
			EcompressibleTypes.class);

	//
	// file structure
	//

	/** {@link Etypes} What this is compressed into */
	public int compressedTypeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : compressedTypeID=" + first.compressedTypeID);
	}

}
