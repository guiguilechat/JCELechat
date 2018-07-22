package fr.guiguilechat.eveonline.model.esi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JArrayClass;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JDirectClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JTypeVar;
import com.helger.jcodemodel.JVar;

import v2.io.swagger.models.ArrayModel;
import v2.io.swagger.models.Path;
import v2.io.swagger.models.Response;
import v2.io.swagger.models.Swagger;
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

/**
 * bridge between a {@link Swagger} and the classes we create in a
 * {@link JCodeModel}
 */
public class ClassBridge {

	private static final Logger logger = LoggerFactory.getLogger(ClassBridge.class);

	public final JCodeModel cm;
	public final v2.io.swagger.models.Swagger swagger;

	protected JPackage responsePackage = null;
	protected JPackage structurePackage = null;
	protected JPackage keyPackage = null;

	protected String responsesPackageName = "responses";
	protected String structuresPackageName = "structures";
	protected String keysPackageName = "keys";
	protected JDefinedClass swaggerClass;

	public ClassBridge(JCodeModel cm, Swagger swagger) {
		this.cm = cm;
		this.swagger = swagger;

		try {
			swaggerClass = cm._class(rootPackage + "." + "Swagger", EClassType.INTERFACE);
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		createSwaggerCalls();

		responsePackage = cm._package(rootPackage + "." + responsesPackageName);
		structurePackage = cm._package(rootPackage + "." + structuresPackageName);
		keyPackage = cm._package(rootPackage + "." + keysPackageName);

		// first pass to fetch all the responses
		for (Path path : swagger.getPaths().values()) {
			addResponseType(Compiler.getResponse(path.getGet()));
			addResponseType(Compiler.getResponse(path.getPost()));
		}
		// then we merge all response types that have same structure.
		// this makes a map of renames
		mergeResponseTypes();

		try {
			cacheClass = cm._class(JMod.PUBLIC | JMod.ABSTRACT, rootPackage + "." + cacheClassName, EClassType.CLASS);
			createCacheMethods();
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("while creating cache classes and methods", e);
		}
	}

	private AbstractJClass headerhandlertype;

	public AbstractJClass headerhandlertype() {
		return headerhandlertype;
	}

	protected void createSwaggerCalls() {
		Set<String> allScopes = swagger.getPaths().values().stream().flatMap(p -> p.getOperations().stream())
				.filter(ope -> ope.getSecurity() != null).flatMap(ope -> ope.getSecurity().stream())
				.flatMap(m -> m.values().stream()).flatMap(l -> l.stream()).collect(Collectors.toSet());
		JFieldVar scopesField = swaggerClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String[].class),
				"SCOPES");
		JArray scopesinit = JExpr.newArray(cm.ref(String.class));
		for (String scope : allScopes) {
			scopesinit.add(JExpr.lit(scope));
		}
		scopesField.init(scopesinit);

		JMethod flatten = swaggerClass.method(JMod.PUBLIC, cm.ref(String.class), "flatten");
		flatten.param(cm.ref(Object.class), "o");

