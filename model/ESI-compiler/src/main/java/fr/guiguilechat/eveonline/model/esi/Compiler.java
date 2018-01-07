package fr.guiguilechat.eveonline.model.esi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JVar;

import io.swagger.models.ArrayModel;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.BooleanProperty;
import io.swagger.models.properties.DecimalProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.ObjectProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;
import io.swagger.parser.SwaggerParser;

public class Compiler {
	/**
	 *
	 * @param args
	 *          { base url , destination folder } .base url is typically the
	 *          swagger URL without the swagger.json at the end.
	 * @throws IOException
	 * @throws JClassAlreadyExistsException
	 */
	public static void main(String[] args) throws IOException, JClassAlreadyExistsException {
		Compiler c = new Compiler();
		c.setBaseURL(args[0]);
		JCodeModel cm = c.compile();
		File dir = new File(args[1]);
		dir.mkdirs();
		cm.build(dir);
	}

	protected String baseURL;

	public void setBaseURL(String url) {
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		baseURL = url;
	}

	protected String swaggerFile = "swagger.json";

	protected String responsesPackage = "responses";
	protected String structuresPackage = "structures";
	JDefinedClass jc;

	public Compiler() {

	}

	JCodeModel cm = null;
	JPackage responsePackage = null;
	JPackage structurePackage = null;

	public JCodeModel compile() throws JClassAlreadyExistsException {
		Swagger swagger = new SwaggerParser().read(baseURL + "/" + swaggerFile);
		cm = new JCodeModel();
		List<String> add = Arrays.asList(baseURL.split("/")[2].split("\\."));
		Collections.reverse(add);
		String rootPackage = add.stream().collect(Collectors.joining("."));
		System.err.println("root package is " + rootPackage);

		jc = cm._class(rootPackage + "." + "Swagger", EClassType.INTERFACE);
		jc._extends(cm.ref(RequestHandler.class));

		responsePackage = cm._package(rootPackage + "." + responsesPackage);
		structurePackage = cm._package(rootPackage + "." + structuresPackage);

		swagger.getPaths().entrySet().forEach(e -> {
			String resource = e.getKey();
			Path p = e.getValue();
			System.err.println(resource);
			addPath(false, resource, p.getGet());
			addPath(true, resource, p.getPost());
		});
		return cm;
	}

	protected void addPath(boolean isPost, String path, Operation operation) {
		if (operation != null) {
			Response r = operation.getResponses().get("200");
			if (r != null) {
				Property s = r.getSchema();
				AbstractJType retType = translateToClass(s, responsePackage, "R_" + s.getTitle());
				JMethod meth = jc.method(JMod.PUBLIC | JMod.DEFAULT, retType, operation.getOperationId());
				List<JVar> pathparameters = new ArrayList<>();
				List<JVar> queryparameters = new ArrayList<>();
				List<JVar> bodyparameters = new ArrayList<>();

				boolean connected = false;
				for (Parameter p : operation.getParameters()) {
					if (p.getRequired()) {
						if (p instanceof PathParameter) {
							PathParameter pp = (PathParameter) p;
							AbstractJType pt = getExistingClass(pp.getType());
							pathparameters.add(meth.param(pt, pp.getName()));
						} else if (p instanceof QueryParameter) {
							QueryParameter qp = (QueryParameter) p;
							AbstractJType pt = getExistingClass(qp);
							JVar param = meth.param(pt, qp.getName());
							queryparameters.add(param);
						} else if (p instanceof BodyParameter) {
							BodyParameter bp = (BodyParameter) p;
							AbstractJType pt = getExistingClass(bp.getSchema());
							JVar param = meth.param(pt, bp.getName());
							bodyparameters.add(param);
						} else {
							System.err.println("  no match for parameter " + p.getClass());
						}
					} else {
						if (p.getName().equals("token")) {
							connected=true;
						}
					}

				}
				String urlAssign = "String url=\"" + baseURL + path + "\"";
				for (JVar jv : pathparameters) {
					urlAssign += ".replace(\"{" + jv.name() + "}\", \"\"+" + jv.name() + ")";
				}
				for (int pi = 0; pi < queryparameters.size(); pi++) {
					JVar qp = queryparameters.get(pi);
					urlAssign += "+\"" + (pi == 0 ? '?' : '&') + qp.name() + "=\"+flatten(" + qp.name() + ")";
				}
				meth.body().directStatement(urlAssign + ";");
				if (isPost) {
					if (bodyparameters.isEmpty()) {
						meth.body().directStatement("java.util.Map<String, String> content = java.util.Collections.emptyMap();");
					} else {
						meth.body().directStatement("java.util.Map<String, String> content = new java.util.HashMap<>();");
						for (JVar p : bodyparameters) {
							meth.body().directStatement("content.put(\"" + p.name() + "\", flatten(" + p.name() + "));");
						}
					}
					meth.body()
					.directStatement("String fetched=" + "connectPost(url, content," + Boolean.toString(connected) + ");");
				} else {
					meth.body().directStatement("String fetched=" + "connectGet(url," + Boolean.toString(connected) + ");");
				}
				meth.body().directStatement("return convert(fetched, " + retType.binaryName() + ".class);");
			}
		}
	}

	/**
	 * translate a property into a JClass . Create it if needed, return any
	 * already created if exists.
	 *
	 * @param p
	 *          The property to transform
	 * @param pck
	 *          the package to create the new class into
	 * @param name
	 *          the new name of the class
	 * @return
	 */
	protected AbstractJType translateToClass(Property p, JPackage pck, String name) {
		AbstractJType ret = getExistingClass(p.getType());
		if (ret != null) {
			return ret;
		}
		switch (p.getType()) {
		case ObjectProperty.TYPE:
			return translateToClass((ObjectProperty) p, pck, name);
		case ArrayProperty.TYPE:
			return translateToClass((ArrayProperty) p, pck, name);
		default:
			throw new UnsupportedOperationException("case not handled " + p.getType());
		}
	}

	protected AbstractJType getExistingClass(String name) {
		switch (name) {
		case IntegerProperty.TYPE:
			return cm.LONG;
		case BooleanProperty.TYPE:
			return cm.BOOLEAN;
		case StringProperty.TYPE:
			return cm.ref(String.class);
		case DecimalProperty.TYPE:
			return cm.DOUBLE;
		}
		JDefinedClass created = cm._getClass(name);
		if (created != null) {
			return created;
		}
		return null;
	}

	protected AbstractJType getExistingClass(Model model) {
		if (model instanceof ArrayModel) {
			return translateToClass(((ArrayModel) model).getItems(), structurePackage, "S_" + model.getTitle()).array();
		}
		System.err.println(
				"  model " + model.getTitle() + " has class " + model.getClass() + " properties " + model.getProperties());
		return null;
	}

	protected AbstractJType getExistingClass(QueryParameter pp) {
		if (pp.getType().equals(ArrayProperty.TYPE)) {
			return getExistingClass(pp.getItems().getType()).array();
		} else {
			return getExistingClass(pp.getType());
		}
	}

	protected AbstractJClass translateToClass(ObjectProperty p, JPackage pck, String name) {
		try {
			JDefinedClass cl = pck._class(name.replaceAll("_ok", ""));
			for (Entry<String, Property> e : p.getProperties().entrySet()) {
				cl.field(JMod.PUBLIC, translateToClass(e.getValue(), pck, name + "_" + e.getKey()), e.getKey());
			}
			return cl;
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	protected AbstractJClass translateToClass(ArrayProperty p, JPackage pck, String name) {
		AbstractJType arraCl = translateToClass(p.getItems(), pck, name);
		return arraCl.array();
	}
}
