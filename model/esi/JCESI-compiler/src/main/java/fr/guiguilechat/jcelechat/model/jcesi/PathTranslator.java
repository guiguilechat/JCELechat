package fr.guiguilechat.jcelechat.model.jcesi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JArrayClass;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JLambda;
import com.helger.jcodemodel.JLambdaParam;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPrimitiveType;
import com.helger.jcodemodel.JSynchronizedBlock;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsMapHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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

	protected final Operation operation;
	protected final OpType optype;
	protected final String path;
	protected final ClassBridge bridge;

	protected final JCodeModel cm;

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

	protected Response response;
	protected Property responseSchema;
	/**
	 * the type of resource produced by the http fetch. if the fetch actually
	 * produces an array, this is the item type of the array, eg int[] will
	 * resolve to int.
	 */
	protected AbstractJType resourceType;
	/**
	 * return type of the fetch method.
	 */
	protected AbstractJType fetchRetType;
	protected JMethod fetchMeth;

	protected List<String> requiredRoles;

	@SuppressWarnings("unchecked")
	public void apply() {
		if (operation == null) {
			return;
		}
		response = ESICompiler.getResponse(operation);
		if (response == null) {
			logger.error("can't find response for path " + path + " " + optype);
			return;
		}
		responseSchema = response.getSchema();

		// get the existing class for this response.
		fetchRetType = bridge.getReponseClass(responseSchema);
		if (fetchRetType instanceof JArrayClass) {
			resourceType = ((JArrayClass) fetchRetType).elementType();
		} else {
			resourceType = fetchRetType;
		}
		String fetchMethName = operation.getOperationId();
		for (Parameter p : operation.getParameters()) {
			fetchMethName = fetchMethName.replaceAll(p.getName(), "");
		}
		fetchMethName = fetchMethName.replaceAll("__", "_").replaceAll("_$", "");
		findConnected();
		// create the method
		fetchMeth = (connected ? bridge.swaggerCOClass : bridge.swaggerDCClass).method(JMod.PUBLIC | JMod.DEFAULT,
				fetchRetType, fetchMethName);
		// add the parameters
		extractFetchParameters();

		requiredRoles = operation.getVendorExtensions().containsKey("x-required-roles")
				? (List<String>) operation.getVendorExtensions().get("x-required-roles")
						: Collections.emptyList();

				addPathJavadoc();

				headerParam = fetchMeth.param(bridge.headerhandlertype(), headerHandlerName);

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
				JVar url = fetchMeth.body().decl(cm.ref(String.class), "url");
				url.init(JExpr.direct(urlAssign));
				switch (optype) {
				case post:
				case put:
					String methName = optype == OpType.post ? "connectPost" : "connectPut";
					JVar content = null;
					if (!bodyparameters.isEmpty()) {
						content = fetchMeth.body().decl(cm.ref(Map.class).narrow(cm.ref(String.class)).narrow(cm.ref(Object.class)),
								"content");
						content.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
						for (JVar p : bodyparameters) {
							fetchMeth.body().directStatement("content.put(\"" + p.name() + "\", " + p.name() + ");");
						}
					}
					if (responseSchema == null) {
						fetchMeth.body().add(JExpr.invoke(methName).arg(url)
								.arg(content == null ? cm.ref(Collections.class).staticInvoke("emptyMap") : content).arg(headerParam));
					} else {
						JVar fetched = fetchMeth.body().decl(cm.ref(String.class), "fetched");
						fetched.init(JExpr.invoke(methName).arg(url)
								.arg(content == null ? cm.ref(Collections.class).staticInvoke("emptyMap") : content).arg(headerParam));
					}
					break;
				case get:
					fetchMeth.body().directStatement("String fetched=" + "connectGet(url," + headerHandlerName + ");");
					createCache();
					break;
				case delete:
					fetchMeth.body().directStatement("connectDel(url," + headerHandlerName + ");");
					break;
				default:
					throw new UnsupportedOperationException("unsupported type " + optype + " for path " + path);
				}
				if (responseSchema != null) {
					fetchMeth.body()._return(
							JExpr.invoke("convert").arg(JExpr.direct("fetched")).arg(JExpr.direct(fetchRetType.binaryName() + ".class")));
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

	protected void findConnected() {
		connected = operation.getParameters().stream().filter(p -> p.getName().equals("token")).findAny().isPresent();
	}

	/** argument of the fetch method for the header handler */
	JVar headerParam;

	/**
	 * extract the parameters from an operation and put them in corresponding
	 * list. also add javadoc on the method for those parameters.
	 */
	protected void extractFetchParameters() {
		for (Parameter p : operation.getParameters()) {
			switch (p.getName()) {
			// we skip following parameters
			case "token":
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
					fetchMeth.javadoc().addParam(p.getName()).add(p.getDescription());
					PathParameter pp = (PathParameter) p;
					pt = bridge.getExistingClass(pp.getType(), pp.getName(), pp.getFormat(), pp.getEnum());
					if (!pp.getRequired() && pt.isPrimitive()) {
						pt = pt.boxify();
					}
					param = fetchMeth.param(pt, pp.getName());
					pathparameters.add(param);
					allParams.add(param);
					break;
				case "query":
					fetchMeth.javadoc().addParam(p.getName()).add(p.getDescription());
					QueryParameter qp = (QueryParameter) p;
					param = fetchMeth.param(bridge.getExistingClass(qp), qp.getName());
					queryparameters.add(param);
					allParams.add(param);
					break;
				case "body":
					BodyParameter bp = (BodyParameter) p;
					Model schema = bp.getSchema();
					if (schema instanceof ArrayModel) {
						fetchMeth.javadoc().addParam(p.getName()).add(p.getDescription());
						pt = bridge.getExistingClass((ArrayModel) schema);
						param = fetchMeth.param(pt, bp.getName());
						bodyparameters.add(param);
						allParams.add(param);
					} else {
						for (Entry<String, Property> e : schema.getProperties().entrySet()) {
							fetchMeth.javadoc().addParam(e.getKey()).add(e.getValue().getDescription());
							AbstractJType type = bridge.translateToClass(e.getValue(), bridge.structurePackage, e.getKey());
							param = fetchMeth.param(type, e.getKey());
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
		fetchMeth.javadoc().append("" + operation.getSummary());
		fetchMeth.javadoc().add(("\n<p>\n" + ("" + operation.getDescription()).replaceAll("---", "<br />") + "\n</p>")
				.replaceAll("\n\n", "\n").replaceAll("<br />\n<", "<").replaceAll("\n<br />\n", "<br />\n"));
		if (!requiredRoles.isEmpty()) {
			if (!requiredRoles.isEmpty()) {
				JFieldVar rolesfield = (connected ? bridge.swaggerCOClass : bridge.swaggerDCClass).field(
						JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String.class).array(),
						(operation.getOperationId() + "_roles").toUpperCase());
				JArray array = JExpr.newArray(cm.ref(String.class));
				for (String role : requiredRoles) {
					array.add(JExpr.lit(role));
				}
				rolesfield.init(array);
				rolesfield.javadoc().add("the roles required for {@link #" + fetchMeth.name() + " this method}");
				fetchMeth.javadoc().add("\n<p>\nrequire the roles specified {@link #" + rolesfield.name() + " here}\n</p>");
			}
		}
	}

	////
	// creation of cache method based on fetch method
	////

	protected JDefinedClass cacheGroup;

	protected List<JVar> cacheParams = new ArrayList<>();

	protected AbstractJType cacheRetType;
	protected AbstractJType cacheHolderType;

	protected JMethod cacheMeth;

	/**
	 * how to handle the result of the fetch method in the cache method :
	 * Container puts in it a property, list put all the results in an observable
	 * list, and map puts the results in a map , with the key being specified by
	 * {@link #cacheRetUniqueField}
	 */
	protected static enum RETURNTYPE {
		CONTAINER, LIST, MAP
	}

	protected RETURNTYPE cacheRetTransform;

	protected AbstractJType cacheKeyType;
	protected JVar cacheContainer;
	protected JVar cacheParam;

	/** java keywords we can't use as a name */
	public static final Set<String> keywords = Collections.unmodifiableSet(new HashSet<>(
			Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
					"continue", "default", "do", "double", "else", "extends", "false", "final", "finally", "float", "for", "goto",
					"if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package",
					"private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized",
					"this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while")));

	protected void createCache() {
		String fieldName = operation.getOperationId().split("_")[1];
		cacheGroup = bridge.getCacheGroupClass(fieldName, connected);

		// first we need to know the result.
		if (responseSchema.getType().equals(ArrayProperty.TYPE)) {
			findCacheRetUniqueField();
			if (cacheRetUniqueField != null) {
				cacheRetType = cm.ref(ObsMapHolder.class).narrow(cacheRetUniqueField.type().boxify(), resourceType.boxify());
				cacheHolderType = cm.ref(ObservableMap.class).narrow(cacheRetUniqueField.type().boxify(),
						resourceType.boxify());
				cacheRetTransform = RETURNTYPE.MAP;
			} else {
				cacheRetType = cm.ref(ObsListHolder.class).narrow(resourceType.boxify());
				cacheHolderType = cm.ref(ObservableList.class).narrow(resourceType.boxify());
				cacheRetTransform = RETURNTYPE.LIST;
			}
		} else {
			cacheRetType = cm.ref(ObsObjHolder.class).narrow(resourceType.boxify());
			cacheHolderType = cm.ref(javafx.beans.property.Property.class).narrow(resourceType.boxify());
			cacheRetTransform = RETURNTYPE.CONTAINER;
		}

		String methName = operation.getOperationId().replaceAll("^get_", "").replaceAll("^" + fieldName + "_", "");

		for (JVar v : allParams) {
			methName = methName.replaceAll(v.name(), "");
		}
		methName = methName.replaceAll("__", "_").replaceAll("^_", "").replaceAll("_$", "");
		if (methName.length() < 2) {
			methName = "get";
		}

		if (keywords.contains(methName)) {
			methName = "get" + methName;
		}

		cacheMeth = cacheGroup.method(JMod.PUBLIC, cacheRetType, methName);
		cacheMeth.javadoc().add(operation.getDescription().split("---")[0]);
		cacheMeth.javadoc().add("cache over {@link Swagger#" + fetchMeth.name() + "}<br />");

		// after that we need to know the parameters

		for (JVar v : allParams) {
			if (!v.name().equals(headerHandlerName) && !v.name().equals("page")) {
				cacheParams.add(cacheMeth.param(v.type(), v.name()));
				Parameter vp = operation.getParameters().stream().filter(p -> p.getName().equals(v.name())).findFirst()
						.orElse(null);
				if (vp != null) {
					cacheMeth.javadoc().addParam(v).add(vp.getDescription());
				}
			}
		}

		switch (cacheParams.size()) {
		case 0:
			cacheKeyType = null;
			switch (cacheRetTransform) {
			case CONTAINER:
				createCache_NoParam_Container();
				break;
			case LIST:
				createCache_NoParam_List();
				break;
			case MAP:
				createCache_NoParam_Map();
				break;
			default:
				throw new UnsupportedOperationException("handle case " + cacheRetTransform);
			}
			break;
		case 1:
			cacheKeyType = cacheParams.get(0).type().boxify();
			cacheContainer = cacheGroup.field(JMod.PRIVATE | JMod.FINAL,
					cm.ref(Map.class).narrow(cacheKeyType).narrow(cacheRetType), operation.getOperationId() + "_holder")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
			cacheParam = cacheParams.get(0);
			switch (cacheRetTransform) {
			case CONTAINER:
				createCache_Param_Container();
				break;
			case LIST:
				createCache_Param_List();
				break;
			case MAP:
				createCache_Param_Map();
				break;
			default:
				throw new UnsupportedOperationException("handle case " + cacheRetTransform);
			}
			break;
		default:
			cacheKeyType = makeKeyParam(cacheParams);
			cacheContainer = cacheGroup.field(JMod.PRIVATE | JMod.FINAL,
					cm.ref(Map.class).narrow(cacheKeyType).narrow(cacheRetType), operation.getOperationId() + "_holder")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
			cacheParam = cacheMeth.body().decl(cacheKeyType, "param");
			JMethod cons = ((JDefinedClass) cacheKeyType).constructors().next();
			JInvocation callNew = JExpr._new(cacheKeyType);
			for (JVar b : cons.params()) {
				callNew = callNew.arg(b);
			}
			cacheParam.init(callNew);
			switch (cacheRetTransform) {
			case CONTAINER:
				createCache_Param_Container();
				break;
			case LIST:
				createCache_Param_List();
				break;
			case MAP:
				createCache_Param_Map();
				break;
			default:
				throw new UnsupportedOperationException("handle case " + cacheRetTransform);
			}
		}
	}

	/**
	 * if the returned type of fetch is an array, the field of the subtype that
	 * should be used as key for the cache return map. Null if returns is not an
	 * array, or if 0 ore more than one fields are designated to be unique.
	 */
	protected JFieldVar cacheRetUniqueField = null;

	/** find out the correct field to identify a result of the fetch */
	protected void findCacheRetUniqueField() {
		List<String> uniqueFields = new ArrayList<>();
		// we only have fields for reponses that are of type Object[], eg int[]
		// can't have a unique field, nor Object[][]
		ArrayProperty ap = (ArrayProperty) responseSchema;
		if (ap.getItems().getType().equals(ObjectProperty.TYPE)) {
			ObjectProperty op = (ObjectProperty) ap.getItems();
			for (Entry<String, Property> esp : op.getProperties().entrySet()) {
				if (esp.getValue().getDescription().toLowerCase().startsWith("unique ")) {
					uniqueFields.add(esp.getKey());
					// System.err.println("accepted description " +
					// esp.getValue().getDescription());
				}
			}
		}
		if (uniqueFields.size() == 1) {
			String name = uniqueFields.get(0);
			AbstractJType fetchSub = ((JArrayClass) fetchRetType).elementType();
			if (fetchSub instanceof JDefinedClass) {
				cacheRetUniqueField = ((JDefinedClass) fetchSub).fields().get(name);
			} else {
				logger.error("can't manage type " + fetchSub);
			}
		}
		if (uniqueFields.size() > 0) {
			// System.err.println("path " + path + " response unique=" +
			// uniqueFields);
		}
	}

	protected JDefinedClass makeKeyParam(List<JVar> cacheParams2) {
		JDefinedClass ret = bridge
				.getCacheKeyClass(cacheParams2.stream().collect(Collectors.toMap(JVar::name, JVar::type)));
		ret.javadoc().append("@see " + path + "\n");
		return ret;
	}

	/**
	 * create the cache body when cache has no parameter and the fetch method
	 * returns an object
	 */
	protected void createCache_NoParam_Container() {
		cacheContainer = cacheGroup.field(JMod.PRIVATE, cm.ref(ObsObjHolder.class).narrow(resourceType.boxify()),
				operation.getOperationId() + "_holder");
		JBlock instanceBlock = sync(cacheMeth.body()._if(cacheContainer.eqNull())._then(), JExpr._this()).body()
				._if(cacheContainer.eqNull())._then();
		JVar holder = instanceBlock.decl(cm.ref(SimpleObjectProperty.class).narrow(resourceType.boxify()), "holder")
				.init(JExpr._new(cm.ref(SimpleObjectProperty.class).narrowEmpty()));
		instanceBlock.assign(cacheContainer, JExpr.invoke(JExpr.direct("cache"), "toHolder").arg(holder));
		JInvocation invoke = instanceBlock.invoke(JExpr.direct("cache"), bridge.methFetchCacheObject())
				.arg(operation.getOperationId());
		invoke.arg(lambdaFetch());
		JLambda lambdaset = new JLambda();
		JLambdaParam item = lambdaset.addParam("item");
		sync(lambdaset.body(), holder).body().invoke(holder, "set").arg(item);
		invoke.arg(lambdaset);
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(cacheContainer);
	}

	/**
	 * create a synchronized(expr) surounded by a call to barker.tak(expr) and a
	 * call to barker.rel(expr)
	 *
	 * @param parent
	 * @param expr
	 * @return
	 */
	protected JSynchronizedBlock sync(JBlock parent, IJExpression expr) {
		parent.invoke(cm.ref(LockWatchDog.class).staticRef("BARKER"), "tak").arg(expr);
		JSynchronizedBlock ret = parent.synchronizedBlock(expr);
		ret.body().invoke(cm.ref(LockWatchDog.class).staticRef("BARKER"), "hld").arg(expr);
		parent.invoke(cm.ref(LockWatchDog.class).staticRef("BARKER"), "rel").arg(expr);
		return ret;
	}

	/**
	 * create the cache body when no parameter and the fetch method returns a list
	 * of items with no unique field.
	 */
	protected void createCache_NoParam_List() {
		cacheContainer = cacheGroup.field(JMod.PRIVATE, cacheRetType, operation.getOperationId() + "_holder");
		JBlock instanceBlock = sync(cacheMeth.body()._if(cacheContainer.eqNull())._then(), JExpr._this()).body()
				._if(cacheContainer.eqNull())._then();
		// _holder = FXCollections.observableArrayList();
		JVar holder = instanceBlock.decl(cacheHolderType, "holder")
				.init(cm.ref(FXCollections.class).staticInvoke("observableArrayList"));
		instanceBlock.assign(cacheContainer, JExpr.invoke(JExpr.direct("cache"), "toHolder").arg(holder));
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(cacheContainer);
		JInvocation invoke = instanceBlock.invoke(JExpr.direct("cache"), bridge.methFetchCacheArray())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());

		JLambda lambdaSet = new JLambda();
		JLambdaParam arr = lambdaSet.addParam("arr");
		JBlock setBody = sync(lambdaSet.body(), holder).body();
		setBody.invoke(holder, "setAll").arg(arr);
		setBody.invoke(finalRet, "dataReceived");
		invoke.arg(lambdaSet);
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(cacheContainer);
	}

	/**
	 * create the lambda method to call the fetch inside the cache method.<br />
	 * The result is transformed into an array of Integer if the fetch returns
	 * integer[]. The lambda takes an additional page parameter iff the
	 * transformation is not into a container (list or map)
	 *
	 * @return
	 */
	protected JLambda lambdaFetch() {
		JLambda lambdaFetch = new JLambda();
		Map<String, IJExpression> paramsByName = new HashMap<>();
		if (cacheRetTransform != RETURNTYPE.CONTAINER) {
			JLambdaParam page = lambdaFetch.addParam("page");
			paramsByName.put(page.name(), page);
		}
		JLambdaParam head = lambdaFetch.addParam("headerHandler");
		paramsByName.put(head.name(), head);
		for (JVar p : cacheParams) {
			paramsByName.put(p.name(), p);
		}
		JInvocation callmeth = JExpr.direct("cache.swagger").invoke(fetchMeth);
		for (JVar v : fetchMeth.params()) {
			IJExpression v2 = paramsByName.get(v.name());
			if (v2 == null) {
				System.err.println("getting arg " + v.name() + " from " + paramsByName);
			}
			callmeth.arg(v2);
		}
		if (cacheRetTransform != RETURNTYPE.CONTAINER && resourceType.isPrimitive()) {
			IJExpression convert, toArr;
			AbstractJClass streamType;
			if (resourceType == cm.INT) {
				convert = JExpr.direct("Integer::valueOf");
				toArr = JExpr.direct("Integer[]::new");
				streamType = cm.ref(IntStream.class);
			} else if (resourceType == cm.LONG) {
				convert = JExpr.direct("Long::valueOf");
				toArr = JExpr.direct("Long[]::new");
				streamType = cm.ref(LongStream.class);
			} else if (resourceType == cm.DOUBLE) {
				convert = JExpr.direct("Double::valueOf");
				toArr = JExpr.direct("Double[]::new");
				streamType = cm.ref(DoubleStream.class);
			} else {
				throw new UnsupportedOperationException("handle type " + resourceType);
			}
			callmeth = streamType.staticInvoke("of").arg(callmeth).invoke("mapToObj").arg(convert).invoke("toArray")
					.arg(toArr);
		}
		lambdaFetch.body().add(callmeth);
		return lambdaFetch;
	}

	/** method is fetch()-> map (param, rettype) */
	protected void createCache_NoParam_Map() {
		cacheContainer = cacheGroup.field(JMod.PRIVATE, cacheRetType, operation.getOperationId() + "_holder");
		JBlock instanceBlock = sync(cacheMeth.body()._if(cacheContainer.eqNull())._then(), JExpr._this()).body()
				._if(cacheContainer.eqNull())._then();
		// _holder = FXCollections.observableHashMap();
		JVar holder = instanceBlock.decl(cacheHolderType, "holder")
				.init(cm.ref(FXCollections.class).staticInvoke("observableHashMap"));
		instanceBlock.assign(cacheContainer, JExpr.invoke(JExpr.direct("cache"), "toHolder").arg(holder));
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(cacheContainer);
		JInvocation invoke = instanceBlock.invoke(JExpr.direct("cache"), bridge.methFetchCacheArray())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());

		JLambda lambdaSet = new JLambda();
		JLambdaParam arr = lambdaSet.addParam("arr");
		JBlock setBody = sync(lambdaSet.body(), holder).body();
		// linkedhashmap<key, resource> newmap = new linkedhashmap<>();
		JVar newmap = setBody
				.decl(cm.ref(LinkedHashMap.class).narrow(cacheRetUniqueField.type()).narrow(resourceType), "newmap")
				.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));
		// for (val : arr) newmap.put(val.unique, val)
		setBody.forEach(resourceType, "val", arr).body().invoke(newmap, "put")
		.arg(JExpr.direct("val." + cacheRetUniqueField.name())).arg(JExpr.direct("val"));
		// container.entrySet().retainAll(newMap.entrySet())
		setBody.add(JExpr.invoke(holder, "keySet").invoke("retainAll").arg(newmap.invoke("keySet")));
		// container.putAll(newmap)
		setBody.invoke(holder, "putAll").arg(newmap);
		setBody.invoke(finalRet, "dataReceived");
		invoke.arg(lambdaSet);

		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}

		cacheMeth.body()._return(cacheContainer);
	}

	/**
	 * Create the cache body when only one parameter and return a container of
	 * resourcetype.<br />
	 * Typically produces a map<cachekey, cacheret>
	 */
	protected void createCache_Param_Container() {
		JVar ret = cacheMeth.body().decl(cacheRetType, "ret");
		JBlock instanceBlock = createTestNullCase(cacheMeth.body(), ret, cacheContainer.invoke("get").arg(cacheParam),
				cacheContainer);
		JVar holder = instanceBlock.decl(cm.ref(SimpleObjectProperty.class).narrow(resourceType), "holder")
				.init(JExpr._new(cm.ref(SimpleObjectProperty.class).narrowEmpty()));
		instanceBlock.assign(ret, JExpr.invoke(JExpr.direct("cache"), "toHolder").arg(holder));
		instanceBlock.invoke(cacheContainer, "put").arg(cacheParam).arg(ret);
		JInvocation invoke = instanceBlock.invoke(JExpr.direct("cache"), bridge.methFetchCacheObject())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());

		JLambda lambdaset = new JLambda();
		JLambdaParam item = lambdaset.addParam("item");
		sync(lambdaset.body(), holder).body().invoke(holder, "set").arg(item);
		invoke.arg(lambdaset);
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}

		cacheMeth.body()._return(ret);
	}

	/**
	 * create the following block : ret=getter; if(ret==null) synchronized(lock)
	 * {ret=getter; if(ret==null){BLOCK}}
	 *
	 * @return the block to assign value. in this block ret is null and we need to
	 *         create it.
	 */
	protected JBlock createTestNullCase(JBlock outBlock, JVar ret, IJExpression getter, JVar lock) {
		ret.init(getter);
		return sync(outBlock._if(ret.eqNull())._then(), lock).body().add(JExpr.assign(ret, getter))
				._if(ret.eqNull())._then();
	}

	/**
	 * create the cache body when only one parameter.
	 */
	protected void createCache_Param_List() {
		JVar ret = cacheMeth.body().decl(cacheRetType, "ret");
		JBlock instanceBlock = createTestNullCase(cacheMeth.body(), ret, cacheContainer.invoke("get").arg(cacheParam),
				cacheContainer);
		JVar holder = instanceBlock.decl(cacheHolderType, "holder")
				.init(cm.ref(FXCollections.class).staticInvoke("observableArrayList"));
		instanceBlock.assign(ret, JExpr.invoke(JExpr.direct("cache"), "toHolder").arg(holder));
		instanceBlock.invoke(cacheContainer, "put").arg(cacheParam).arg(ret);
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(ret);
		JInvocation invoke = instanceBlock.invoke(JExpr.direct("cache"), bridge.methFetchCacheArray())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());

		JLambda lambdaSet = new JLambda();
		JLambdaParam arr = lambdaSet.addParam("arr");
		JBlock setBody = sync(lambdaSet.body(), holder).body();
		setBody.invoke(holder, "setAll").arg(arr);
		setBody.invoke(finalRet, "dataReceived");
		invoke.arg(lambdaSet);
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(ret);
	}

	/**
	 * create the cache body when only one parameter and returns an array of
	 * resources with a unique field, so a map of this field to the resources.
	 */
	protected void createCache_Param_Map() {
		JVar ret = cacheMeth.body().decl(cacheRetType, "ret");
		JBlock instanceBlock = createTestNullCase(cacheMeth.body(), ret, cacheContainer.invoke("get").arg(cacheParam),
				cacheContainer);
		JVar holder = instanceBlock.decl(cacheHolderType, "holder")
				.init(cm.ref(FXCollections.class).staticInvoke("observableHashMap"));
		instanceBlock.assign(ret, JExpr.invoke(JExpr.direct("cache"), "toHolder").arg(holder));
		instanceBlock.invoke(cacheContainer, "put").arg(cacheParam).arg(ret);
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(ret);
		JInvocation invoke = instanceBlock.invoke(JExpr.direct("cache"), bridge.methFetchCacheArray())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());

		JLambda lambdaSet = new JLambda();
		JLambdaParam arr = lambdaSet.addParam("arr");
		JBlock setBody = sync(lambdaSet.body(), holder).body();
		// linkedhashmap<key, resource> newmap = new linkedhashmap<>();
		JVar newmap = setBody
				.decl(cm.ref(LinkedHashMap.class).narrow(cacheRetUniqueField.type()).narrow(resourceType), "newmap")
				.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));
		// for (val : arr) newmap.put(val.unique, val)
		setBody.forEach(resourceType, "val", arr).body().invoke(newmap, "put")
		.arg(JExpr.direct("val." + cacheRetUniqueField.name())).arg(JExpr.direct("val"));
		// container.entrySet().retainAll(newMap.entrySet())
		setBody.add(JExpr.invoke(holder, "keySet").invoke("retainAll").arg(newmap.invoke("keySet")));
		// container.putAll(newmap)
		setBody.invoke(holder, "putAll").arg(newmap);
		setBody.invoke(finalRet, "dataReceived");
		invoke.arg(lambdaSet);
		if (!requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(ret);
	}
}