		headerhandlertype = cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(List.class).narrow(cm.ref(String.class)));

		JMethod coget = swaggerClass.method(JMod.PUBLIC, cm.ref(String.class), "connectGet");
		coget.param(cm.ref(String.class), "url");
		coget.param(cm.BOOLEAN, "connected");
		coget.param(headerhandlertype, "headerHandler");

		JMethod codel = swaggerClass.method(JMod.PUBLIC, cm.ref(String.class), "connectDel");
		codel.param(cm.ref(String.class), "url");
		codel.param(cm.BOOLEAN, "connected");
		codel.param(headerhandlertype, "headerHandler");

		JMethod copost = swaggerClass.method(JMod.PUBLIC, cm.ref(String.class), "connectPost");
		copost.param(cm.ref(String.class), "url");
		copost.param(cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(Object.class)), "content");
		copost.param(cm.BOOLEAN, "connected");
		copost.param(headerhandlertype, "headerHandler");

		JMethod coput = swaggerClass.method(JMod.PUBLIC, cm.ref(String.class), "connectPut");
		coput.param(cm.ref(String.class), "url");
		coput.param(cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(Object.class)), "content");
		coput.param(cm.BOOLEAN, "connected");
		coput.param(headerhandlertype, "headerHandler");

		JDirectClass genericType = cm.directClass("T");
		JMethod convert = swaggerClass.method(JMod.PUBLIC, genericType, "convert");
		convert.generify("T");
		convert.param(cm.ref(String.class), "line");
		convert.param(cm.ref(Class.class).narrow(genericType.wildcardExtends()), "clazz");
	}

	protected String rootPackage = Compiler.class.getPackage().getName() + ".compiled";

	////
	// response merging. Some responses have same structure, we merge them in a
	// first pass
	////

	protected HashMap<Map<String, String>, Set<String>> responseStructures = new HashMap<>();

	protected void addResponseType(Response r) {
		if (r == null) {
			return;
		}
		Property prop = r.getSchema();
		ObjectProperty op = getPropertyObject(prop);
		if (op != null) {
			registerResponseType(prop.getTitle(), op);
			for (Property subprop : op.getProperties().values()) {
				ObjectProperty subop = getPropertyObject(subprop);
				if (subop != null) {
					registerResponseType(subprop.getTitle(), subop);
				}
			}
		}
	}

	protected void registerResponseType(String name, ObjectProperty structure) {
		Map<String, String> classDef = structure.getProperties().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue().getType()));
		Set<String> set = responseStructures.get(classDef);
		if (set == null) {
			set = new HashSet<>();
			responseStructures.put(classDef, set);
		}
		set.add(name);
	}

	/**
	 * get the {@link ObjectProperty} at first or second level. Some responses
	 * return an object, other an array : in both case we return the object.
	 *
	 * @param s
	 * @return the corresponding object property, or null.
	 */
	protected ObjectProperty getPropertyObject(Property s) {
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

	protected HashMap<String, String> structureRenames = new HashMap<>();

	protected void mergeResponseTypes() {
		for (Entry<Map<String, String>, Set<String>> e : responseStructures.entrySet()) {
			String newName = mergeClassesNames(e.getKey(), e.getValue());
			for (String oldName : e.getValue()) {
				structureRenames.put(oldName, newName);
			}
		}
	}

	/**
	 * try find a common name for several classes that have same structure.
	 * <p>
	 * first try to use common parts.<br />
	 * ex R_get_bla_bli and R_get_bla_blo will result in R_get_bla.
	 * </p>
	 * <p>
	 * then if too small, add in the structure of the class<br />
	 * ex {i:int j:char} will be translated in iint_jchar and appended to the
	 * name.
	 * </p>
	 *
	 * @param classDef
	 *          the structural definition of the classes
	 * @param names
	 *          the list of names of classes that have this structure
	 * @return the common name to use
	 */
	protected String mergeClassesNames(Map<String, String> classDef, Set<String> names) {
		if (names.size() == 1) {
			return ("R_" + names.iterator().next()).replaceAll("_ok", "");
		}
		ArrayList<String> tokens = new ArrayList<>(Arrays.asList(names.iterator().next().split("_")));
		for (String name : names) {
			tokens.retainAll(Arrays.asList(name.split("_")));
		}
		tokens.removeIf(s -> s.equals("id") || s.equals("R") || s.equals("ok"));
		tokens.add(0, "M");
		tokens.add("" + classDef.size());
		String common = tokens.stream().collect(Collectors.joining("_"));
		if (tokens.size() < 3) {
			common += "_" + classDef.entrySet().stream().map(e -> e.getKey() + e.getValue()).collect(Collectors.joining("_"));
		}
		logger.debug("merging " + names + " into " + common);
		return common;
	}

	////
	// translate swagger properties to class
	////

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
	public AbstractJType translateToClass(Property p, JPackage pck, String name) {
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

	public AbstractJType getExistingClass(String type, String name, String format, List<String> enums) {
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
			ret = swaggerClass._enum(JMod.PUBLIC | JMod.STATIC, name);
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
			for (JDefinedClass cl : swaggerClass.classes()) {
				if (cl.name().equals(name)) {
					return cl;
				}
			}
			return null;
		}
	}

	public AbstractJType getExistingClass(ArrayModel model) {
		return translateToClass(model.getItems(), structurePackage, model.getTitle()).array();
	}

	public AbstractJType getExistingClass(QueryParameter pp) {
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

	protected HashMap<Map<String, String>, JDefinedClass> createdClasses = new HashMap<>();

	protected JDefinedClass translateToClass(ObjectProperty p, JPackage pck, String name) {
		Map<String, String> classDef = p.getProperties().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue().getType()));
		JDefinedClass createdClass = createdClasses.get(classDef);
		if (createdClass != null) {
			return createdClass;
		}
		try {
			JDefinedClass cl = pck._class(name.replaceAll("_ok", ""));
			// if (createdClass != null) {
			// cl._extends(createdClass);
			// } else {
			for (Entry<String, Property> e : p.getProperties().entrySet()) {
				Property prop = e.getValue();
				JFieldVar field = cl.field(JMod.PUBLIC,
						translateToClass(prop, pck, structureRenames.getOrDefault(prop.getTitle(), prop.getTitle())), e.getKey());
				field.javadoc().add(prop.getDescription());
			}
			createdClasses.put(classDef, cl);
			// }
			return cl;
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	protected AbstractJClass translateToClass(ArrayProperty p, JPackage pck, String name) {
		AbstractJType arraCl = translateToClass(p.getItems(), pck, name);
		return arraCl.array();
	}

	public AbstractJType getReponseClass(Property s) {
		if (s == null) {
			return cm.VOID;
		} else {
			String className = structureRenames.get(s.getTitle());
			return translateToClass(s, responsePackage, className);
		}
	}

	////
	// cache generation classes
	////

	protected String cacheClassName = "SwaggerCache";

	private JDefinedClass cacheClass;

	public JDefinedClass cacheClass() {
		return cacheClass;
	}

	private JMethod methFetchCacheObject;

	public JMethod methFetchCacheObject() {
		return methFetchCacheObject;
	}

	private JMethod methFetchCacheArray;

	public JMethod methFetchCacheArray() {
		return methFetchCacheArray;
	}

	public void createCacheMethods() throws JClassAlreadyExistsException {
		JTypeVar cacheMainType = cacheClass.generify("T", swaggerClass);
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
			methFetchCacheArray = cacheClass.method(JMod.PUBLIC | JMod.ABSTRACT, pausable, "addFetchCacheArray");
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
					cm.ref(Function.class)
					.narrow(cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(List.class).narrow(String.class)), typeVar),
					"fetcher");
			methFetchCacheObject.param(cm.ref(Consumer.class).narrow(typeVar), "cacheHandler");
			methFetchCacheObject.varParam(cm.ref(String.class), "requiredRoles");
		}
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
				ret = cacheClass()._class(JMod.PUBLIC, groupName.substring(0, 1).toUpperCase() + groupName.substring(1));
				cacheGroupClasses.put(groupName, ret);
				// need to make direct call or the generated class is ugly(makes
				// reference to the enclosing unparametrized class)
				JDirectClass direct = cm.directClass(ret.name());
				cacheClass().field(JMod.PUBLIC | JMod.FINAL, direct, groupName).init(JExpr._new(direct));
			} catch (JClassAlreadyExistsException e) {
				throw new UnsupportedOperationException("while getting cache group for " + groupName, e);
			}
		}
		return ret;
	}

	////
	// cache key classes
	////

	Map<Map<String, AbstractJType>, JDefinedClass> existingKeyTypes = new HashMap<>();

	public JDefinedClass getCacheKeyClass(Map<String, AbstractJType> map) {
		JDefinedClass ret = existingKeyTypes.get(map);
		if (ret == null) {
			synchronized (existingKeyTypes) {
				if (ret == null) {
					try {
						String className = "K_" + existingKeyTypes.size();
						for (AbstractJType v : map.values()) {
							className += "_" + compilableName(v);
						}
						ret = keyPackage._class(className);

						JMethod methcons = ret.constructor(JMod.PUBLIC);

						JMethod methEq = ret.method(JMod.PUBLIC, cm.BOOLEAN, "equals");
						JVar methOther = methEq.param(cm.ref(Object.class), "other");
						// if o==this reutrn true;
						methEq.body()._if(methOther.eq(JExpr._this()))._then()._return(JExpr.TRUE);
						// if o==null || o.getclass!=this.getClass return false;
						methEq.body()._if(methOther.eqNull().cor(methOther.invoke("getClass").ne(JExpr._this().invoke("getClass"))))
						._then()._return(JExpr.FALSE);
						methOther = methEq.body().decl(ret, "other2").init(JExpr.cast(ret, methOther));
						IJExpression testeq = null;

						JMethod methHashCode = ret.method(JMod.PUBLIC, cm.INT, "hashCode");
						IJExpression retHashCode = JExpr.lit(0);

						for (Entry<String, AbstractJType> e : map.entrySet()) {
							JFieldVar field = ret.field(JMod.PUBLIC|JMod.FINAL, e.getValue(), e.getKey());

							JVar consParam = methcons.param(e.getValue(), e.getKey());
							methcons.body().assign(JExpr.refthis(field), consParam);

							IJExpression addedtest = null;
							// test field==other.field
							addedtest = field.eq(methOther.ref(field));
							if (!e.getValue().isPrimitive()) {
								IJExpression objectTest = field.neNull().cand(field.invoke("equals").arg(methOther.ref(field)));
								// test field==other.field || field!=null &&
								// field.equals(other.field)
								addedtest = addedtest.cor(objectTest);
							}
							testeq=testeq==null? addedtest:testeq.cand(addedtest);

							retHashCode = retHashCode.plus(e.getValue().isPrimitive() ? field
									: JExpr.cond(field.eqNull(), JExpr.lit(0), field.invoke("hashCode")));
						}

						methEq.body()._return(testeq);
						methHashCode.body()._return(JExpr.cast(cm.INT, retHashCode));

						existingKeyTypes.put(map, ret);
					} catch (JClassAlreadyExistsException e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				}
			}
		}
		return ret;
	}

	protected String compilableName(AbstractJType t) {
		if (t.isArray()) {
			return "L" + compilableName(((JArrayClass) t).elementType());
		} else {
			return t.name();
		}
	}
}
