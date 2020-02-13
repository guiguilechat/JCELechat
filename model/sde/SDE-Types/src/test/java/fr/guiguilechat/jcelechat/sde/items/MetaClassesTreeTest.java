package fr.guiguilechat.jcelechat.sde.items;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;

public class MetaClassesTreeTest {

	public static void main(String[] args) {
		IMetaGroup<? extends Asteroid> group = Asteroid.METACAT.groups().iterator().next();
		System.err.println(" group name " + group.getName());
		for (Entry<String, ? extends Asteroid> e : group.load().entrySet()) {
			System.err.println("  " + e.getKey());
		}
	}

}
