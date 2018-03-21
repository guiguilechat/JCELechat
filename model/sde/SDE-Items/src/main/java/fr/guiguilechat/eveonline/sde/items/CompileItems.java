package fr.guiguilechat.eveonline.sde.items;

import java.io.IOException;

import fr.guiguilechat.eveonline.model.sde.compile.SDECompiler;

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
