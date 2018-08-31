package fr.guiguilechat.jcelechat.model.jcesi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JArrayClass;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldRef;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JTypeVar;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ISwaggerCacheHelper;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ITransfer;
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
	protected JPackage connectedPackage = null;
	protected JPackage disconnectedPackage = null;

	protected String responsesPackageName = "responses";
	protected String structuresPackageName = "structures";
	protected String keysPackageName = "keys";
	protected String connectedPackageName = "connected";
	protected String disconnectedPackageName = "disconnected";
	protected AbstractJClass swaggerItf;
	protected JDefinedClass swaggerCOClass;
	protected JDefinedClass swaggerDCClass;

	public ClassBridge(JCodeModel cm, Swagger swagger) {
		this.cm = cm;
		this.swagger = swagger;

		try {
			swaggerItf = (AbstractJClass) cm._ref(ITransfer.class);

			swaggerDCClass = cm._class(rootPackage + "." + "G_IDCAccess", EClassType.INTERFACE)._extends(swaggerItf);
			swaggerDCClass.javadoc().add("interface to access the ESI without an account.<br />"
					+ "This gives access to static data, eg items, markets, etc.");

			swaggerCOClass = cm._class(rootPackage + "." + "G_ICOAccess", EClassType.INTERFACE)._extends(swaggerItf);
			swaggerCOClass.javadoc().add("interface to access the ESI with a connected account.<br />"
					+ "This typically gives access to the character information, corporation, etc.");

		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		createSwaggerCalls();

		responsePackage = cm._package(rootPackage + "." + responsesPackageName);
		structurePackage = cm._package(rootPackage + "." + structuresPackageName);
		keyPackage = cm._package(rootPackage + "." + keysPackageName);
		connectedPackage = cm._package(rootPackage + "." + connectedPackageName);
		disconnectedPackage = cm._package(rootPackage + "." + disconnectedPackageName);

		// first pass to fetch all the responses
		for (Path path : swagger.getPaths().values()) {
			addResponseType(ESICompiler.getResponse(path.getGet()));
			addResponseType(ESICompiler.getResponse(path.getPost()));
		}
		// then we merge all response types that have same structure.
		// this makes a map of renames
		mergeResponseTypes();

		try {
			createCacheMethods();
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("catch this", e);
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
		JFieldVar scopesField = swaggerCOClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String[].class),
				"SCOPES");
		JArray scopesinit = JExpr.newArray(cm.ref(String.class));
		for (String scope : allScopes) {
			scopesinit.add(JExpr.lit(scope));
		}
		scopesField.init(scopesinit);

		headerhandlertype = cm.ref(Map.class).narrow(cm.ref(String.class), cm.ref(List.class).narrow(cm.ref(String.class)));
	}

	protected String rootPackage = ESICompiler.class.getPackage().getName() + ".compiled";

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
	 * get the {@link ObjectProperty} at first or second level from a property. If
	 * the property defines an object, return it ; if the property defines an
	 * array, return the item type fo the array.<br />
	 * So pasing as parameters a property which defines int[] or one which defines
	 * int will both return int.
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
			ret = structurePackage._enum(JMod.PUBLIC, name);
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
			for (JDefinedClass cl : structurePackage.classes()) {
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
			for (Entry<String, Property> e : p.getProperties().entrySet()) {
				Property prop = e.getValue();
				JFieldVar field = cl.field(JMod.PUBLIC,
						translateToClass(prop, pck, structureRenames.getOrDefault(prop.getTitle(), prop.getTitle())), e.getKey());
				field.javadoc().add(prop.getDescription());
			}
			createEquals(cl);
			createHashCode(cl);
			createdClasses.put(classDef, cl);
			return cl;
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	protected void createEquals(JDefinedClass cl) {

		JMethod eqmeth = cl.method(JMod.PUBLIC, cm.BOOLEAN, "equals");
		JVar eqOther = eqmeth.param(cm.ref(Object.class), "other");
		eqmeth.annotate(Override.class);
		// if(other==this) return true;
		eqmeth.body()._if(eqOther.eq(JExpr._this()))._then()._return(JExpr.TRUE);
		// if (other==null || other.getClass!=getClass()) return false;
		eqmeth.body()._if(eqOther.eqNull().cor(eqOther.invoke("getClass").ne(JExpr.invoke("getClass"))))._then()
		._return(JExpr.FALSE);
		JVar eqOtherSame = eqmeth.body().decl(cl, "othersame").init(JExpr.cast(cl, eqOther));

		for (JFieldVar field : cl.fields().values()) {
			JFieldRef otherfield = eqOtherSame.ref(field);
			if (field.type().isPrimitive()) {
				eqmeth.body()._if(field.ne(otherfield))._then()._return(JExpr.FALSE);
			} else {
				eqmeth.body()._if(
						// field!=otherfield
						field.ne(otherfield)
						// && field==null
						.cand(field.eqNull()
								// || !field.equals(otherfield)
								.cor(field.invoke("equals").arg(otherfield).not())))
				._then()._return(JExpr.FALSE);
			}
		}

		eqmeth.body()._return(JExpr.TRUE);
	}

	protected void createHashCode(JDefinedClass cl) {
		JMethod hashmeth = cl.method(JMod.PUBLIC, cm.INT, "hashCode");
		IJExpression ret = null;
		for (JFieldVar field : cl.fields().values()) {
			IJExpression newret = null;
			if (field.type().isPrimitive()) {
				newret = field;
				if (field.type() == cm.DOUBLE || field.type() == cm.FLOAT) {
					// Double.hashCode(field)
					newret = cm.ref(Double.class).staticInvoke("hashCode").arg(field);
				} else {
					if (field.type() == cm.BOOLEAN) {
						newret = cm.ref(Boolean.class).staticInvoke("hashCode").arg(field);
					} else if (field.type() == cm.LONG) {
						newret = cm.ref(Long.class).staticInvoke("hashCode").arg(field);
					}
				}
			} else {
				newret = JExpr.cond(field.eqNull(), JExpr.lit(0), field.invoke("hashCode"));
			}
			ret = ret == null ? newret : ret.plus(newret);
		}
		hashmeth.body()._return(ret);
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

	protected String cacheCOName = "SwaggerCOCache";
	protected String cacheDCName = "SwaggerDCCache";

	private AbstractJClass cacheItf;
	private JDefinedClass cacheCOClass;
	private JDefinedClass cacheDCClass;

	public JDefinedClass cacheClass(boolean connected) {
		return connected ? cacheCOClass : cacheDCClass;
	}

	public String methFetchCacheObject() {
		return "addFetchCacheObject";
	}

	public String methFetchCacheArray() {
		return "addFetchCacheArray";
	}

	public void createCacheMethods() throws JClassAlreadyExistsException {

		cacheItf = cm.ref(ISwaggerCacheHelper.class);
		cacheCOClass = cm._class(JMod.PUBLIC | JMod.ABSTRACT, rootPackage + "." + cacheCOName, EClassType.CLASS)
				._implements(cacheItf);
		cacheDCClass = cm._class(JMod.PUBLIC | JMod.ABSTRACT, rootPackage + "." + cacheDCName, EClassType.CLASS)
				._implements(cacheItf);

		// we need to add generics to allow the cache to be built on a more specific
		// class, ie to bring more functions to be called.

		JTypeVar cacheCOParam = cacheCOClass.generify("T", swaggerCOClass);
		JFieldVar cacheParent = cacheCOClass.field(JMod.PUBLIC | JMod.FINAL, cacheCOParam, "swagger");
		// add a constructor with swagger param
		JMethod cachecons = cacheCOClass.constructor(JMod.PUBLIC);
		JVar swag = cachecons.param(cacheCOParam, "swag");
		cachecons.body().assign(cacheParent, swag);

		JTypeVar cacheDCParam = cacheDCClass.generify("T", swaggerDCClass);
		cacheParent = cacheDCClass.field(JMod.PUBLIC | JMod.FINAL, cacheDCParam, "swagger");
		// add a constructor with swagger param
		cachecons = cacheDCClass.constructor(JMod.PUBLIC);
		swag = cachecons.param(cacheDCParam, "swag");
		cachecons.body().assign(cacheParent, swag);

	}

	protected Map<String, JDefinedClass> cacheCOGroups = new HashMap<>();
	protected Map<String, JDefinedClass> cacheDCGroups = new HashMap<>();

	/**
	 * get the group cache class for given group name. also create a field of
	 * given type in the {@link #cacheCOName} class .
	 *
	 * <br />
	 * eg calling this with "bla" will create a Bla class in the
	 * {@link #cacheCOName} class, as well as a field
	 * <code>public final bla = new Bla();</code> in it.
	 *
	 * @param groupName
	 * @return
	 */
	public JDefinedClass getCacheGroupClass(String groupName, boolean connected) {
		Map<String, JDefinedClass> map = connected ? cacheCOGroups : cacheDCGroups;
		JDefinedClass ret = map.get(groupName);
		if (ret == null) {
			try {
				ret = (connected ? connectedPackage : disconnectedPackage)._class(JMod.PUBLIC,
						groupName.substring(0, 1).toUpperCase() + groupName.substring(1));
				JDefinedClass cachecl = cacheClass(connected);
				// add a final field swagger and constructor Ret(Swagger){this.swagger =
				// swagger;}
				JFieldVar cacheField = ret.field(JMod.PUBLIC | JMod.FINAL, cachecl.narrowAny(), "cache");
				JMethod groupCons = ret.constructor(JMod.PUBLIC);
				JVar swagParam = groupCons.param(cachecl.narrowAny(), "parent");
				groupCons.body().assign(cacheField, swagParam);
				map.put(groupName, ret);
				// need to make direct call or the generated class is ugly(makes
				// reference to the enclosing unparametrized class)
				cacheClass(connected).field(JMod.PUBLIC | JMod.FINAL, ret, groupName).init(JExpr._new(ret).arg(JExpr._this()));
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

						for (Entry<String, AbstractJType> e : map.entrySet()) {
							JFieldVar field = ret.field(JMod.PUBLIC | JMod.FINAL, e.getValue(), e.getKey());

							JVar consParam = methcons.param(e.getValue(), e.getKey());
							methcons.body().assign(JExpr.refthis(field), consParam);
						}

						createEquals(ret);
						createHashCode(ret);

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
