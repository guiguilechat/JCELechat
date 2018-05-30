package fr.guiguilechat.eveonline.model.esi.compile;

import java.io.IOException;

import com.helger.jcodemodel.JClassAlreadyExistsException;

import fr.guiguilechat.eveonline.model.esi.Compiler;

public class Compile {

	public static void main(String... args) throws IOException, JClassAlreadyExistsException {
		Compiler.main("https://esi.tech.ccp.is/_latest/swagger.json", "src/generated/java/");
	}

}
