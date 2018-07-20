package fr.guiguilechat.eveonline.model.esi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPrimitiveType;
import com.helger.jcodemodel.JVar;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import v2.io.swagger.models.ArrayModel;
import v2.io.swagger.models.Model;
import v2.io.swagger.models.Operation;
import v2.io.swagger.models.Response;
import v2.io.swagger.models.parameters.BodyParameter;
import v2.io.swagger.models.parameters.Parameter;
import v2.io.swagger.models.parameters.PathParameter;
import v2.io.swagger.models.parameters.QueryParameter;
import v2.io.swagger.models.properties.ArrayProperty;
import v2.io.swagger.models.properties.ObjectProperty;
import v2.io.swagger.models.properties.Property;

public class PathTranslator {

	private static final Logger logger = LoggerFactory.getLogger(PathTranslator.class);

	private final Operation operation;
	private final OpType optype;
	private final String path;
	private final ClassBridge bridge;

	private final JCodeModel cm;

	public static enum OpType {
		get, post, put, delete;
	};

	protected String headerHandlerName = "headerHandler";

	public PathTranslator(Operation operation, OpType optype, String path, ClassBridge bridge) {
		this.operation = operation;
		this.optype = optype;
		this.path = path;
		this.bridge = bridge;
		cm = bridge.cm;

	}

	private Response response;
	private AbstractJType retType;
	private JMethod meth;

	private List<String> requiredRoles;

