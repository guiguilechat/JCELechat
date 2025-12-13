package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EtypeMaterials {

	//
	// SDE loading
	//

	public static final IntMapLoader<EtypeMaterials> LOADER = new IntMapLoader<>(
			"typeMaterials",
			EtypeMaterials.class);

	//
	// file structure
	//

	public static class Material {
		public int quantity;
		/** {@link Etypes} */
		public int materialTypeID;

		@Override
		public String toString() {
			return "" + quantity + "×id:" + materialTypeID;
		}
	}

	public ArrayList<Material> materials = new ArrayList<>();

	public static class RandomizedMaterial {
		public int quantityMax;
		public int quantityMin;
		/** {@link Etypes} */
		public int materialTypeID;

		@Override
		public String toString() {
			return "[" + quantityMin + "-" + quantityMax + "]×id:" + materialTypeID;
		}
	}

	public ArrayList<RandomizedMaterial> randomizedMaterials = new ArrayList<>();

	@Override
	public String toString() {
		return materials.toString();
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : materials=" + first.materials.size());
		for(var val : loaded.values()) {
			assert val.getClass().equals(first.getClass());
		}
	}

}
