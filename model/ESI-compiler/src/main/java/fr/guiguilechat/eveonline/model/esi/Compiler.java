package fr.guiguilechat.eveonline.model.esi;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public class Compiler {
	public static void main(String[] args) {
		Swagger swagger = new SwaggerParser().read("https://esi.tech.ccp.is/latest/swagger.json");
		System.err.println("path is " + swagger.getHost() + swagger.getBasePath());
	}
}
