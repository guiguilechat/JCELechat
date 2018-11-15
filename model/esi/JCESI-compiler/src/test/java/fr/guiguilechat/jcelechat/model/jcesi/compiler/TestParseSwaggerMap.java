package fr.guiguilechat.jcelechat.model.jcesi.compiler;

import java.util.Map.Entry;

import io.swagger.models.ArrayModel;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

/**
 *
 *
 */
public class TestParseSwaggerMap {

	public static void main(String[] args) {
		Swagger sw = new SwaggerParser().read("src/test/resources/petstore.json");
		for (Entry<String, Path> e : sw.getPaths().entrySet()) {
			showSchema(e.getKey(), e.getValue().getGet().getResponses().get("200").getResponseSchema());
		}
	}

	public static void showSchema(String path, Model m) {
		if (m == null) {
			return;
		}
		System.err.println(path);
		if (m.getClass() == ArrayModel.class) {
			ArrayModel m2 = (ArrayModel) m;
			System.err.println(m2.getType() + " : " + m2.getItems());
		} else if (m.getClass() == ModelImpl.class) {
			ModelImpl m2 = (ModelImpl) m;
			System.err.println(" " + m2.getType() + " : " + m2.getAdditionalProperties());
		}
	}

}
