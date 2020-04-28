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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jcodemodel.AbstractJClass;
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

	public CompilationData compile(TypeHierarchy hierarchy) {
		CompilationData ret = new CompilationData();
		cm = ret.model;
		AbstractJClass strRef = cm.ref(String.class);

		// create the attribute class
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

		// create the attributes
		HashMap<Integer, JDefinedClass> idToAttribute = new HashMap<>();
		for (int attId : allAttributesIds) {
			AttributeDetails eattr = hierarchy.attID2Details.get(attId);
			String name = formatName(eattr.name);
			ret.attID2FieldName.put(attId, name);
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
				idToAttribute.put(attId, attClass);
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// build

		// metainf category and group

		JDefinedClass metaCatClass;
		JDefinedClass metaGroupClass;
		JMethod groupGetCat;
		JMethod catGetGroups;
		JMethod groupGetTypes;

		try {
			metaCatClass = rootPackage()._interface(JMod.PUBLIC, "IMetaCategory");
			JTypeVar paramMetaCat = metaCatClass.generify("T");
			metaCatClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.INT, "getCategoryId");
			metaGroupClass = rootPackage()._interface(JMod.PUBLIC | JMod.PUBLIC, "IMetaGroup");
			JTypeVar paramMetaGroup = metaGroupClass.generify("T");
			metaGroupClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.INT, "getGroupId");

			catGetGroups = metaCatClass.method(JMod.PUBLIC,
					cm.ref(Collection.class).narrow(metaGroupClass.narrow(paramMetaCat.wildcardExtends())), "groups");
			metaCatClass.method(JMod.PUBLIC, cm.ref(String.class), "getName");

			// create a load() method;
			// }
			// public default Map<String, T> load() {
			JMethod loadMethod = metaCatClass.method(JMod.PUBLIC | JMod.DEFAULT,
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

			groupGetCat = metaGroupClass.method(JMod.PUBLIC, metaCatClass.narrow(paramMetaGroup.wildcardSuper()), "category");
			groupGetTypes = metaGroupClass.method(JMod.PUBLIC | JMod.DEFAULT,
					cm.ref(Map.class).narrow(cm.ref(String.class), paramMetaGroup), "load");
			groupGetTypes.body()._return(JExpr._null());

			metaGroupClass.method(JMod.PUBLIC, cm.ref(String.class), "getName");
		} catch (JClassAlreadyExistsException e) {
			throw new UnsupportedOperationException(e);
		}

		// root class is abstract

		JDefinedClass typeClass;
		try {
			typeClass = rootPackage()._class(JMod.ABSTRACT | JMod.PUBLIC, "EveType");

			typeClass.method(JMod.PUBLIC | JMod.ABSTRACT, metaGroupClass.narrow(cm.wildcard()), "getGroup");
			typeClass.method(JMod.PUBLIC, cm.INT, "getGroupId").body()._return(JExpr.invoke("getGroup").invoke("getGroupId"));

			typeClass.method(JMod.PUBLIC | JMod.ABSTRACT, metaCatClass.narrow(cm.wildcard()), "getCategory");
			typeClass.method(JMod.PUBLIC, cm.INT, "getCategoryId").body()
			._return(JExpr.invoke("getCategory").invoke("getCategoryId"));

			typeClass.field(JMod.PUBLIC, cm.INT, "id");
			typeClass.field(JMod.PUBLIC, cm.INT, "marketGroup");
			typeClass.field(JMod.PUBLIC, cm.DOUBLE, "mass");
			typeClass.field(JMod.PUBLIC, strRef, "name");
			typeClass.field(JMod.PUBLIC, cm.DOUBLE, "packagedVolume");
			typeClass.field(JMod.PUBLIC, cm.INT, "portionSize");
			typeClass.field(JMod.PUBLIC, cm.DOUBLE, "price");
			typeClass.field(JMod.PUBLIC, cm.BOOLEAN, "published");
			typeClass.field(JMod.PUBLIC, cm.DOUBLE, "volume");

			JMethod attrMeth = typeClass.method(JMod.PUBLIC, cm.ref(Number.class), "attribute");
			JVar att = attrMeth.param(attributeClass, "attribute");
			JSwitch js = attrMeth.body()._switch(att.invoke("getId"));
			js._default().body()
			._throw(JExpr._new(cm.ref(UnsupportedOperationException.class))
					.arg(JExpr.lit("can't load attribute id ").plus(att.invoke("getId")).plus(" on type id ")
							.plus(JExpr.direct("id").plus(" ").plus(JExpr.direct("name")))));

			// create toString() as a name(id)
			JMethod TypeToString = typeClass.method(JMod.PUBLIC, cm.ref(String.class), "toString");
			TypeToString.annotate(Override.class);
			TypeToString.body()._return(JExpr.direct("name + \"(\" + id + \")\""));

			// create a method to load the values of the fields in root class from the
			// actual fields, when they are annotated
			JMethod loadDefault = typeClass.method(JMod.PUBLIC, cm.VOID, "loadDefault");
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
			JVar Typeparam = valueMeth.param(typeClass, "Type");
			valueMeth.body()._return(Typeparam.invoke(attrMeth).arg(JExpr._this()));

			JMethod valueDoubleMeth = doubleAttribute.method(JMod.PUBLIC, cm.DOUBLE.boxify(), "value");
			valueDoubleMeth.annotate(Override.class);
			JVar TypeDoubleparam = valueDoubleMeth.param(typeClass, "Type");
			valueDoubleMeth.body()._return(JExpr._super().invoke("value").arg(TypeDoubleparam).invoke("doubleValue"));

			JMethod valueIntMeth = intAttribute.method(JMod.PUBLIC, cm.INT.boxify(), "value");
			valueIntMeth.annotate(Override.class);
			JVar TypeIntparam = valueIntMeth.param(typeClass, "Type");
			valueIntMeth.body()._return(JExpr._super().invoke("value").arg(TypeIntparam).invoke("intValue"));

			// valueDoubleMeth = attr
		} catch (JClassAlreadyExistsException e2) {
			throw new UnsupportedOperationException("catch this", e2);
		}

		// create all categories
		// categories are abstract classes.

		HashMap<Integer, JDefinedClass> catIDToClass = new HashMap<>();
		HashMap<Integer, JInvocation> catIDToGroupListInvoke = new HashMap<>();
		HashMap<Integer, JFieldRef> catIDToMetaInstance = new HashMap<>();

		for (Entry<Integer, CatDetails> cate : hierarchy.catID2Details.entrySet()) {
			CatDetails details = cate.getValue();
			String newName = formatName(details.name);
			// System.err.println("create cat " + newName);
			try {
				JDefinedClass catClass = TypePackage()._class(JMod.PUBLIC | JMod.ABSTRACT, newName);
				catClass._extends(typeClass);
				addAttributes(catClass, catID2AttIDs.get(cate.getKey()), hierarchy);
				catIDToClass.put(cate.getKey(), catClass);
				ret.cat2Groups.put(catClass, new HashSet<>());

				// meta class management

				// create corresponding metaclass class
				JDefinedClass metaCat = catClass._class(JMod.PUBLIC | JMod.STATIC, "MetaCat");
				metaCat._implements(metaCatClass.narrow(catClass));

				// create the returned instance
				JVar metaInstance = catClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, metaCat, "METACAT")
						.init(JExpr._new(metaCat));
				JMethod catGetMeta = catClass.method(JMod.PUBLIC, metaCatClass.narrow(catClass), "getCategory");
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

				JNarrowedClass subGroupsClass = metaGroupClass.narrow(catClass.wildcardExtends());

				// make the getGroups()#return arrays.aslist(group1, grop2, …)

				// we return it in the getgroups method
				JMethod getGroupsMethod = metaCat.method(JMod.PUBLIC, cm.ref(Collection.class).narrow(subGroupsClass),
						catGetGroups.name());
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

			JDefinedClass catClass = catIDToClass.get(gd.catID);
			if (catClass == null) {
				logger
				.warn("skipped group " + gd.name + "(" + groupEntry.getKey() + "), can't resolve category " + gd.id);
				continue;
			}
			String name = formatName(gd.name);
			try {
				JDefinedClass groupClass = TypePackage().subPackage(catClass.name().toLowerCase())._class(name);
				groupClass._extends(catClass);
				addAttributes(groupClass, groupID2AttIDs.get(groupEntry.getKey()), hierarchy);

				ret.groupID2ClassName.put(groupEntry.getKey(), groupClass.fullName());
				ret.cat2Groups.get(catClass).add(groupClass);

				// meta class management

				// create corresponding metaGroup class
				JDefinedClass metaGroup = groupClass._class(JMod.PUBLIC | JMod.STATIC, "MetaGroup");
				metaGroup._implements(metaGroupClass.narrow(groupClass));

				// with one instance that is returned
				JVar metaInstance = groupClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, metaGroup, "METAGROUP")
						.init(JExpr._new(metaGroup));
				JMethod groupGetMeta = groupClass.method(JMod.PUBLIC, metaGroupClass.narrow(groupClass), "getGroup");
				groupGetMeta.annotate(Override.class);
				groupGetMeta.body()._return(metaInstance);

				// link with parent MetaCat
				catIDToGroupListInvoke.get(gd.catID).arg(groupClass.staticRef(metaInstance));

				// meta group refers to meta category
				JMethod getCat = metaGroup.method(JMod.PUBLIC, metaCatClass.narrow(groupClass.wildcardSuper()),
						groupGetCat.name());
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
			JNarrowedClass cacheValueType = cm.ref(Map.class).narrow(strRef).narrow(typeClass.wildcardExtends());
			JVar groupcache = ret.typeIndexClass
					.field(JMod.PRIVATE | JMod.STATIC, cm.ref(Map.class).narrow(strRef).narrow(cacheValueType), "groupcache")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));

			// create the method that uses this cache
			JMethod getType = ret.typeIndexClass.method(JMod.PUBLIC | JMod.STATIC, typeClass, "getType");
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
			JVar img = assignblock.decl(metaGroupClass.narrow(typeClass.wildcardExtends()), "img")
					.init(JExpr.cast(metaGroupClass.narrow(typeClass.wildcardExtends()),
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
			getType = ret.typeIndexClass.method(JMod.PUBLIC | JMod.STATIC, typeClass, "getType");
			JVar TypeId = getType.param(cm.INT, "id");
			getType.body()._return(ret.typeIndexClass.staticInvoke("getType")
					.arg(ret.typeIndexClass.staticInvoke("load").ref("id2name").invoke("get").arg(TypeId)));

		} catch (JClassAlreadyExistsException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}

		return ret;
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
			// System.err.println(" - " + attr.attributeName);
			JFieldVar f = cl.field(JMod.PUBLIC, attr.hasFloat ? cm.DOUBLE : cm.INT, formatName(attr.name));
			f.annotate(getHighIsGoodAnnotation()).param("value", attr.highIsGood);
			f.annotate(getStackableAnnotation()).param("value", attr.stackable);
			if (attr.hasFloat) {
				f.annotate(getDefaultDoubleValueAnnotation()).param("value", attr.defaultValue);
			} else {
				f.annotate(getDefaultIntValueAnnotation()).param("value", (int) attr.defaultValue);
			}
			if (jsAttr == null) {
				JMethod attrMeth = cl.method(JMod.PUBLIC, cm.ref(Number.class), "attribute");
				attrMeth.annotate(Override.class);
				JVar att = attrMeth.param(attributeClass, "attribute");
				jsAttr = attrMeth.body()._switch(att.invoke("getId"));
			}
			jsAttr._case(JExpr.lit(attr.id)).body()._return(f);
			f.javadoc().add(attr.description);
		}
		if (jsAttr != null) {
			jsAttr._default().body()._return(JExpr._super().invoke("attribute").arg(JExpr.direct("attribute")));
		}
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
