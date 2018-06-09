package fr.guiguilechat.eveonline.model.esi;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JDirectClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JPrimitiveType;
import com.helger.jcodemodel.JVar;

import v2.io.swagger.models.ArrayModel;
import v2.io.swagger.models.Model;
import v2.io.swagger.models.Operation;
import v2.io.swagger.models.Path;
import v2.io.swagger.models.Response;
import v2.io.swagger.models.parameters.BodyParameter;
import v2.io.swagger.models.parameters.Parameter;
import v2.io.swagger.models.parameters.PathParameter;
import v2.io.swagger.models.parameters.QueryParameter;
import v2.io.swagger.models.properties.ArrayProperty;
import v2.io.swagger.models.properties.BooleanProperty;
import v2.io.swagger.models.properties.DecimalProperty;
import v2.io.swagger.models.properties.FloatProperty;
import v2.io.swagger.models.properties.IntegerProperty;
import v2.io.swagger.models.properties.LongProperty;
import v2.io.swagger.models.properties.ObjectProperty;
import v2.io.swagger.models.properties.Property;
import v2.io.swagger.models.properties.StringProperty;
import v2.io.swagger.parser.SwaggerParser;

public class Compiler {

	private static final Logger logger = LoggerFactory.getLogger(Compiler.class);

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
		Compiler c = new Compiler();
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

	protected String baseURL;

	protected String responsesPackage = "responses";
	protected String structuresPackage = "structures";
	JDefinedClass jc;

	public Compiler() {

	}

	JCodeModel cm = null;
	JPackage responsePackage = null;
	JPackage structurePackage = null;

	private AbstractJClass headerhandlertype;

	static enum OpType {
		get, post, put, delete;
	};

