package fr.guiguilechat.jcelechat.sde.items;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.types.Module;

public class ListAbyssals {

	public static void main(String[] args) {
		System.err.println("name\tid\tstatsAbyssal\tnameAbyssal");
		Predicate<Module> testAbyssal = module ->
		// module.MetaLevel == 0 &&
		module.marketGroup == 0 && module.hp == 0
		&& module.volume != 0;
		Module.METACAT.load().values()
		.stream()
		.filter(
				module -> testAbyssal.test(module) || module.name.contains("Abyssal"))
		.sorted((m1, m2) -> m1.name.compareTo(m2.name)).forEach(module -> {
			System.err.println(module.name + "\t" + module.id + "\t"
					+ testAbyssal.test(module) + "\t"
					+ module.name.contains("Abyssal"));
		});
	}

}
