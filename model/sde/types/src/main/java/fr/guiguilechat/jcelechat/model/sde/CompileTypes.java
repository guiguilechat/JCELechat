package fr.guiguilechat.jcelechat.model.sde;

import java.io.IOException;

import com.helger.jcodemodel.exceptions.JCodeModelException;

import fr.guiguilechat.jcelechat.model.sde.compile.MainCompile;

/**
 * a main class to launch compiler without using maven. this is the same
 * invocation as maven.
 *
 */
public class CompileTypes {

	public static void main(String[] args) throws IOException, JCodeModelException {
		MainCompile.main("src/generated/java",
				"src/generated/resources/SDE/types/", "SDE/types/");
		// UnitTranslater.main(args);
	}

}
