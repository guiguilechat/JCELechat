package fr.guiguilechat.jcelechat.jcesi.compile;

import java.io.IOException;

import com.helger.jcodemodel.JClassAlreadyExistsException;

import fr.guiguilechat.jcelechat.model.jcesi.ESICompiler;

public class CompileSwagger {

	public static void main(String... args) throws IOException, JClassAlreadyExistsException {
		ESICompiler.main("https://esi.tech.ccp.is/_latest/swagger.json", "src/generated/java/");
	}

}