	@SuppressWarnings("unchecked")
	public void apply() {
		if (operation == null) {
			return;
		}
		response = Compiler.getResponse(operation);
		if (response == null) {
			logger.error("can't find response for path " + path + " " + optype);
			return;
		}
		Property s = response.getSchema();

		// get the existing class for this response.
		retType = bridge.getReponseClass(s);
		//create the method
		meth = bridge.swaggerClass.method(JMod.PUBLIC | JMod.DEFAULT, retType, operation.getOperationId());
		//add the parameters
		extractParameters();

		requiredRoles = operation.getVendorExtensions().containsKey("x-required-roles")
				? (List<String>) operation.getVendorExtensions().get("x-required-roles")
						: Collections.emptyList();

				addPathJavadoc();

				headerParam = meth.param(bridge.headerhandlertype(), headerHandlerName);

				String urlAssign = "\"" + path + "\"";
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
						urlAssign += "+(" + qp.name() + "==null?\"\":\"&" + qp.name() + "=\"+flatten(" + qp.name() + "))";
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
						.arg(JExpr.lit(connected)).arg(headerParam);
					} else {
						JVar fetched = meth.body().decl(cm.ref(String.class), "fetched");
						fetched.init(JExpr.invoke(methName).arg(url)
								.arg(content == null ? cm.ref(Collections.class).staticInvoke("emptyMap") : content)
								.arg(JExpr.lit(connected)).arg(headerParam));
					}
					break;
				case get:
					meth.body().directStatement(
							"String fetched=" + "connectGet(url," + Boolean.toString(connected) + ", " + headerHandlerName + ");");
					createCache();
					break;
				case delete:
					meth.body().directStatement("connectDel(url," + Boolean.toString(connected) + ", " + headerHandlerName + ");");
					break;
				default:
					throw new UnsupportedOperationException("unsupported type " + optype + " for path " + path);
				}
				if (s != null) {
					meth.body()._return(
							JExpr.invoke("convert").arg(JExpr.direct("fetched")).arg(JExpr.direct(retType.binaryName() + ".class")));
				}
	}

	////
	// parameters management for the fetch method
	////

	/**
	 * path parameter that are passed as argument
	 */
	private List<JVar> pathparameters = new ArrayList<>();

	/**
	 * queryparameter that are passed as arguments
	 */
	private List<JVar> queryparameters = new ArrayList<>();

	/**
	 * body parameters that are passed as arguments
	 */
	private List<JVar> bodyparameters = new ArrayList<>();

	private List<JVar> allParams = new ArrayList<>();

	/** true iff the path requires connection */
	private boolean connected;

	/** argument of the fetch method for the header handler */
	JVar headerParam;

	/**
	 * extract the parameters from an operation and put them in corresponding
	 * list. also add javadoc on the method for those parameters.
	 */
	protected void extractParameters() {
		connected = false;
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
				break;
			default:
				String in = p.getIn();
				AbstractJType pt;
				JVar param;
				switch (in) {
				case "path":
					meth.javadoc().addParam(p.getName()).add(p.getDescription());
					PathParameter pp = (PathParameter) p;
					pt = bridge.getExistingClass(pp.getType(), pp.getName(), pp.getFormat(), pp.getEnum());
					if (!pp.getRequired() && pt.isPrimitive()) {
						pt = pt.boxify();
					}
					param = meth.param(pt, pp.getName());
					pathparameters.add(param);
					allParams.add(param);
					break;
				case "query":
					meth.javadoc().addParam(p.getName()).add(p.getDescription());
					QueryParameter qp = (QueryParameter) p;
					param = meth.param(bridge.getExistingClass(qp), qp.getName());
					queryparameters.add(param);
					allParams.add(param);
					break;
				case "body":
					BodyParameter bp = (BodyParameter) p;
					Model schema = bp.getSchema();
					if (schema instanceof ArrayModel) {
						meth.javadoc().addParam(p.getName()).add(p.getDescription());
						pt = bridge.getExistingClass((ArrayModel) schema);
						param = meth.param(pt, bp.getName());
						bodyparameters.add(param);
						allParams.add(param);
					} else {
						for (Entry<String, Property> e : schema.getProperties().entrySet()) {
							meth.javadoc().addParam(e.getKey()).add(e.getValue().getDescription());
							AbstractJType type = bridge.translateToClass(e.getValue(), bridge.structurePackage, e.getKey());
							param = meth.param(type, e.getKey());
							bodyparameters.add(param);
							allParams.add(param);
						}
					}
					break;
				default:
					logger.error("no matching type " + p.getIn() + " for parameter " + p.getName() + " in operation "
							+ operation.getOperationId());
				}
			}
		}
	}

	protected void addPathJavadoc() {
		meth.javadoc().append("" + operation.getSummary());
		meth.javadoc().add(("\n<p>\n" + ("" + operation.getDescription()).replaceAll("---", "<br />") + "\n</p>")
				.replaceAll("\n\n", "\n").replaceAll("<br />\n<", "<").replaceAll("\n<br />\n", "<br />\n"));
		if (!requiredRoles.isEmpty()) {
			if (!requiredRoles.isEmpty()) {
				JFieldVar rolesfield = bridge.swaggerClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL,
						cm.ref(String.class).array(), (operation.getOperationId() + "_roles").toUpperCase());
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

	////
	// creation of cache method based on fetch method
	////

	protected void createCache() {
		String fieldName = operation.getOperationId().split("_")[1];
		JDefinedClass cacheGroup = bridge.getCacheGroupClass(fieldName);
		List<Parameter> remainingArgs = operation.getParameters().stream()
				.filter(p -> !p.getName().equals(headerHandlerName) && !p.getName().equals("page") && p.getRequired())
				.collect(Collectors.toList());
		if (remainingArgs.size() > 1) {
			logger.debug("skip obj met " + meth.name() + " : too many arguments to create a cache : "
					+ remainingArgs.stream().map(Parameter::getName).collect(Collectors.toList()));
			return;
		}
		if (retType.isArray()) {
			createCacheArray(operation, meth, ((ArrayProperty) response.getSchema()).getItems(), retType.elementType(),
					requiredRoles, cacheGroup, headerParam);
		} else {
			if (headerParam == null) {
				createCacheObject(operation, meth, retType, requiredRoles, cacheGroup);
			} else {
				createCacheObject(operation, meth, retType, requiredRoles, cacheGroup, headerParam);
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

			System.err.println("can't use more than one unique field + param " + meth.name() + " unique fields="
					+ uniqueFields + " params=" + parameter);
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

		// public ObservableList<U> operationId(){
		JMethod retrieveMeth = cacheGroup.method(JMod.PUBLIC, cm.ref(ObservableList.class).narrow(elementType),
				operation.getOperationId());

		// if(container==null) synchronized(this){if(container==null){
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

	protected void createCacheObject(Operation operation, JMethod meth, AbstractJType retType, List<String> requiredRoles,
			JDefinedClass cacheGroup) {
		JVar container = cacheGroup.field(JMod.PRIVATE, cm.ref(SimpleObjectProperty.class).narrow(retType),
				operation.getOperationId() + "_container").init(JExpr._null());
		JMethod retrieveMeth = cacheGroup.method(JMod.PUBLIC, cm.ref(javafx.beans.property.Property.class).narrow(retType),
				operation.getOperationId());
		JBlock instanceBlock = retrieveMeth.body()._if(container.eqNull())._then().synchronizedBlock(JExpr._this()).body()
				._if(container.eqNull())._then();
		instanceBlock.assign(container, JExpr._new(cm.ref(SimpleObjectProperty.class).narrowEmpty()));
		JInvocation invoke = instanceBlock.invoke(bridge.methFetchCacheObject()).arg(operation.getOperationId());
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

	protected void createCacheObject(Operation operation, JMethod meth, AbstractJType retType, List<String> requiredRoles,
			JDefinedClass containerClass, JVar parameter) {
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
		JInvocation invoke = instanceBlock.invoke(bridge.methFetchCacheObject()).arg(operation.getOperationId());
		invoke.arg(JExpr.direct("header->swagger." + meth.name() + "(" + param.name() + ", header)"));
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
}
