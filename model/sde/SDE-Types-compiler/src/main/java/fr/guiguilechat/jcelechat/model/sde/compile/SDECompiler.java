package fr.guiguilechat.jcelechat.model.sde.compile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JCatchBlock;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JConditional;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldRef;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JForEach;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JLambda;
import com.helger.jcodemodel.JLambdaParam;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JNarrowedClass;
import com.helger.jcodemodel.JOp;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JSwitch;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JTypeVar;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.jcelechat.model.sde.hierarchy.AttributeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/** stateful compiler */
public class SDECompiler {

	private static final Logger logger = LoggerFactory.getLogger(SDECompiler.class);

	protected SDECache sde;

	public SDECompiler() {
		this(new SDECache());
	}

	public SDECompiler(SDECache sdeCache) {
		sde = sdeCache;
	}

	protected JCodeModel cm;

	protected JPackage rootPackage() {
		return cm._package("fr.guiguilechat.jcelechat.model.sde");
	}

	protected JPackage annotationsPackage() {
		return rootPackage().subPackage("annotations");
	}

	protected JPackage TypePackage() {
		return rootPackage().subPackage("types");
	}

	protected JPackage attributesPackage() {
		return rootPackage().subPackage("attributes");
	}

	//

	JDefinedClass attributeClass;
	JDefinedClass doubleAttribute, intAttribute;
	JMethod typeGetAttributes;

	public CompilationData compile(TypeHierarchy hierarchy) {
		CompilationData ret = new CompilationData();
		cm = ret.model;
		AbstractJClass strRef = cm.ref(String.class);

		// first create the root attribute classes
		makeRootClasses(ret);

		//
		// first we find the attributes that a group has a lest one of, and then
		// which attributes are present in all the groups of a cat. This allows to
		// set fields in cats when possible.
		//
		// for each group, list all the attributes
		HashMap<Integer, HashSet<Integer>> groupID2AttIDs = new HashMap<>();
		// we also the common attributes to the category of the group
		HashMap<Integer, HashSet<Integer>> catID2AttIDs = new HashMap<>();
		// all the attribute ids
		HashSet<Integer> allAttributesIds = new HashSet<>();
		assignCatGroupAttributes(hierarchy, catID2AttIDs, groupID2AttIDs, allAttributesIds);
		createAttributes(hierarchy, ret, allAttributesIds, ret.attID2Class);

		// create all categories
		// categories are abstract classes.

		HashMap<Integer, JInvocation> catIDToGroupListInvoke = new HashMap<>();
		HashMap<Integer, JFieldRef> catIDToMetaInstance = new HashMap<>();

		for (Entry<Integer, CatDetails> cate : hierarchy.catID2Details.entrySet()) {
			CatDetails details = cate.getValue();
			String newName = formatName(details.name);
			try {
				JDefinedClass catClass = TypePackage()._class(JMod.PUBLIC | JMod.ABSTRACT, newName);
				catClass._extends(ret.eveTypeClass);
				addAttributes(catClass, catID2AttIDs.get(cate.getKey()), hierarchy);
				addOwnAttributes(catClass, catID2AttIDs.get(cate.getKey()), hierarchy, ret);
				ret.catID2Class.put(cate.getKey(), catClass);
				ret.cat2Groups.put(catClass, new HashSet<>());

				// meta class management

				// create corresponding metaclass class
				JDefinedClass metaCat = catClass._class(JMod.PUBLIC | JMod.STATIC, "MetaCat");
				metaCat._implements(ret.metaCatClass.narrow(catClass));

				// create the returned instance
				JVar metaInstance = catClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, metaCat, "METACAT")
						.init(JExpr._new(metaCat));
				JMethod catGetMeta = catClass.method(JMod.PUBLIC, ret.metaCatClass.narrow(catClass), "getCategory");
				catGetMeta.body()._return(metaInstance);
				catGetMeta.annotate(Override.class);
				catIDToMetaInstance.put(cate.getKey(), catClass.staticRef(metaInstance));

				// metaCat.getCategoryId return the id
				JMethod catID = metaCat.method(JMod.PUBLIC, cm.INT, "getCategoryId");
				catID.body()._return(JExpr.lit(cate.getKey()));
				catID.annotate(Override.class);

				// metaCat.getName return the category name
				JMethod getName = metaCat.method(JMod.PUBLIC, cm.ref(String.class), "getName");
				getName.annotate(Override.class);
				getName.body()._return(JExpr.lit(newName));

				JNarrowedClass subGroupsClass = ret.metaGroupClass.narrow(catClass.wildcardExtends());

				// make the getGroups()#return arrays.aslist(group1, grop2, …)

				// we return it in the getgroups method
				JMethod getGroupsMethod = metaCat.method(JMod.PUBLIC, cm.ref(Collection.class).narrow(subGroupsClass),
						ret.catGetGroups.name());
				JInvocation retGetGroups = cm.ref(Arrays.class).staticInvoke("asList");
				getGroupsMethod.body()._return(retGetGroups);
				catIDToGroupListInvoke.put(cate.getKey(), retGetGroups);
				getGroupsMethod.annotate(Override.class);
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this " + e1.getExistingClass(), e1);
			}
		}