	public JCodeModel compile() throws JClassAlreadyExistsException {
		v2.io.swagger.models.Swagger swagger = new SwaggerParser().read(swaggerURL);
		baseURL = swagger.getSchemes().get(0).toValue()
				+ "://"
				+ swagger.getHost()
				+ (swagger.getBasePath() == null ? "" : swagger.getBasePath());
		cm = new JCodeModel();
		String rootPackage = Compiler.class.getPackage().getName() + ".compiled";
		// List<String> add = Arrays.asList(baseURL.split("/")[2].split("\\."));
		// Collections.reverse(add);
		// rootPackage = add.stream().collect(Collectors.joining("."));
		// System.err.println("root package is " + rootPackage);

		jc = cm._class(rootPackage + "." + "Swagger", EClassType.INTERFACE);

		Set<String> allScopes = swagger.getPaths().values().stream().flatMap(p -> p.getOperations().stream())
				.filter(ope -> ope.getSecurity() != null).flatMap(ope -> ope.getSecurity().stream())
				.flatMap(m -> m.values().stream()).flatMap(l -> l.stream()).collect(Collectors.toSet());
		JFieldVar scopesField = jc.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String[].class), "SCOPES");
		JArray scopesinit = JExpr.newArray(cm.ref(String.class));
		for (String scope : allScopes) {
			scopesinit.add(JExpr.lit(scope));
		}
		scopesField.init(scopesinit);

		JMethod flatten = jc.method(JMod.PUBLIC, cm.ref(String.class), "flatten");
		flatten.param(cm.ref(Object.class), "o");

		headerhandlertype = cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(List.class).narrow(cm.ref(String.class)));

		JMethod coget = jc.method(JMod.PUBLIC, cm.ref(String.class), "connectGet");
		coget.param(cm.ref(String.class), "url");
		coget.param(cm.BOOLEAN, "connected");
		coget.param(headerhandlertype, "headerHandler");

		JMethod codel = jc.method(JMod.PUBLIC, cm.ref(String.class), "connectDel");
		codel.param(cm.ref(String.class), "url");
		codel.param(cm.BOOLEAN, "connected");
		codel.param(headerhandlertype, "headerHandler");

		JMethod copost = jc.method(JMod.PUBLIC, cm.ref(String.class), "connectPost");
		copost.param(cm.ref(String.class), "url");
		copost.param(cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(Object.class)), "content");
		copost.param(cm.BOOLEAN, "connected");
		copost.param(headerhandlertype, "headerHandler");

		JMethod coput = jc.method(JMod.PUBLIC, cm.ref(String.class), "connectPut");
		coput.param(cm.ref(String.class), "url");
		coput.param(cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(Object.class)), "content");
		coput.param(cm.BOOLEAN, "connected");
		coput.param(headerhandlertype, "headerHandler");

		JDirectClass genericType = cm.directClass("T");
		JMethod convert = jc.method(JMod.PUBLIC, genericType, "convert");
		convert.generify("T");
		convert.param(cm.ref(String.class), "line");
		convert.param(cm.ref(Class.class).narrow(genericType.wildcardExtends()), "clazz");

		responsePackage = cm._package(rootPackage + "." + responsesPackage);
		structurePackage = cm._package(rootPackage + "." + structuresPackage);

		swagger.getPaths().entrySet().forEach(e -> {
			String resource = e.getKey();
			Path p = e.getValue();
			// System.err.println(resource);
			addPath(OpType.get, resource, p.getGet());
			addPath(OpType.post, resource, p.getPost());
			addPath(OpType.delete, resource, p.getDelete());
			addPath(OpType.put, resource, p.getPut());

		});
		return cm;
	}

	protected void addPath(OpType optype, String path, Operation operation) {
		if (operation == null) {
			return;
		}
		Response r = operation.getResponses().get("200");
		if (r == null) {
			r = operation.getResponses().get("201");
		}
		if (r == null) {
			r = operation.getResponses().get("204");
		}
		if (r == null) {
			logger.error("can't find response for path " + path);
			return;
		}
		Property s = r.getSchema();
		AbstractJType retType = s == null ? cm.VOID : translateToClass(s, responsePackage, "R_" + s.getTitle());
		JMethod meth = jc.method(JMod.PUBLIC | JMod.DEFAULT, retType, operation.getOperationId());
		List<JVar> pathparameters = new ArrayList<>();
		List<JVar> queryparameters = new ArrayList<>();
		List<JVar> bodyparameters = new ArrayList<>();

		boolean connected = false;
		for (Parameter p : operation.getParameters()) {
			switch (p.getName()) {
			case "token":
				connected = true;
				break;
				// we skip following parameters
			case "user_agent":
			case "X-User-Agent":
			case "datasource":
			case "If-None-Match":
			case "Accept-language":
				// case "page":
				continue;
			default:
				String in = p.getIn();
				AbstractJType pt;
				JVar param;
				switch (in) {
				case "path":
					PathParameter pp = (PathParameter) p;
					pt = getExistingClass(pp.getType(), pp.getName(), pp.getFormat(), pp.getEnum());
					if (!pp.getRequired() && pt.isPrimitive()) {
						pt=pt.boxify();
					}
					pathparameters.add(meth.param(pt, pp.getName()));
					break;
				case "query":
					QueryParameter qp = (QueryParameter) p;
					param = meth.param(getExistingClass(qp), qp.getName());
					queryparameters.add(param);
					break;
				case "body":
					BodyParameter bp = (BodyParameter) p;
					Model schema = bp.getSchema();
					if (schema instanceof ArrayModel) {
						pt = getExistingClass((ArrayModel) schema);
						param = meth.param(pt, bp.getName());
						bodyparameters.add(param);
					} else {
						for (Entry<String, Property> e : schema.getProperties().entrySet()) {
							AbstractJType type = translateToClass(e.getValue(), structurePackage, e.getKey());
							param = meth.param(type, e.getKey());
							bodyparameters.add(param);
						}
					}
					break;
				default:
					logger.error("no matching type " + p.getIn() + " for parameter " + p.getName() + " in " + path);
				}
			}
		}
		meth.javadoc().add(("<p>\n" + ("" + operation.getDescription()).replaceAll("---", "<br />") + "\n</p>")
				.replaceAll("\n\n", "\n").replaceAll("<br />\n<", "<").replaceAll("\n<br />\n", "<br />\n"));
		if (operation.getVendorExtensions().containsKey("x-required-roles")) {
			Object extension = operation.getVendorExtensions().get("x-required-roles");
			@SuppressWarnings("unchecked")
			List<String> roles = (List<String>) extension;
			if (!roles.isEmpty()) {
				JFieldVar rolesfield = jc.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String.class).array(),
						(operation.getOperationId() + "_roles").toUpperCase());
				JArray array = JExpr.newArray(cm.ref(String.class));
				for (String role : roles) {
					array.add(JExpr.lit(role));
				}
				rolesfield.init(array);
				rolesfield.javadoc().add("the roles required for {@link #" + meth.name() + " this method}");
				meth.javadoc().add("\n<p>\nrequire the roles specified {@link #" + rolesfield.name() + " here}\n</p>");
			}
		}
		JVar header = meth.param(headerhandlertype, "headerHandler");

		String urlAssign = "\"" + baseURL + path + "\"";
		for (JVar jv : pathparameters) {
			urlAssign += ".replace(\"{" + jv.name() + "}\", \"\"+" + jv.name() + ")";
		}
		if (queryparameters.size() > 0) {
			urlAssign += "+\"?\"";
		}
		for (int pi = 0; pi < queryparameters.size(); pi++) {
			JVar qp = queryparameters.get(pi);
			if (qp.type() instanceof JPrimitiveType) {
				urlAssign += "+\"&" + qp.name() + "=\"+flatten(" + qp.name() + ")";
			} else {
				urlAssign += "+("+qp.name()+"==null?\"\":\"&" + qp.name() + "=\"+flatten(" + qp.name() + "))";
			}
		}
		JVar url = meth.body().decl(cm.ref(String.class), "url");
		url.init(JExpr.direct(urlAssign));
		switch (optype) {
		case post:
		case put:
			String methName = optype == OpType.post ? "connectPost" : "connectPut";
			JVar content = null;
			if (!bodyparameters.isEmpty()) {
				content = meth.body().decl(cm.ref(Map.class).narrow(cm.ref(String.class)).narrow(cm.ref(Object.class)),
						"content");
				content.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
				for (JVar p : bodyparameters) {
					meth.body().directStatement("content.put(\"" + p.name() + "\", " + p.name() + ");");
				}
			}
			if (s == null) {
				meth.body().invoke(methName).arg(url)
				.arg(content == null ? cm.ref(Collections.class).staticInvoke("emptyMap") : content)
				.arg(JExpr.lit(connected)).arg(header);
			} else {
				JVar fetched = meth.body().decl(cm.ref(String.class), "fetched");
				fetched.init(JExpr.invoke(methName).arg(url)
						.arg(content == null ? cm.ref(Collections.class).staticInvoke("emptyMap") : content)
						.arg(JExpr.lit(connected)).arg(header));
			}
			break;
		case get:
			meth.body().directStatement(
					"String fetched=" + "connectGet(url," + Boolean.toString(connected) + ", headerHandler);");
			break;
		case delete:
			meth.body().directStatement(
					"connectDel(url," + Boolean.toString(connected) + ", headerHandler);");
			break;
		default:
			throw new UnsupportedOperationException("unsupported type " + optype + " for path " + path);
		}
		if (s != null) {
			meth.body()._return(
					JExpr.invoke("convert").arg(JExpr.direct("fetched")).arg(JExpr.direct(retType.binaryName() + ".class")));
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
		AbstractJType ret = getExistingClass(p.getType(), name, p.getFormat(), null);
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

	protected AbstractJType getExistingClass(String type, String name, String format, List<String> enums) {
		switch (type) {
		case IntegerProperty.TYPE:
			if (format == null) {
				return cm.LONG;
			}
			switch (format) {
			case LongProperty.FORMAT:
				return cm.LONG;
			case IntegerProperty.FORMAT:
				return cm.INT;
			default:
				throw new UnsupportedOperationException("can't translate property name " + name + " with format " + format);
			}
		case BooleanProperty.TYPE:
			return cm.BOOLEAN;
		case StringProperty.TYPE:
			if (enums != null && !enums.isEmpty()) {
				return getStringEnum(name, enums);
			}
			return cm.ref(String.class);
		case DecimalProperty.TYPE:
			switch (format) {
			case FloatProperty.FORMAT:
				return cm.FLOAT;
			default:
				return cm.DOUBLE;
			}
		}
		JDefinedClass created = cm._getClass(type);
		if (created != null) {
			return created;
		}
		return null;
	}

	protected AbstractJType getStringEnum(String name, List<String> enums) {
		JDefinedClass ret = null;
		try {
			ret = jc._enum(JMod.PUBLIC | JMod.STATIC, name);
			JFieldVar toStringf = ret.field(JMod.PUBLIC | JMod.FINAL, cm.ref(String.class), "toString");
			JMethod constr = ret.constructor(0);
			JVar toStringp = constr.param(cm.ref(String.class), "toString");
			constr.body().assign(JExpr.refthis(toStringf), toStringp);
			JMethod toStringm = ret.method(JMod.PUBLIC, cm.ref(String.class), "toString");
			toStringm.body()._return(toStringf);
			toStringm.annotate(Override.class);
			for (String s : enums) {
				ret.enumConstant(s.replaceAll("-", "_")).arg(JExpr.lit(s));
			}
			// logger.info("created enum " + name + " with values " + enums);
			return ret;
		} catch (JClassAlreadyExistsException e) {
			// logger.info("can't recreate enum " + name + " with values " + enums);
			for (JDefinedClass cl : jc.classes()) {
				if (cl.name().equals(name)) {
					return cl;
				}
			}
			return null;
		}
	}

	protected AbstractJType getExistingClass(ArrayModel model) {
		return translateToClass(model.getItems(), structurePackage, "S_" + model.getTitle()).array();
	}

	protected AbstractJType getExistingClass(QueryParameter pp) {
		if (pp.getType().equals(ArrayProperty.TYPE)) {
			return getExistingClass(pp.getItems()).array();
		} else {
			AbstractJType type = getExistingClass(pp.getType(), pp.getName(), pp.getFormat(), pp.getEnum());
			if (type != null && !pp.getRequired() && type.isPrimitive()) {
				type = type.boxify();
			}
			return type;
		}
	}

	public AbstractJType getExistingClass(Property pp) {
		if (pp.getType().equals(ArrayProperty.TYPE)) {
			ArrayProperty ap = (ArrayProperty) pp;
			return getExistingClass(ap.getItems()).array();
		} else {
			return getExistingClass(pp.getType(), pp.getName(), pp.getFormat(), null);
		}
	}

	protected HashMap<Map<String, String>, AbstractJClass> createdClasses = new HashMap<>();

	protected AbstractJClass translateToClass(ObjectProperty p, JPackage pck, String name) {
		Map<String, String> classDef = p.getProperties().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue().getType()));
		AbstractJClass createdClass = createdClasses.get(classDef);
		try {
			JDefinedClass cl = pck._class(name.replaceAll("_ok", ""));
			if (createdClass != null) {
				cl._extends(createdClass);
			} else {
				for (Entry<String, Property> e : p.getProperties().entrySet()) {
					cl.field(JMod.PUBLIC, translateToClass(e.getValue(), pck, name + "_" + e.getKey()), e.getKey());
				}
				createdClasses.put(classDef, cl);
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
