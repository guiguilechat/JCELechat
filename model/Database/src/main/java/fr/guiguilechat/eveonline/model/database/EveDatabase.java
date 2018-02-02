package fr.guiguilechat.eveonline.model.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.eveonline.model.apiv2.Eve;
import fr.guiguilechat.eveonline.model.database.yaml.Hull;
import fr.guiguilechat.eveonline.model.database.yaml.Module;
import fr.guiguilechat.eveonline.model.database.yaml.Type;

public abstract class EveDatabase {

	public abstract LinkedHashMap<String, Hull> getHulls();

	public abstract LinkedHashMap<String, Module> getModules();

	protected HashSet<String> hubsNames = null;

	public Type getTypeByName(String name) {
		Type ret = getHulls().get(name);
		if (ret == null) {
			ret = getModules().get(name);
		}
		return ret;
	}

	protected final Eve eve = new Eve();

	/** get the {@link Eve} apiv2 access */
	public Eve eve() {
		return eve;
	}

	public static enum InventionDecryptor {

		None("No Decryptor", 0, 0, 0, 0, 1.0)
		, Accelerant("Accelerant Decryptor", 34201, 1, 2, 10, 1.2)
		, Attainment("Attainment Decryptor", 34202, 4, -1, +4, 1.8)
		, Augmentation("Augmentation Decryptor", 34203, 9, -2, +2, 0.6)
		, Parity("Parity Decryptor", 34204, 3, 1, -2, 1.5)
		, Process("Process Decryptor", 34205, 0, 3, 6, 1.1)
		, Symmetry("Symmetry Decryptor", 34206, 2,1, 8, 1.0)
		, OptimizedAttainment("Optimized Attainment Decryptor", 34207, 2, 1, -2, 1.9)
		, OptimizedAugmentation("Optimized Augmentation Decryptor", 34208, 7, 2, 0, 0.9);

		public final String name;
		public final int maxrun, me, te;
		public final double probmult;
		public final int id;

		private InventionDecryptor(String name, int id, int runs, int me, int te, double successMult) {
			this.name = name;
			this.id = id;
			maxrun = runs;
			this.me = me;
			this.te = te;
			probmult = successMult;
		}
	}

	public List<InventionDecryptor> decryptors() {
		return Arrays.asList(InventionDecryptor.values());
	}

}