		// then create all typeid groups

		for (Entry<Integer, GroupDetails> groupEntry : hierarchy.groupID2Details.entrySet()) {
			GroupDetails gd = groupEntry.getValue();
			int groupid = groupEntry.getKey();
			CatDetails cd = hierarchy.catID2Details.get(gd.catID);
			if (cd.published && !gd.published) {
				logger.debug("skipped group " + gd.name + "(" + groupEntry.getKey() + "), is not published while cat " + cd.name
						+ " is");
				continue;
			}
			if (gd.name == null) {
				logger.debug("skipped group " + groupEntry.getKey() + " has no name");
				continue;
			}

			// skip the group that have no Type
			if (hierarchy.groupID2TypeIDs.get(groupid).stream().map(i -> hierarchy.typeID2Details.get(i))
					.filter(td -> !ignoreType(cd.published, gd.published, td.published)).findAny().isEmpty()) {
				groupID2AttIDs.put(groupid, new HashSet<>());
				logger.debug("skipped group " + gd.name + "(" + groupEntry.getKey() + "), has no Type");
				continue;
			}

			JDefinedClass catClass = ret.catID2Class.get(gd.catID);
			if (catClass == null) {
				logger.warn("skipped group " + gd.name + "(" + groupEntry.getKey() + "), can't resolve category " + gd.id);
				continue;
			}
			String name = formatName(gd.name);
			try {
				JDefinedClass groupClass = TypePackage().subPackage(catClass.name().toLowerCase())._class(name);
				groupClass._extends(catClass);
				addAttributes(groupClass, groupID2AttIDs.get(groupEntry.getKey()), hierarchy);
				HashSet<Integer> ownAttributes = new HashSet<>(groupID2AttIDs.get(groupid));
				ownAttributes.addAll(catID2AttIDs.get(gd.catID));
				addOwnAttributes(groupClass, ownAttributes, hierarchy, ret);

				ret.groupID2ClassName.put(groupEntry.getKey(), groupClass.fullName());
				ret.cat2Groups.get(catClass).add(groupClass);

				// meta class management

				// create corresponding metaGroup class
				JDefinedClass metaGroup = groupClass._class(JMod.PUBLIC | JMod.STATIC, "MetaGroup");
				metaGroup._implements(ret.metaGroupClass.narrow(groupClass));

				// with one instance that is returned
				JVar metaInstance = groupClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, metaGroup, "METAGROUP")
						.init(JExpr._new(metaGroup));
				JMethod groupGetMeta = groupClass.method(JMod.PUBLIC, ret.metaGroupClass.narrow(groupClass), "getGroup");
				groupGetMeta.annotate(Override.class);
				groupGetMeta.body()._return(metaInstance);

				// link with parent MetaCat
				catIDToGroupListInvoke.get(gd.catID).arg(groupClass.staticRef(metaInstance));

				// meta group refers to meta category
				JMethod getCat = metaGroup.method(JMod.PUBLIC, ret.metaCatClass.narrow(groupClass.wildcardSuper()),
						ret.groupGetCat.name());
				getCat.body()._return(catIDToMetaInstance.get(gd.catID));
				getCat.annotate(Override.class);

				// MetaGroup.getGroupID returns the group id
				JMethod groupID = metaGroup.method(JMod.PUBLIC, cm.INT, "getGroupId");
				groupID.body()._return(JExpr.lit(groupEntry.getKey()));
				groupID.annotate(Override.class);

				// metaGroup.getName return the group name
				JMethod getName = metaGroup.method(JMod.PUBLIC, cm.ref(String.class), "getName");
				getName.annotate(Override.class);
				getName.body()._return(JExpr.lit(name));

			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException(
						"while creating class " + e1.getExistingClass() + " for group name=" + name + " id=" + groupEntry.getKey(),
						e1);
			}
		}

		// create the TypeIndex class

		try {
			ret.typeIndexClass = rootPackage()._class("TypeIndex");
			ret.typeIndexClass
			.field(JMod.PUBLIC, cm.ref(LinkedHashMap.class).narrow(cm.ref(Integer.class), strRef), "id2name")
			.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));
			ret.typeIndexClass.field(JMod.PUBLIC, cm.ref(LinkedHashMap.class).narrow(strRef, strRef), "name2group")
			.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));
			ret.typeIndexClass.field(JMod.PUBLIC, cm.ref(LinkedHashMap.class).narrow(strRef, strRef), "group2class")
			.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));

			// method to load an Type from its name.

			// create a cache classname-> map<id, ? extends Type>
			JNarrowedClass cacheValueType = cm.ref(Map.class).narrow(strRef).narrow(ret.eveTypeClass.wildcardExtends());
			JVar groupcache = ret.typeIndexClass
					.field(JMod.PRIVATE | JMod.STATIC, cm.ref(Map.class).narrow(strRef).narrow(cacheValueType), "groupcache")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));

			// create the method that uses this cache
			JMethod getType = ret.typeIndexClass.method(JMod.PUBLIC | JMod.STATIC, ret.eveTypeClass, "getType");
			getType.annotate(SuppressWarnings.class).param("value", "unchecked");
			JVar TypeName = getType.param(strRef, "name");
			getType.body()._if(TypeName.eq(JExpr._null()))._then()._return(JExpr._null());
			JVar grp = getType.body().decl(strRef, "group")
					.init(ret.typeIndexClass.staticInvoke("load").ref("name2group").invoke("get").arg(TypeName));
			getType.body()._if(grp.eq(JExpr._null()))._then()._return(JExpr._null());
			JVar map = getType.body().decl(cacheValueType, "map").init(groupcache.invoke("get").arg(grp));
			JBlock createBlock = getType.body()._if(map.eq(JExpr._null()))._then();

			// try to load the class then call load() on it
			JTryBlock tryblock = createBlock._try();
			// convert the group to the classname
			JVar className = tryblock.body().decl(strRef, "className").init(
					JExpr.lit(TypePackage().name() + ".").plus(grp.invoke("replaceAll").arg(JExpr.lit("/")).arg(JExpr.lit("."))));
			JVar loadclass = tryblock.body().decl(cm.ref(Class.class).narrowAny(), "loadclass");
			loadclass.init(ret.typeIndexClass.dotclass().invoke("getClassLoader").invoke("loadClass").arg(className));
			JBlock assignblock = tryblock.body()._if(loadclass.neNull())._then();
			// IMetaGroup<? extends Type> img = (IMetaGroup<? extends Type>)
			// loadclass.getField("METAGROUP").get(null);
			// map = img.load();
			JVar img = assignblock.decl(ret.metaGroupClass.narrow(ret.eveTypeClass.wildcardExtends()), "img")
					.init(JExpr.cast(ret.metaGroupClass.narrow(ret.eveTypeClass.wildcardExtends()),
							loadclass.invoke("getField").arg("METAGROUP").invoke("get").arg(JExpr._null())));
			assignblock.assign(map, img.invoke("load"));

			// if exception loading the class
			JCatchBlock catchblock = tryblock._catch(cm.ref(Exception.class));
			catchblock.body()
			._throw(JExpr._new(cm.ref(UnsupportedOperationException.class)).arg(catchblock.param("exception")));

			// if map is still nul, assing it an empty map
			createBlock._if(map.eq(JExpr._null()))._then().assign(map, cm.ref(Collections.class).staticInvoke("emptyMap"));
			createBlock.add(JExpr.invoke(groupcache, "put").arg(grp).arg(map));
			getType.body()._return(map.invoke("get").arg(TypeName));

			// create the getType(int id)
			getType = ret.typeIndexClass.method(JMod.PUBLIC | JMod.STATIC, ret.eveTypeClass, "getType");
			JVar TypeId = getType.param(cm.INT, "id");
			getType.body()._return(ret.typeIndexClass.staticInvoke("getType")
					.arg(ret.typeIndexClass.staticInvoke("load").ref("id2name").invoke("get").arg(TypeId)));

		} catch (JClassAlreadyExistsException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}

		// create the TypeRef class

		try {
			JDefinedClass clazz = rootPackage()._class("TypeRef");
			ret.typeRefClass = clazz;
			JTypeVar generic = clazz.generify("T", ret.eveTypeClass);
			JFieldVar id_f = clazz.field(JMod.PUBLIC, cm.INT, "id");

			// access to the type
			JFieldVar type_f = clazz.field(JMod.PRIVATE | JMod.TRANSIENT, generic, "type");
			JMethod type_m = clazz.method(JMod.PUBLIC, generic, "type");
			type_m.annotate(SuppressWarnings.class).param("unchecked");
			type_m.body()._if(type_f.eqNull())._then().assign(type_f,
					ret.typeIndexClass.staticInvoke("getType").arg(id_f).castTo(generic));
			type_m.body()._return(type_f);

			// access to category
			JFieldVar cat_f = clazz.field(JMod.PRIVATE | JMod.TRANSIENT, strRef, "category");
			JMethod cat_m = clazz.method(JMod.PUBLIC, strRef, "category");
			cat_m.body()._if(cat_f.eqNull())._then().assign(cat_f,
					JExpr.invoke(type_m).invoke("getCategory").invoke("getName"));
			cat_m.body()._return(cat_f);

			// access to group
			JFieldVar group_f = clazz.field(JMod.PRIVATE | JMod.TRANSIENT, strRef, "group");
			JMethod group_m = clazz.method(JMod.PUBLIC, strRef, "group");
			group_m.body()._if(group_f.eqNull())._then().assign(group_f,
					JExpr.invoke(type_m).invoke("getGroup").invoke("getName"));
			group_m.body()._return(group_f);

			// access to name
			JFieldVar name_f = clazz.field(JMod.PRIVATE | JMod.TRANSIENT, strRef, "name");
			JMethod name_m = clazz.method(JMod.PUBLIC, strRef, "name");
			name_m.body()._if(name_f.eqNull())._then().assign(name_f, JExpr.invoke(type_m).ref("name"));
			name_m.body()._return(name_f);

			// tostring method
			JFieldVar tostring_f = clazz.field(JMod.PROTECTED | JMod.TRANSIENT, strRef, "toString");
			JMethod tostring_m = clazz.method(JMod.PUBLIC, strRef, "toString");
			tostring_m.annotate(Override.class);
			tostring_m.body()._if(tostring_f.eqNull())._then().assign(tostring_f,
					JExpr.invoke(name_m).plus(JExpr.lit("(")).plus(id_f).plus(JExpr.lit(")")));
			tostring_m.body()._return(tostring_f);

		} catch (JClassAlreadyExistsException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}

		return ret;
	}

	/**
	 * create the root classes : <br />
	 * the attribute, intattribute and adoubleattribute classes<br />
	 * the metaclass, metagroup with their load() method.<br />
	 * and the EveType class to design a type.
	 */
	protected void makeRootClasses(CompilationData ret) {
		// Attribute, IntAttribute, DoubleAttribute
		try {
			attributeClass = rootPackage()._class(JMod.ABSTRACT | JMod.PUBLIC, "Attribute");
			attributeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.INT, "getId");
			attributeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.BOOLEAN, "getHighIsGood");
			attributeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.DOUBLE, "getDefaultValue");
			attributeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.BOOLEAN, "getPublished");
			attributeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.BOOLEAN, "getStackable");

			doubleAttribute = rootPackage()._class(JMod.ABSTRACT | JMod.PUBLIC, "DoubleAttribute")._extends(attributeClass);
			intAttribute = rootPackage()._class(JMod.ABSTRACT | JMod.PUBLIC, "IntAttribute")._extends(attributeClass);
		} catch (JClassAlreadyExistsException e3) {
			throw new UnsupportedOperationException("catch this", e3);
		}

		// create the evetype now as it is used by metaclass
		try {
			ret.eveTypeClass = rootPackage()._class(JMod.ABSTRACT | JMod.PUBLIC, "EveType");
		} catch (JClassAlreadyExistsException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}

		// metaclass and metagroup
		try {
			ret.metaCatClass = rootPackage()._interface(JMod.PUBLIC, "IMetaCategory");
			JTypeVar paramMetaCat = ret.metaCatClass.generify("T", ret.eveTypeClass);
			ret.metaCatClass.method(JMod.PUBLIC, cm.INT, "getCategoryId");

			ret.metaGroupClass = rootPackage()._interface(JMod.PUBLIC | JMod.PUBLIC, "IMetaGroup");
			JTypeVar paramMetaGroup = ret.metaGroupClass.generify("T", ret.eveTypeClass);
			ret.metaGroupClass.method(JMod.PUBLIC, cm.INT, "getGroupId");

			ret.catGetGroups = ret.metaCatClass.method(JMod.PUBLIC,
					cm.ref(Collection.class).narrow(ret.metaGroupClass.narrow(paramMetaCat.wildcardExtends())), "groups");
			ret.metaCatClass.method(JMod.PUBLIC, cm.ref(String.class), "getName");

			// create a load() method;
			// public default Map<String, T> load() {
			JMethod loadMethod = ret.metaCatClass.method(JMod.PUBLIC | JMod.DEFAULT,
					cm.ref(Map.class).narrow(cm.ref(String.class), paramMetaCat), "load");
			// HashMap<String, T> ret = new HashMap<>()
			JVar loadRet = loadMethod.body().decl(cm.ref(HashMap.class).narrow(cm.ref(String.class), paramMetaCat), "ret")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
			// groups().stream().flatMap(img ->
			// img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(),
			// e.getValue()));
			JLambda lambdafm = new JLambda();
			JLambdaParam lambdafmpar = lambdafm.addParam("img");
			lambdafm.body().add(lambdafmpar.invoke("load").invoke("entrySet").invoke("stream"));
			JLambda lambdafe = new JLambda();
			JLambdaParam lambdafepar = lambdafe.addParam("e");
			lambdafe.body()
			.add(JExpr.invoke(loadRet, "put").arg(lambdafepar.invoke("getKey")).arg(lambdafepar.invoke("getValue")));
			loadMethod.body()
			.add(JExpr.invoke("groups").invoke("stream").invoke("flatMap").arg(lambdafm).invoke("forEach").arg(lambdafe));
			// return ret;
			loadMethod.body()._return(loadRet);

			ret.groupGetCat = ret.metaGroupClass.method(JMod.PUBLIC, ret.metaCatClass.narrow(paramMetaGroup.wildcardSuper()),
					"category");
			ret.groupGetTypes = ret.metaGroupClass.method(JMod.PUBLIC | JMod.DEFAULT,
					cm.ref(Map.class).narrow(cm.ref(String.class), paramMetaGroup), "load");
			ret.groupGetTypes.body()._return(JExpr._null());

			ret.metaGroupClass.method(JMod.PUBLIC, cm.ref(String.class), "getName");
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException(e);
		}

		// create body of EveType

		ret.eveTypeClass.method(JMod.PUBLIC | JMod.ABSTRACT, ret.metaGroupClass.narrow(cm.wildcard()), "getGroup");
		ret.eveTypeClass.method(JMod.PUBLIC, cm.INT, "getGroupId").body()
		._return(JExpr.invoke("getGroup").invoke("getGroupId"));

		ret.eveTypeClass.method(JMod.PUBLIC | JMod.ABSTRACT, ret.metaCatClass.narrow(cm.wildcard()), "getCategory");
		ret.eveTypeClass.method(JMod.PUBLIC, cm.INT, "getCategoryId").body()
		._return(JExpr.invoke("getCategory").invoke("getCategoryId"));

		ret.eveTypeClass.field(JMod.PUBLIC, cm.INT, "id");
		ret.eveTypeClass.field(JMod.PUBLIC, cm.INT, "marketGroup");
		JFieldVar massField = ret.eveTypeClass.field(JMod.PUBLIC, cm.DOUBLE, "mass");
		ret.eveTypeClass.field(JMod.PUBLIC, ret.model.ref(String.class), "name");
		ret.eveTypeClass.field(JMod.PUBLIC, cm.DOUBLE, "packagedVolume");
		ret.eveTypeClass.field(JMod.PUBLIC, cm.INT, "portionSize");
		ret.eveTypeClass.field(JMod.PUBLIC, cm.DOUBLE, "price");
		ret.eveTypeClass.field(JMod.PUBLIC, cm.BOOLEAN, "published");
		ret.eveTypeClass.field(JMod.PUBLIC, cm.DOUBLE, "volume");

		ret.valueSetMeth = ret.eveTypeClass.method(JMod.PUBLIC, cm.ref(Number.class), "valueSet");
		{
			JVar att = ret.valueSetMeth.param(attributeClass, "attribute");
			JSwitch switchattid = ret.valueSetMeth.body()._switch(att.invoke("getId"));
			switchattid._case(JExpr.lit(4)).body()._return(massField);
			switchattid._default().body()._return(JExpr._null());
			ret.valueSetMeth.javadoc().add("get the value affected to this for given attribute, or null if not set");
		}

		// create toString() as a name(id)
		JMethod TypeToString = ret.eveTypeClass.method(JMod.PUBLIC, cm.ref(String.class), "toString");
		TypeToString.annotate(Override.class);
		TypeToString.body()._return(JExpr.direct("name + \"(\" + id + \")\""));

		// create a method to load the values of the fields in root class from the
		// actual fields, when they are annotated
		JMethod loadDefault = ret.eveTypeClass.method(JMod.PUBLIC, cm.VOID, "loadDefault");
		JForEach fr = loadDefault.body().forEach(cm.ref(Field.class), "f", JExpr.direct("getClass().getFields()"));
		JVar annotDouble = fr.body().decl(getDefaultDoubleValueAnnotation(), "annotDouble",
				fr.var().invoke("getAnnotation").arg(getDefaultDoubleValueAnnotation().dotclass()));
		JConditional ifDoubleBlock = fr.body()._if(JOp.ne(annotDouble, JExpr.direct("null")));
		JTryBlock tryblock = ifDoubleBlock._then()._try();
		tryblock.body().add(JExpr.invoke(fr.var(), "setAccessible").arg(JExpr.lit(true)));
		tryblock.body().add(JExpr.invoke(fr.var(), "set").arg(JExpr._this()).arg(JExpr.invoke(annotDouble, "value")));
		tryblock._catch(cm.ref(Exception.class));
		JBlock elseDoubleblock = ifDoubleBlock._else();
		JVar annotLong = elseDoubleblock.decl(getDefaultIntValueAnnotation(), "annotLong",
				fr.var().invoke("getAnnotation").arg(getDefaultIntValueAnnotation().dotclass()));
		JConditional ifLongBlock = elseDoubleblock._if(JOp.ne(annotLong, JExpr.direct("null")));
		tryblock = ifLongBlock._then()._try();
		tryblock.body().add(JExpr.invoke(fr.var(), "setAccessible").arg(JExpr.lit(true)));
		tryblock.body().add(JExpr.invoke(fr.var(), "set").arg(JExpr._this()).arg(JExpr.invoke(annotLong, "value")));
		tryblock._catch(cm.ref(Exception.class));

		JMethod valueMeth = attributeClass.method(JMod.PUBLIC, cm.ref(Number.class), "value");
		{
			JVar Typeparam = valueMeth.param(ret.eveTypeClass, "Type");
			JVar retrieved = valueMeth.body().decl(ret.model.ref(Number.class), "retrieved")
					.init(Typeparam.invoke(ret.valueSetMeth).arg(JExpr._this()));
			JConditional ifnull = valueMeth.body()._if(retrieved.eqNull());
			ifnull._then()._return(JExpr.invoke("getDefaultValue"));
			ifnull._else()._return(retrieved);
		}

		JMethod valueDoubleMeth = doubleAttribute.method(JMod.PUBLIC, cm.DOUBLE.boxify(), "value");
		valueDoubleMeth.annotate(Override.class);
		JVar TypeDoubleparam = valueDoubleMeth.param(ret.eveTypeClass, "Type");
		valueDoubleMeth.body()._return(JExpr._super().invoke("value").arg(TypeDoubleparam).invoke("doubleValue"));

		JMethod valueIntMeth = intAttribute.method(JMod.PUBLIC, cm.INT.boxify(), "value");
		valueIntMeth.annotate(Override.class);
		JVar TypeIntparam = valueIntMeth.param(ret.eveTypeClass, "Type");
		valueIntMeth.body()._return(JExpr._super().invoke("value").arg(TypeIntparam).invoke("intValue"));

		// create an abstract method to get the attributes
		typeGetAttributes = ret.eveTypeClass.method(JMod.PUBLIC | JMod.ABSTRACT,
				ret.model.ref(Set.class).narrow(attributeClass), "getAttributes");
		typeGetAttributes.javadoc().add("list all the attributes that are set for this type");
	}

	/**
	 * assign attribute ids to the groups, or the cat if all groups of this cat
	 * have the attribute assigned.
	 *
	 * @param hierarchy
	 * @param catID2AttIDs
	 * @param groupID2AttIDs
	 * @param allAttributesIds
	 */
	protected void assignCatGroupAttributes(TypeHierarchy hierarchy, HashMap<Integer, HashSet<Integer>> catID2AttIDs,
			HashMap<Integer, HashSet<Integer>> groupID2AttIDs, HashSet<Integer> allAttributesIds) {
		for (Entry<Integer, Set<Integer>> e : hierarchy.catID2GroupIDs.entrySet()) {
			int catId = e.getKey();
			CatDetails cd = hierarchy.catID2Details.get(catId);
			HashSet<Integer> catAttributes = null;
			for (int groupid : e.getValue()) {
				GroupDetails gd = hierarchy.groupID2Details.get(groupid);
				if (hierarchy.groupID2TypeIDs.get(groupid).stream().map(i -> hierarchy.typeID2Details.get(i))
						.filter(td -> !ignoreType(cd.published, gd.published, td.published)).findAny().isEmpty()) {
					groupID2AttIDs.put(groupid, new HashSet<>());
					logger.debug("skip group id=" + groupid + " name=" + gd.name + " as it has no published type");
					continue;
				}
				HashSet<Integer> groupAttributes = new HashSet<>();
				for (int typeId : hierarchy.groupID2TypeIDs.get(groupid)) {
					groupAttributes.addAll(hierarchy.typeID2Details.get(typeId).definition.keySet());
				}
				groupID2AttIDs.put(groupid, groupAttributes);
				allAttributesIds.addAll(groupAttributes);
				if (catAttributes == null) {
					catAttributes = new HashSet<>(groupAttributes);
				} else {
					catAttributes.retainAll(groupAttributes);
				}
			}
			if (catAttributes == null) {
				catAttributes = new HashSet<>();
			}
			// second pass to remove all the attributes of the cat from the group
			for (int groupid : e.getValue()) {
				groupID2AttIDs.get(groupid).removeAll(catAttributes);
			}
			catID2AttIDs.put(catId, catAttributes);
		}
	}

	/**
	 * create the attributes as java classes.
	 *
	 * @param hierarchy
	 *          the hierarchy, used to get attribute details eg name
	 * @param compilation
	 *          used to store the attribute name for an attribute id.
	 * @param allAttributesIds
	 *          all the attribute ids used by types in the hierarchy.
	 * @param attID2Class
	 *          the map to store the class of an attribute.
	 */
	protected void createAttributes(TypeHierarchy hierarchy, CompilationData compilation,
			HashSet<Integer> allAttributesIds, Map<Integer, JDefinedClass> attID2Class) {
		for (int attId : allAttributesIds) {
			AttributeDetails eattr = hierarchy.attID2Details.get(attId);
			String name = formatName(eattr.name);
			compilation.attID2FieldName.put(attId, name.toLowerCase());
			JDefinedClass attClass;
			try {
				attClass = attributesPackage()._class(name);
				if (eattr.hasFloat) {
					attClass._extends(doubleAttribute);
				} else {
					attClass._extends(intAttribute);
				}
				JMethod meth = attClass.method(JMod.PUBLIC, cm.INT, "getId");
				meth.annotate(cm.ref(Override.class));
				meth.body()._return(JExpr.lit(attId));
				meth = attClass.method(JMod.PUBLIC, cm.BOOLEAN, "getHighIsGood");
				meth.annotate(cm.ref(Override.class));
				meth.body()._return(JExpr.lit(eattr.highIsGood));
				meth = attClass.method(JMod.PUBLIC, cm.DOUBLE, "getDefaultValue");
				meth.annotate(cm.ref(Override.class));
				meth.body()._return(JExpr.lit(eattr.defaultValue));
				meth = attClass.method(JMod.PUBLIC, cm.BOOLEAN, "getPublished");
				meth.annotate(cm.ref(Override.class));
				meth.body()._return(JExpr.lit(eattr.published));
				meth = attClass.method(JMod.PUBLIC, cm.BOOLEAN, "getStackable");
				meth.annotate(cm.ref(Override.class));
				meth.body()._return(JExpr.lit(eattr.stackable));
				meth = attClass.method(JMod.PUBLIC, cm.ref(String.class), "toString");
				meth.annotate(cm.ref(Override.class));
				meth.body()._return(JExpr.lit(name));
				attClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, attClass, "INSTANCE").init(JExpr._new(attClass));
				attClass.javadoc().add(eattr.description);
				attID2Class.put(attId, attClass);
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}
	}

	protected JDefinedClass highIsGoodAnnotation;

	protected JDefinedClass getHighIsGoodAnnotation() {
		if (highIsGoodAnnotation == null) {
			try {
				highIsGoodAnnotation = annotationsPackage()._annotationTypeDeclaration("HighIsGood");
				highIsGoodAnnotation.annotate(Retention.class).param("value",
						cm.ref(RetentionPolicy.class).staticRef("RUNTIME"));
				highIsGoodAnnotation.method(JMod.PUBLIC, cm.BOOLEAN, "value");
			} catch (JClassAlreadyExistsException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return highIsGoodAnnotation;
	}

	protected JDefinedClass stackableAnnotation;

	protected JDefinedClass getStackableAnnotation() {
		if (stackableAnnotation == null) {
			try {
				stackableAnnotation = annotationsPackage()._annotationTypeDeclaration("Stackable");
				stackableAnnotation.annotate(Retention.class).param("value",
						cm.ref(RetentionPolicy.class).staticRef("RUNTIME"));
				stackableAnnotation.method(JMod.PUBLIC, cm.BOOLEAN, "value");
			} catch (JClassAlreadyExistsException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return stackableAnnotation;
	}

	protected JDefinedClass defaultDoubleValueAnnotation;

	protected JDefinedClass getDefaultDoubleValueAnnotation() {
		if (defaultDoubleValueAnnotation == null) {
			try {
				defaultDoubleValueAnnotation = annotationsPackage()._annotationTypeDeclaration("DefaultDoubleValue");
				defaultDoubleValueAnnotation.annotate(Retention.class).param("value",
						cm.ref(RetentionPolicy.class).staticRef("RUNTIME"));
				defaultDoubleValueAnnotation.method(JMod.PUBLIC, cm.DOUBLE, "value");
			} catch (JClassAlreadyExistsException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return defaultDoubleValueAnnotation;
	}

	protected JDefinedClass defaulIntValueAnnotation;

	protected JDefinedClass getDefaultIntValueAnnotation() {
		if (defaulIntValueAnnotation == null) {
			try {
				defaulIntValueAnnotation = annotationsPackage()._annotationTypeDeclaration("DefaultIntValue");
				defaulIntValueAnnotation.annotate(Retention.class).param("value",
						cm.ref(RetentionPolicy.class).staticRef("RUNTIME"));
				defaulIntValueAnnotation.method(JMod.PUBLIC, cm.INT, "value");
			} catch (JClassAlreadyExistsException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return defaulIntValueAnnotation;
	}

	/**
	 * resolve known attributes in a class with a switch, defaults to
	 * super.getAttribute
	 *
	 * @param cl
	 * @param attributeIDs
	 * @param hierarchy
	 */
	protected void addAttributes(JDefinedClass cl, HashSet<Integer> attributeIDs, TypeHierarchy hierarchy) {
		if (attributeIDs == null) {
			return;
		}
		Integer[] sortedAttIds = attributeIDs.stream()
				.sorted((i1, i2) -> hierarchy.attID2Details.get(i1).name.compareTo(hierarchy.attID2Details.get(i2).name))
				.toArray(Integer[]::new);

		JSwitch jsAttr = null;

		for (Integer attributeID : sortedAttIds) {
			AttributeDetails attr = hierarchy.attID2Details.get(attributeID);
			JFieldVar f = cl.field(JMod.PUBLIC, attr.hasFloat ? cm.DOUBLE : cm.INT, formatName(attr.name).toLowerCase());
			f.annotate(getHighIsGoodAnnotation()).param("value", attr.highIsGood);
			f.annotate(getStackableAnnotation()).param("value", attr.stackable);
			if (attr.hasFloat) {
				f.annotate(getDefaultDoubleValueAnnotation()).param("value", attr.defaultValue);
			} else {
				f.annotate(getDefaultIntValueAnnotation()).param("value", (int) attr.defaultValue);
			}
			if (jsAttr == null) {
				JMethod attrMeth = cl.method(JMod.PUBLIC, cm.ref(Number.class), "valueSet");
				attrMeth.annotate(Override.class);
				JVar att = attrMeth.param(attributeClass, "attribute");
				jsAttr = attrMeth.body()._switch(att.invoke("getId"));
			}
			jsAttr._case(JExpr.lit(attr.id)).body()._return(f);
			f.javadoc().add(attr.description);
		}
		if (jsAttr != null) {
			jsAttr._default().body()._return(JExpr._super().invoke("valueSet").arg(JExpr.direct("attribute")));
		}
	}

	/**
	 * create a getAttributes() method in the class that refers to the the static set of attributes
	 * @param catClass
	 * @param hashSet
	 * @param hierarchy
	 * @param ret
	 */
	protected void addOwnAttributes(JDefinedClass catClass, HashSet<Integer> hashSet, TypeHierarchy hierarchy,
			CompilationData ret) {
		// public static final Set<Attributes> ATTRIBUTES =
		JVar attField = catClass
				.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, ret.model.ref(Set.class).narrow(attributeClass), "ATTRIBUTES");
		if(hashSet.isEmpty()) {
			// ATTRIBUTES = Collections.emptySet();
			attField.init(ret.model.ref(Collections.class).staticInvoke("emptySet"));
		}else {
			// create an array of the attribute instances
			// array = new Attribute[]{attribute1.INSTANCe, attribute2.INSTANCE};
			JArray array = JExpr.newArray(attributeClass);
			for(Integer attId : hashSet) {
				JDefinedClass attributecl = ret.attID2Class.get(attId);
				if (attributecl != null) {
					array.add(attributecl.staticRef("INSTANCE"));
				}
			}
			//convert it into an unmodifiableset
			// set = Collections.unmodifiableSet(new
			// LinkedHasSet<>(Arrays.aslist(array)))
			JInvocation set = ret.model.ref(Collections.class).staticInvoke("unmodifiableSet").arg(JExpr._new(ret.model.ref(LinkedHashSet.class).narrowEmpty()).arg(ret.model.ref(Arrays.class).staticInvoke("asList").arg(array)));
			//ATTRIBUTES= set
			attField.init(set);
		}

		JMethod meth = catClass.method(JMod.PUBLIC, typeGetAttributes.type(), typeGetAttributes.name());
		meth.annotate(Override.class);
		meth.body()._return(attField);
	}

	public static String formatName(String name) {
		if (name.equals("Abstract")) {
			return "Abstrct";
		}
		name = name.replaceAll("♦", "NPC");
		char[] newName = new char[name.length()];
		int skipped = 0;
		for (int charIndex = 0; charIndex < name.length(); charIndex++) {
			char totrans = name.charAt(charIndex);
			if (skipChar(totrans)) {
				skipped++;
				continue;
			} else {
				if (charIndex - skipped == 0 || skipChar(name.charAt(charIndex - 1))) {
					newName[charIndex - skipped] = Character.toUpperCase(totrans);
				} else {
					newName[charIndex - skipped] = totrans;
				}
			}
		}
		String ret = new String(newName, 0, name.length() - skipped);
		if (ret.charAt(0) >= 0 && ret.charAt(0) <= '9') {
			ret = "Max" + ret;
		}
		return ret;
	}

	protected static boolean skipChar(char c) {
		return !(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9');
	}

	public static boolean ignoreType(boolean catPublished, boolean groupPublished, boolean typePublished) {
		return !typePublished && groupPublished || !groupPublished && catPublished;
	}

}
