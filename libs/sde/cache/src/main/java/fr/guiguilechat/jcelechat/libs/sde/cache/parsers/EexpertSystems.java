package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EexpertSystems {

	//
	// SDE loading
	//

	public static final IntMapLoader<EexpertSystems> LOADER = new IntMapLoader<>(
			"expertSystems",
			EexpertSystems.class);

	//
	// file structure
	//

	public List<Integer> associatedShipTypes = new ArrayList<>();

	public int durationDays;
	public boolean hidden;
	public String internalName;
	public boolean retired;

	public static class SkillGranted {
		public int level;
		public int typeID;
	}

	public List<SkillGranted> skillsGranted = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out
				.println("first : internalName=" + first.internalName + " skillsGranted.size=" + first.skillsGranted.size()
						+ " retired=" + first.retired);
	}

}
