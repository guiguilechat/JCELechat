package fr.guiguilechat.jcelechat.model.sde.items;

import java.io.IOException;

import fr.guiguilechat.jcelechat.model.sde.compile.SDECompiler;

/**
 * a main class to launch compiler without using maven. this is the same
 * invocation as maven.
 *
 */
public class CompileItems {

	public static void main(String[] args) throws IOException {
		SDECompiler.main("src/generated/java",
				"src/generated/resources/SDE/items/", "SDE/items/");
	}

}
