package fr.guiguilechat.jcelechat.model.sde.items;

import java.io.IOException;

import fr.guiguilechat.jcelechat.model.sde.compile.MainCompile;
import fr.guiguilechat.jcelechat.model.sde.translate.UnitTranslater;

/**
 * a main class to launch compiler without using maven. this is the same
 * invocation as maven.
 *
 */
public class CompileItems {

	public static void main(String[] args) throws IOException {
		MainCompile.main("src/generated/java",
				"src/generated/resources/SDE/types/", "SDE/types/");
		UnitTranslater.main(args);
	}

}
