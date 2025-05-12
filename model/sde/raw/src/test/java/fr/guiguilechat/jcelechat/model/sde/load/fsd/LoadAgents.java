package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

public class LoadAgents {

	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();
		LinkedHashMap<Integer, Eagents> loaded = Eagents.load();
		long timeStop = System.currentTimeMillis();
		System.out.println("loaded " + loaded.size() + " agents in " + (timeStop - timeStart) + " ms");
		Eagents agent0 = loaded.values().iterator().next();
		System.out.println(agent0.level);
	}

}
