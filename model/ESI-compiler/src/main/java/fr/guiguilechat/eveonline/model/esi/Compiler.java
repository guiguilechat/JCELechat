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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JDirectClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPrimitiveType;
import com.helger.jcodemodel.JTypeVar;
import com.helger.jcodemodel.JVar;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import v2.io.swagger.models.properties.ObjectProperty;
import v2.io.swagger.models.properties.Property;
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

	protected String cacheClassName = "SwaggerCache";
	protected JDefinedClass cacheClass;
	protected JMethod methFetchCacheObject;
	protected JMethod methFetchCacheArray;

	public Compiler() {

	}

	v2.io.swagger.models.Swagger swagger;

	JCodeModel cm = null;

	static enum OpType {
		get, post, put, delete;
	};

	ClassBridge cltrans;

	public JCodeModel compile() throws JClassAlreadyExistsException {
		swagger = new SwaggerParser().read(swaggerURL);
		baseURL = swagger.getSchemes().get(0).toValue()
				+ "://"
				+ swagger.getHost()
				+ (swagger.getBasePath() == null ? "" : swagger.getBasePath());
		cm = new JCodeModel();

		cltrans = new ClassBridge(cm, swagger);

		cacheClass = cm._class(JMod.PUBLIC | JMod.ABSTRACT, cltrans.rootPackage + "." + cacheClassName, EClassType.CLASS);
		createCacheMethods();

		swagger.getPaths().entrySet().forEach(e -> {
			String resource = e.getKey();
			Path p = e.getValue();
			addPath(OpType.get, resource, p.getGet());
			addPath(OpType.post, resource, p.getPost());
			addPath(OpType.delete, resource, p.getDelete());
			addPath(OpType.put, resource, p.getPut());

		});
		return cm;
	}

	public void createCacheMethods() throws JClassAlreadyExistsException {
		JTypeVar cacheMainType = cacheClass.generify("T", cltrans.swaggerClass);
		cacheClass.javadoc().addParam(cacheMainType.binaryName()).add(
				"the type of Swagger this refers to. this parameter allows to work on specific implementation of Swagger, thus call its methdos instead of having to cast.");
		JFieldVar cacheParent = cacheClass.field(JMod.PUBLIC | JMod.FINAL, cacheMainType, "swagger");

		// add a constructor with swagger param
		JMethod cachecons = cacheClass.constructor(JMod.PUBLIC);
		JVar swag = cachecons.param(cacheMainType, "swag");
		cachecons.body().assign(cacheParent, swag);

		// add the pausable interface
		JDefinedClass pausable = cacheClass._class(JMod.PUBLIC, "Pausable", EClassType.INTERFACE);
		pausable.method(JMod.PUBLIC, cm.VOID, "pause");
		pausable.method(JMod.PUBLIC, cm.VOID, "resume");

		{
			// add
			// public <T> Pausable addFetchCacheArray(String name,
			// BiFunction<Integer, Map<String, List<String>>, T[]> fetcher,
			// Consumer<List<T>> cacheHandler, String... requiredRoles)
			methFetchCacheArray = cacheClass.method(JMod.PUBLIC | JMod.ABSTRACT, pausable,
					"addFetchCacheArray");
			JTypeVar typeVar = methFetchCacheArray.generify("U");
			methFetchCacheArray.param(cm.ref(String.class), "name");
			methFetchCacheArray.param(
					cm.ref(BiFunction.class).narrow(cm.ref(Integer.class),
							cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(List.class).narrow(String.class)), typeVar.array()),
					"fetcher");
			methFetchCacheArray.param(cm.ref(Consumer.class).narrow(cm.ref(List.class).narrow(typeVar)), "cacheHandler");
			methFetchCacheArray.varParam(cm.ref(String.class), "requiredRoles");
		}

		{
			// add
			// public <T> Pausable addFetchCacheObject(String name,
			// Function<Map<String, List<String>>,T> fetcher,
			// Consumer<T> cacheHandler, String... requiredRoles)
			methFetchCacheObject = cacheClass.method(JMod.PUBLIC | JMod.ABSTRACT, pausable, "addFetchCacheObject");
			JTypeVar typeVar = methFetchCacheObject.generify("U");
			methFetchCacheObject.param(cm.ref(String.class), "name");
			methFetchCacheObject.param(
					cm.ref(Function.class).narrow(
							cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(List.class).narrow(String.class)), typeVar),
					"fetcher");
			methFetchCacheObject.param(cm.ref(Consumer.class).narrow(typeVar), "cacheHandler");
			methFetchCacheObject.varParam(cm.ref(String.class), "requiredRoles");
		}
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

	protected String headerHandlerName = "headerHandler";

	protected void addPath(OpType optype, String path, Operation operation) {
		if (operation == null) {
			return;
		}
		Response r = getResponse(operation);
		if (r == null) {
			logger.error("can't find response for path " + path + " " + optype);
			return;
		}
		Property s = r.getSchema();

		AbstractJType retType = cltrans.getReponseClass(s);
		JMethod meth = cltrans.swaggerClass.method(JMod.PUBLIC | JMod.DEFAULT, retType, operation.getOperationId());

		List<JVar> pathparameters = new ArrayList<>();
		List<JVar> queryparameters = new ArrayList<>();
		List<JVar> bodyparameters = new ArrayList<>();
		boolean connected = extractParameters(meth, operation, pathparameters, queryparameters, bodyparameters);

		@SuppressWarnings("unchecked")
		List<String> requiredRoles = operation.getVendorExtensions().containsKey("x-required-roles")
		? (List<String>) operation.getVendorExtensions().get("x-required-roles")
				: Collections.emptyList();

		addPathJavadoc(meth, operation, requiredRoles);

		JVar header = meth.param(cltrans.headerhandlertype(), headerHandlerName);

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
					"String fetched=" + "connectGet(url," + Boolean.toString(connected) + ", " + headerHandlerName + ");");
			createCache(operation, meth, s, r, retType, requiredRoles);
			break;
		case delete:
			meth.body().directStatement(
					"connectDel(url," + Boolean.toString(connected) + ", " + headerHandlerName + ");");
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
	 * extract the parameters from an operation and put them in corresponding
	 * list. also add javadoc on the method.
	 *
	 * @param meth
	 *          the method wed are creating, to add the javadoc.
	 * @param operation
	 *          the operation we create the method from.
	 * @param pathparameters
	 *          the list to add the path parameters into.
	 * @param queryparameters
	 *          the list to add the query parameters into.
	 * @param bodyparameters
	 *          the list to add body parameters into.
	 * @return true if the parameters deduced the path requires to be connected
	 */
	protected boolean extractParameters(JMethod meth, Operation operation, List<JVar> pathparameters,
			List<JVar> queryparameters, List<JVar> bodyparameters) {
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
			case "Accept-Language":
			case "language":
				// case "page":
				continue;
			default:
				String in = p.getIn();
				AbstractJType pt;
				JVar param;
				switch (in) {
				case "path":
					meth.javadoc().addParam(p.getName()).add(p.getDescription());
					PathParameter pp = (PathParameter) p;
					pt = cltrans.getExistingClass(pp.getType(), pp.getName(), pp.getFormat(), pp.getEnum());
					if (!pp.getRequired() && pt.isPrimitive()) {
						pt = pt.boxify();
					}
					pathparameters.add(meth.param(pt, pp.getName()));
					break;
				case "query":
					meth.javadoc().addParam(p.getName()).add(p.getDescription());
					QueryParameter qp = (QueryParameter) p;
					param = meth.param(cltrans.getExistingClass(qp), qp.getName());
					queryparameters.add(param);
					break;
				case "body":
					BodyParameter bp = (BodyParameter) p;
					Model schema = bp.getSchema();
					if (schema instanceof ArrayModel) {
						meth.javadoc().addParam(p.getName()).add(p.getDescription());
						pt = cltrans.getExistingClass((ArrayModel) schema);
						param = meth.param(pt, bp.getName());
						bodyparameters.add(param);
					} else {
						for (Entry<String, Property> e : schema.getProperties().entrySet()) {
							meth.javadoc().addParam(e.getKey()).add(e.getValue().getDescription());
							AbstractJType type = cltrans.translateToClass(e.getValue(), cltrans.structurePackage, e.getKey());
							param = meth.param(type, e.getKey());
							bodyparameters.add(param);
						}
					}
					break;
				default:
					logger.error(
							"no matching type " + p.getIn() + " for parameter " + p.getName() + " in operation "
									+ operation.getOperationId());
				}
			}
		}
		return connected;
	}

	protected void addPathJavadoc(JMethod meth, Operation operation, List<String> requiredRoles) {
		meth.javadoc().append("" + operation.getSummary());
		meth.javadoc().add(("\n<p>\n" + ("" + operation.getDescription()).replaceAll("---", "<br />") + "\n</p>")
				.replaceAll("\n\n", "\n").replaceAll("<br />\n<", "<").replaceAll("\n<br />\n", "<br />\n"));
		if (!requiredRoles.isEmpty()) {
			if (!requiredRoles.isEmpty()) {
				JFieldVar rolesfield = cltrans.swaggerClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL,
						cm.ref(String.class).array(),
						(operation.getOperationId() + "_roles").toUpperCase());
				JArray array = JExpr.newArray(cm.ref(String.class));
				for (String role : requiredRoles) {
					array.add(JExpr.lit(role));
				}
				rolesfield.init(array);
				rolesfield.javadoc().add("the roles required for {@link #" + meth.name() + " this method}");
				meth.javadoc().add("\n<p>\nrequire the roles specified {@link #" + rolesfield.name() + " here}\n</p>");
			}
		}
	}

	protected void createCache(Operation operation, JMethod meth, Property responseSchema, Response r,
			AbstractJType retType,
			List<String> requiredRoles) {
		String fieldName = operation.getOperationId().split("_")[1];
		JDefinedClass cacheGroup = getCacheGroupClass(fieldName);
		List<Parameter> remainingArgs = operation.getParameters().stream()
				.filter(p -> !p.getName().equals(headerHandlerName) && !p.getName().equals("page") && p.getRequired())
				.collect(Collectors.toList());
		if (remainingArgs.size() > 1) {
			logger.debug("skip obj met " + meth.name() + " : too many arguments to create a cache : "
					+ remainingArgs.stream().map(Parameter::getName).collect(Collectors.toList()));
			return;
		}
		JVar parameter = meth.params().stream().filter(p -> p.name() != headerHandlerName).findFirst().orElse(null);
		if (retType.isArray()) {
			createCacheArray(operation, meth, ((ArrayProperty) responseSchema).getItems(), retType.elementType(),
					requiredRoles, cacheGroup, parameter);
		} else {
			if (parameter == null) {
				createCacheObject(operation, meth, retType, requiredRoles, cacheGroup);
			} else {
				createCacheObject(operation, meth, retType, requiredRoles, cacheGroup, parameter);
			}
		}
	}

	/**
	 * create a caching method.
	 *
	 * @param operation
	 *          the swagger operation for which we make a cache
	 * @param meth
	 *          the Swagger method we created that actually represents this
	 *          operation
	 * @param responseSchema
	 *          the schema for the item in the array (eg if the path returns
	 *          int[], this should be int)
	 * @param elementType
	 *          the class we created to represent this response element.
	 * @param requiredRoles
	 *          the roles required in order to fetch this path.
	 * @param cacheGroup
	 *          the class in which we create the cache method.
	 * @param parameter
	 *          the optional parameter to consider as unique id. we assume there
	 *          is also a page and a
	 */
	protected void createCacheArray(Operation operation, JMethod meth, Property responseSchema, AbstractJType elementType,
			List<String> requiredRoles, JDefinedClass cacheGroup, JVar parameter) {
		// find out the response fields that are unique
		// so far CCP add "U|unique " in the property description for those fields.
		List<String> uniqueFields = new ArrayList<>();
		// we only have fields for reponses that are of type Object[], eg int[]
		// can't have a unique field, nor Object[][]
		if (responseSchema.getType().equals(ObjectProperty.TYPE)) {
			ObjectProperty op = (ObjectProperty) responseSchema;
			for (Entry<String, Property> esp : op.getProperties().entrySet()) {
				if (esp.getValue().getDescription().toLowerCase().contains("unique ")) {
					uniqueFields.add(esp.getKey());
				}
			}
		}
		if (uniqueFields.size() == 0 && parameter == null) {
			// createCacheArrayNoUnique(operation, meth, responseSchema, elementType,
			// requiredRoles, cacheGroup);
		} else if (parameter == null) {
			// createCacheArrayWithUnique(operation, meth, responseSchema,
			// elementType, requiredRoles, cacheGroup,
			// uniqueFields.get(0));
		} else {

			System.err.println("can't use more than one uniqu field " + meth.name());
		}
	}

	/**
	 * create a cache for a path that returns an array of items, the items having
	 * no unique field
	 */
	protected void createCacheArrayNoUnique(Operation operation, JMethod meth, Property responseSchema,
			AbstractJType elementType, List<String> requiredRoles, JDefinedClass cacheGroup) {
		// we have no unique field to identify the returned values. thus we return
		// a new observableList, that will be cleared on new cache data.

		// private ObservableList<U> operationid_container = null
		JVar container = cacheGroup.field(JMod.PRIVATE, cm.ref(ObservableList.class).narrow(elementType),
				operation.getOperationId() + "_container").init(JExpr._null());

		//public ObservableList<U> operationId(){
		JMethod retrieveMeth = cacheGroup.method(JMod.PUBLIC,
				cm.ref(ObservableList.class).narrow(elementType), operation.getOperationId());

		//if(container==null) synchronized(this){if(container==null){
		JBlock instanceBlock = retrieveMeth.body()._if(container.eqNull())._then().synchronizedBlock(JExpr._this()).body()
				._if(container.eqNull())._then();

		// container= FXCollections.observableArrayList();
		instanceBlock.assign(container, cm.ref(FXCollections.class).staticInvoke("observableArrayList"));

		// addFetchCacheArray(operationid,
		// JInvocation invoke =
		// instanceBlock.invoke(methFetchCacheArray).arg(operation.getOperationId());

		// }} return container;
		retrieveMeth.body()._return(container);
	}

	/**
	 * create a cache for a path that returns an array of items, the items having
	 * a unique field
	 */
	protected void createCacheArrayWithUnique(Operation operation, JMethod meth, Property responseSchema,
			AbstractJType elementType, List<String> requiredRoles, JDefinedClass cacheGroup, String uniqFieldName) {
		// we have one response field that is unique. The cache is thus a
		// map<fieldtype, elementType>.

	}

	protected void createCacheObject(Operation operation, JMethod meth, AbstractJType retType,
			List<String> requiredRoles, JDefinedClass cacheGroup) {
		JVar container = cacheGroup.field(JMod.PRIVATE, cm.ref(SimpleObjectProperty.class).narrow(retType),
				operation.getOperationId() + "_container").init(JExpr._null());
		JMethod retrieveMeth = cacheGroup.method(JMod.PUBLIC,
				cm.ref(javafx.beans.property.Property.class).narrow(retType), operation.getOperationId());
		JBlock instanceBlock = retrieveMeth.body()._if(container.eqNull())._then().synchronizedBlock(JExpr._this()).body()
				._if(container.eqNull())._then();
		instanceBlock.assign(container, JExpr._new(cm.ref(SimpleObjectProperty.class).narrowEmpty()));
		JInvocation invoke = instanceBlock.invoke(methFetchCacheObject).arg(operation.getOperationId());
		invoke.arg(JExpr.direct("m->swagger." + meth.name() + "(m)"));
		invoke.arg(JExpr.direct(container.name() + "::set"));
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		retrieveMeth.body()._return(container);
	}

	protected void createCacheObject(Operation operation, JMethod meth, AbstractJType retType,
			List<String> requiredRoles, JDefinedClass containerClass, JVar parameter) {
		AbstractJClass mapKeyType = parameter.type().boxify();
		JVar container = containerClass.field(JMod.PRIVATE,
				cm.ref(HashMap.class).narrow(mapKeyType, cm.ref(SimpleObjectProperty.class).narrow(retType)),
				operation.getOperationId() + "_container").init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
		JMethod retrieveMeth = containerClass.method(JMod.PUBLIC,
				cm.ref(javafx.beans.property.Property.class).narrow(retType), operation.getOperationId());
		JVar param = retrieveMeth.param(parameter.type(), parameter.name());
		JVar holder = retrieveMeth.body().decl(cm.ref(SimpleObjectProperty.class).narrow(retType), "holder");
		holder.init(container.invoke("get").arg(param));
		JBlock instanceBlock = retrieveMeth.body()._if(holder.eqNull())._then().synchronizedBlock(container).body()
				.assign(holder, container.invoke("get").arg(param))._if(holder.eqNull())._then();
		instanceBlock.assign(holder, JExpr._new(cm.ref(SimpleObjectProperty.class).narrowEmpty()));
		instanceBlock.invoke(container, "put").arg(param).arg(holder);
		JVar holder2 = instanceBlock.decl(cm.ref(SimpleObjectProperty.class).narrow(retType), "holderf").init(holder);
		JInvocation invoke = instanceBlock.invoke(methFetchCacheObject).arg(operation.getOperationId());
		invoke.arg(JExpr.direct("m->swagger." + meth.name() + "(" + param.name() + ", m)"));
		invoke.arg(JExpr.direct("o-> {synchronized(" + holder2.name() + "){" + holder2.name() + ".set(o);}}"));
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		retrieveMeth.body()._return(holder);
	}

	protected Map<String, JDefinedClass> cacheGroupClasses = new HashMap<>();

	/**
	 * get the group cache class for given group name. also create a field of
	 * given type in the {@link #cacheClassName} class .
	 *
	 * <br />
	 * eg calling this with "bla" will create a Bla class in the
	 * {@link #cacheClassName} class, as well as a field
	 * <code>public final bla = new Bla();</code> in it.
	 *
	 * @param groupName
	 * @return
	 */
	public JDefinedClass getCacheGroupClass(String groupName) {
		JDefinedClass ret = cacheGroupClasses.get(groupName);
		if (ret == null) {
			try {
				ret = cacheClass._class(JMod.PUBLIC, groupName.substring(0, 1).toUpperCase() + groupName.substring(1));
				cacheGroupClasses.put(groupName, ret);
				// need to make direct call or the generated class is ugly(makes
				// reference to the enclosing unparametrized class)
				JDirectClass direct = cm.directClass(ret.name());
				cacheClass.field(JMod.PUBLIC | JMod.FINAL, direct, groupName).init(JExpr._new(direct));
			} catch (JClassAlreadyExistsException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return ret;
	}

}
