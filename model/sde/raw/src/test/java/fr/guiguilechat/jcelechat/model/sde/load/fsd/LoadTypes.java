package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

public class LoadTypes {

	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();
		LinkedHashMap<Integer, Etypes> loaded = Etypes.LOADER.load();
		long timeStop = System.currentTimeMillis();
		System.out.println("loaded " + loaded.size() + " types in " + (timeStop - timeStart) + " ms");
	}

}
