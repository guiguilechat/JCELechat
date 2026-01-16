package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EcloneGrades {

	//
	// SDE loading
	//

	public static final IntMapLoader<EcloneGrades> LOADER = new IntMapLoader<>(
			"cloneGrades",
			EcloneGrades.class);

	//
	// file structure
	//

	public String name;

	public static record SkillLevel(int level, int typeID) {
	}

	public List<SkillLevel> skills = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}

}
