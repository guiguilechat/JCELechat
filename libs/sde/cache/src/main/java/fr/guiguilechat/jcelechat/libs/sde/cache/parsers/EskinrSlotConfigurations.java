package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EskinrSlotConfigurations {

	//
	// SDE loading
	//

	public static final IntMapLoader<EskinrSlotConfigurations> LOADER = new IntMapLoader<>(
			"skinrSlotConfigurations",
			EskinrSlotConfigurations.class);

	//
	// file structure
	//

	public boolean allowAllShips;
	public List<Integer> config = new ArrayList<>();
	public String name;
	public int priority;
	public List<Integer> ships = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}

}
