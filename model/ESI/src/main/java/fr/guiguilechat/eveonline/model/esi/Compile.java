package fr.guiguilechat.eveonline.model.esi;

import java.io.IOException;

import com.helger.jcodemodel.JClassAlreadyExistsException;

public class Compile {

	public static void main(String... args) throws IOException, JClassAlreadyExistsException {
		Compiler.main("https://esi.tech.ccp.is/latest/", "src/generated/java/");
	}

}
