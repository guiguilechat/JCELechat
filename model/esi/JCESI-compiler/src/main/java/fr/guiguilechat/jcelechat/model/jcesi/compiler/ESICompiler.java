package fr.guiguilechat.jcelechat.model.jcesi.compiler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.PathTranslator.OpType;
import io.swagger.models.ArrayModel;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.ObjectProperty;
import io.swagger.models.properties.Property;
import io.swagger.parser.SwaggerParser;

public class ESICompiler {

	private static final Logger logger = LoggerFactory.getLogger(ESICompiler.class);

	/**
	 *
	 * @param args
	 *          { base url , destination folder } .base url is typically the
	 *          swagger URL without the swagger.json at the end.
	 * @throws IOException
	 * @throws JClassAlreadyExistsException
	 */
	public static void main(String... args) throws IOException, JClassAlreadyExistsException {
		long startTime = System.currentTimeMillis();
		logger.info("compiling esi with params " + Arrays.asList(args));
		ESICompiler c = new ESICompiler();
		c.setSwaggerURL(args[0]);
		JCodeModel cm = c.compile();
		File dir = new File(args[1]);
		delDir(dir);
		dir.mkdirs();
		cm.build(dir, (PrintStream) null);
		logger.info("compiled esi in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
	}

	protected static void delDir(File delete) {
		if (delete.exists()) {
			if (delete.isDirectory()) {
				for (File child : delete.listFiles()) {
					delDir(child);
				}
			}
			delete.delete();
		}
	}

	protected String swaggerURL;

	public void setSwaggerURL(String url) {
		swaggerURL = url;
	}

	public JCodeModel compile() throws JClassAlreadyExistsException {
		Swagger swagger = new SwaggerParser().read(swaggerURL);
		String baseURL = swagger.getSchemes().get(0).toValue()
				+ "://"
				+ swagger.getHost()
				+ (swagger.getBasePath() == null ? "" : swagger.getBasePath());

		JCodeModel cm = new JCodeModel();
		ClassBridge cltrans = makeClassBridge(cm, swagger);

		swagger.getPaths().entrySet().forEach(e -> {
			String resource = e.getKey();
			Path p = e.getValue();
			new PathTranslator(p.getGet(), OpType.get, baseURL + resource, cltrans).apply();
			new PathTranslator(p.getPut(), OpType.put, baseURL + resource, cltrans).apply();
			new PathTranslator(p.getDelete(), OpType.delete, baseURL + resource, cltrans).apply();
			new PathTranslator(p.getPost(), OpType.post, baseURL + resource, cltrans).apply();
		});
		return cm;
	}

	/** Override to change the way to generate a classBridge */
	protected ClassBridge makeClassBridge(JCodeModel cm, Swagger swagger) {
		return new ClassBridge(cm, swagger);
	}

	public static Response getResponse(Operation operation) {
		if (operation == null) {
			return null;
		}
		Response r = operation.getResponses().get("200");
		if (r == null) {
			r = operation.getResponses().get("201");
		}
		if (r == null) {
			r = operation.getResponses().get("204");
		}
		return r;
	}

	public static Map<String, Property> getStructureDef(Model m) {
		if (m == null) {
			return null;
		}
		if (m.getClass() == ArrayModel.class) {
			ArrayModel am = (ArrayModel) m;
			return getPropertyObject(am.getItems()).getProperties();
		}
		logger.warn("can't translate model class " + m.getClass());
		return null;
	}

	/**
	 * get the {@link ObjectProperty} at first or second level from a property. If
	 * the property defines an object, return it ; if the property defines an
	 * array, return the item type fo the array.<br />
	 * So passing as parameters a property which defines int[] or one which
	 * defines int will both return int.
	 *
	 * @param s
	 * @return the corresponding object property, or null.
	 */
	public static ObjectProperty getPropertyObject(Property s) {
		if (s == null) {
			return null;
		}
		switch (s.getType()) {
		case ObjectProperty.TYPE:
			return (ObjectProperty) s;
		case ArrayProperty.TYPE:
			Property sublevel = ((ArrayProperty) s).getItems();
			if (sublevel.getType() == ObjectProperty.TYPE) {
				return (ObjectProperty) sublevel;
			}
			// if an Arraypropert<y??> we return null so no break;
		default:
			return null;
		}
	}

}
