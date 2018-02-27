package fr.guiguilechat.eveonline.model.sde.compile;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

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
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JForEach;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JNarrowedClass;
import com.helger.jcodemodel.JOp;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.eveonline.model.Tools;
import fr.guiguilechat.eveonline.model.sde.load.SDECache;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EdgmAttributeTypes;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EcategoryIDs;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.model.sde.translate.ItemsTranslater;

/** Compile the sde tables into java classes */
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
		return cm._package("fr.guiguilechat.eveonline.model.sde.items");
	}

	protected JPackage annotationsPackage() {
		return rootPackage().subPackage("annotations");
	}

	protected JPackage itemPackage() {
		return rootPackage().subPackage("types");
	}

	// following allow parallel pre-caching

	LinkedHashMap<Integer, EcategoryIDs> catids;

	protected void loadCatIDs() {
		catids = EcategoryIDs.load();
	}

	LinkedHashMap<Integer, EgroupIDs> groupids;

	protected void loadgroupIDs() {
		groupids = EgroupIDs.load();
	}

	LinkedHashMap<Integer, EtypeIDs> typeids;

	protected void loadTypeIDs() {
		typeids = EtypeIDs.load();
	}

	ArrayList<EdgmTypeAttributes> typeAttributes;

	protected void loadAttributes() {
		typeAttributes = EdgmTypeAttributes.load();
	}

	LinkedHashMap<Integer, EdgmAttributeTypes> attTypes;

	protected void loadAttTypes() {
		attTypes = EdgmAttributeTypes.loadByAttributeID();
	}

	protected void load() {
		long beginTime = System.currentTimeMillis();
		Stream<Runnable> r = Stream.of(this::loadAttributes, this::loadAttTypes, this::loadCatIDs, this::loadgroupIDs,
				this::loadTypeIDs);
		r.parallel().forEach(Runnable::run);
		logger.info("preloaded tables in " + (System.currentTimeMillis() - beginTime) / 1000 + "s");
	}

	//

	public static class CompiledClassesData {
		public JCodeModel model = new JCodeModel();
		public HashMap<Integer, String> groupID2ClassName = new HashMap<>();
		public HashMap<Integer, String> attID2FieldName = new HashMap<>();
		public JDefinedClass metaInfClass;

		// for each category, the set of groups that inherit it.
		public HashMap<JDefinedClass, Set<JDefinedClass>> cat2Groups = new HashMap<>();
	}

	public CompiledClassesData compile() {
		long startTime = System.currentTimeMillis();
		load();
		CompiledClassesData ret = new CompiledClassesData();
		cm = ret.model;
		AbstractJClass strRef = cm.ref(String.class);

		// for each group, list all the attributes
		HashMap<Integer, HashSet<Integer>> groupAttributes = new HashMap<>();
		// we also add all the attributes to the category of the group
		HashMap<Integer, HashSet<Integer>> catAttributes = new HashMap<>();
		HashSet<Integer> attributesWithFloatValue = new HashSet<>();
		for (EdgmTypeAttributes attribute : typeAttributes) {
			int attId = attribute.attributeID;
			int typeID = attribute.typeID;
			EtypeIDs type = typeids.get(typeID);
			if (type == null) {
				logger.warn("can't find type entry for id " + typeID);
				continue;
			}
			int groupID = type.groupID;
			if (attribute.valueFloat != 0 && Math.round(attribute.valueFloat) != attribute.valueFloat) {
				if (attributesWithFloatValue.add(attribute.attributeID)) {
					// System.err.println("attribute " + attTypes.get(attId).attributeName
					// + " has float value "
					// + attribute.valueFloat + " for item " + type.enName());
				}
			}
			if (attribute.valueInt != 0) {

			}
			HashSet<Integer> groupAttribute = groupAttributes.get(groupID);
			if (groupAttribute == null) {
				groupAttribute = new HashSet<>();
				groupAttributes.put(groupID, groupAttribute);
			}
			groupAttribute.add(attId);

			int catID = groupids.get(groupID).categoryID;
			HashSet<Integer> catAttribute = catAttributes.get(catID);
			if (catAttribute == null) {
				catAttribute = new HashSet<>();
				catAttributes.put(catID, catAttribute);
			}
			catAttribute.add(attId);
		}

		// then for each cat we keep oly the attributes that are present in every
		// group

		for (Entry<Integer, HashSet<Integer>> e : groupAttributes.entrySet()) {
			int catID = groupids.get(e.getKey()).categoryID;
			if (catAttributes.containsKey(catID)) {
				catAttributes.get(catID).retainAll(e.getValue());
			} else {
				System.err.println("error : can't find cat id  " + catID);
			}
		}

		// then once all cats have their attributes, we removed those from their
		// group
		// attributes

		for (Entry<Integer, HashSet<Integer>> e : groupAttributes.entrySet()) {
			int catID = groupids.get(e.getKey()).categoryID;
			if (catAttributes.containsKey(catID)) {
				e.getValue().removeAll(catAttributes.get(catID));
			} else {
				System.err.println("error : can't find cat id  " + catID);
			}
		}

		// build

		// root class is abstract

		JDefinedClass typeClass;
		try {
			typeClass = rootPackage()._class(JMod.ABSTRACT | JMod.PUBLIC, "Item");
			typeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.INT, "getCategoryId");
			typeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.ref(Class.class).narrow(cm.wildcard()), "getCategory");
			typeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.INT, "getGroupId");
			typeClass.method(JMod.PUBLIC | JMod.ABSTRACT, cm.ref(Class.class).narrow(cm.wildcard()), "getGroup");
			typeClass.field(JMod.PUBLIC, cm.INT, "id");
			typeClass.field(JMod.PUBLIC, cm.DOUBLE, "volume");
			typeClass.field(JMod.PUBLIC, strRef, "name");

			// create a method to load the values of the fields in root class from the
			// actual fields, when they are annotated
			JMethod loadDefault = typeClass.method(JMod.PUBLIC, cm.VOID, "loadDefault");
			JForEach fr = loadDefault.body().forEach(cm.ref(Field.class), "f", JExpr.direct("getClass().getFields()"));
			JVar annotDouble = fr.body().decl(getDefaultDoubleValueAnnotation(), "annotDouble",
					fr.var().invoke("getAnnotation").arg(getDefaultDoubleValueAnnotation().dotclass()));
			JConditional ifDoubleBlock = fr.body()._if(JOp.ne(annotDouble, JExpr.direct("null")));
			JTryBlock tryblock = ifDoubleBlock._then()._try();
			tryblock.body().invoke(fr.var(), "setAccessible").arg(JExpr.lit(true));
			tryblock.body().invoke(fr.var(), "set").arg(JExpr._this()).arg(JExpr.invoke(annotDouble, "value"));
			tryblock._catch(cm.ref(Exception.class));
			JBlock elseDoubleblock = ifDoubleBlock._else();
			JVar annotLong = elseDoubleblock.decl(getDefaultIntValueAnnotation(), "annotLong",
					fr.var().invoke("getAnnotation").arg(getDefaultIntValueAnnotation().dotclass()));
			JConditional ifLongBlock = elseDoubleblock._if(JOp.ne(annotLong, JExpr.direct("null")));
			tryblock = ifLongBlock._then()._try();
			tryblock.body().invoke(fr.var(), "setAccessible").arg(JExpr.lit(true));
			tryblock.body().invoke(fr.var(), "set").arg(JExpr._this()).arg(JExpr.invoke(annotLong, "value"));
			tryblock._catch(cm.ref(Exception.class));
		} catch (JClassAlreadyExistsException e2) {
			throw new UnsupportedOperationException("catch this", e2);
		}

		// create all categories
		// categories are abstract classes.

		HashMap<Integer, JDefinedClass> catIDToClass = new HashMap<>();

		for (Entry<Integer, EcategoryIDs> cate : catids.entrySet()) {
			String newName = formatName(cate.getValue().enName());
			try {
				JDefinedClass catClass = itemPackage()._class(JMod.PUBLIC | JMod.ABSTRACT, newName);
				catClass._extends(typeClass);
				addAttributes(catClass, catAttributes.get(cate.getKey()), attributesWithFloatValue);
				JMethod catID = catClass.method(JMod.PUBLIC, cm.INT, "getCategoryId");
				catID.body()._return(JExpr.lit(cate.getKey()));
				catID.annotate(Override.class);
				JMethod catMeth = catClass.method(JMod.PUBLIC, cm.ref(Class.class).narrow(cm.wildcard()), "getCategory");
				catMeth.body()._return(JExpr.dotclass(catClass));
				catMeth.annotate(Override.class);
				catIDToClass.put(cate.getKey(), catClass);
				ret.cat2Groups.put(catClass, new HashSet<>());
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// then create all groups
		final Set<String> createdClasses = new HashSet<>();
		for (Entry<Integer, EgroupIDs> groupEntry : groupids.entrySet()) {
			String newName = formatName(groupEntry.getValue().enName());
			JDefinedClass catClass = catIDToClass.get(groupEntry.getValue().categoryID);
			try {
				final String fullClassName = itemPackage().subPackage(catClass.name().toLowerCase()).name() + "." + formatName(newName);
				if(!createdClasses.add(fullClassName)){
					logger.warn("Duplicate class creation prevented for {}", fullClassName);
					continue;
				}
				JDefinedClass groupClass = itemPackage().subPackage(catClass.name().toLowerCase())._class(formatName(newName));
				groupClass._extends(catClass);
				addAttributes(groupClass, groupAttributes.get(groupEntry.getKey()), attributesWithFloatValue);

				JMethod groupID = groupClass.method(JMod.PUBLIC, cm.INT, "getGroupId");
				groupID.body()._return(JExpr.lit(groupEntry.getKey()));
				groupID.annotate(Override.class);

				JMethod groupMeth = groupClass.method(JMod.PUBLIC, cm.ref(Class.class).narrow(cm.wildcard()), "getGroup");
				groupMeth.body()._return(JExpr.dotclass(groupClass));
				groupMeth.annotate(Override.class);

				ret.groupID2ClassName.put(groupEntry.getKey(), groupClass.fullName());
				ret.cat2Groups.get(catClass).add(groupClass);
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// map all attributes ids to the fields names

		for (Entry<Integer, EdgmAttributeTypes> e : attTypes.entrySet()) {
			ret.attID2FieldName.put(e.getKey(), formatName(e.getValue().attributeName));
		}

		// create the metainf class

		try {
			ret.metaInfClass = rootPackage()._class("MetaInf");
			ret.metaInfClass.field(JMod.PUBLIC, cm.ref(LinkedHashMap.class).narrow(cm.ref(Integer.class), strRef), "id2name")
			.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));
			ret.metaInfClass.field(JMod.PUBLIC, cm.ref(LinkedHashMap.class).narrow(strRef, strRef), "name2group")
			.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));
			ret.metaInfClass.field(JMod.PUBLIC, cm.ref(LinkedHashMap.class).narrow(strRef, strRef), "group2class")
			.init(JExpr._new(cm.ref(LinkedHashMap.class).narrowEmpty()));

			// method to load an item from its name.

			// create a cache classname-> map<id, ? extends item>
			JNarrowedClass cacheValueType = cm.ref(Map.class).narrow(strRef).narrow(typeClass.wildcardExtends());
			JVar groupcache = ret.metaInfClass
					.field(JMod.PRIVATE | JMod.STATIC, cm.ref(Map.class).narrow(strRef).narrow(cacheValueType), "groupcache")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));

			// create the mthod that uses this cache
			JMethod getItem = ret.metaInfClass.method(JMod.PUBLIC | JMod.STATIC, typeClass, "getItem");
			getItem.annotate(SuppressWarnings.class).param("value", "unchecked");
			JVar itemName = getItem.param(strRef, "name");
			getItem.body()._if(itemName.eq(JExpr._null()))._then()._return(JExpr._null());
			JVar grp = getItem.body().decl(strRef, "group")
					.init(ret.metaInfClass.staticInvoke("load").ref("name2group").invoke("get").arg(itemName));
			getItem.body()._if(grp.eq(JExpr._null()))._then()._return(JExpr._null());
			JVar map = getItem.body().decl(cacheValueType, "map").init(groupcache.invoke("get").arg(grp));
			JBlock createBlock = getItem.body()._if(map.eq(JExpr._null()))._then();

			// try to load the class then call load() on it
			JTryBlock tryblock = createBlock._try();
			// convert the group to the classname
			JVar className = tryblock.body().decl(strRef, "className")
					.init(JExpr.lit(itemPackage().name() + ".")
							.plus(grp.invoke("replaceAll").arg(JExpr.lit("/")).arg(JExpr.lit("."))));
			JVar loadclass = tryblock.body().decl(cm.ref(Class.class).narrowAny(), "loadclass");
			loadclass.init(ret.metaInfClass.dotclass().invoke("getClassLoader").invoke("loadClass").arg(className));
			JBlock assinblock = tryblock.body()._if(loadclass.ne(JExpr._null()))._then();
			assinblock.assign(map,
					JExpr.cast(cacheValueType, loadclass.invoke("getMethod").arg("load").invoke("invoke").arg(JExpr._null())));

			// if exception loading the class
			JCatchBlock catchblock = tryblock._catch(cm.ref(Exception.class));
			catchblock.body()
			._throw(JExpr._new(cm.ref(UnsupportedOperationException.class)).arg(catchblock.param("exception")));

			// if map is still nul, assing it an empty map
			createBlock._if(map.eq(JExpr._null()))._then().assign(map, cm.ref(Collections.class).staticInvoke("emptyMap"));
			createBlock.invoke(groupcache, "put").arg(grp).arg(map);
			getItem.body()._return(map.invoke("get").arg(itemName));

			// create the getItem(int id)
			getItem = ret.metaInfClass.method(JMod.PUBLIC | JMod.STATIC, typeClass, "getItem");
			JVar itemId = getItem.param(cm.INT, "id");
			getItem.body()._return(ret.metaInfClass.staticInvoke("getItem")
					.arg(ret.metaInfClass.staticInvoke("load").ref("id2name").invoke("get").arg(itemId)));

		} catch (JClassAlreadyExistsException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}

		logger.info("compiled items in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
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

	protected void addAttributes(JDefinedClass cl, HashSet<Integer> attributeIDs,
			HashSet<Integer> attributesWithFloatValue) {
		if (attributeIDs == null) {
			return;
		}
		for (Integer attributeID : attributeIDs) {
			EdgmAttributeTypes attr = attTypes.get(attributeID);
			boolean isDouble = attributesWithFloatValue.contains(attr.attributeID);
			JFieldVar f = cl.field(JMod.PUBLIC, isDouble ? cm.DOUBLE : cm.INT, formatName(attr.attributeName));
			f.annotate(getHighIsGoodAnnotation()).param("value", attr.highIsGood);
			f.annotate(getStackableAnnotation()).param("value", attr.stackable);
			if (isDouble) {
				f.annotate(getDefaultDoubleValueAnnotation()).param("value", attr.defaultValue);
			} else {
				f.annotate(getDefaultIntValueAnnotation()).param("value", (int) attr.defaultValue);
			}
			f.javadoc().add(attr.description);
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

	public static boolean skipChar(char c) {
		return !(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9');
	}

	/**
	 *
	 * @param args
	 *          classGenerationFolder resourcePath classPathResourcePath ;
	 *          Typically, src/generated/java src/generated/resources/SDE SDE/
	 * @throws IOException
	 */
	public static void main(String... args) throws IOException {
		File srcTarget = new File(args[0]);
		Tools.delDir(srcTarget);
		srcTarget.mkdirs();
		File resTarget = new File(args[1]);
		Tools.delDir(resTarget);
		resTarget.mkdirs();
		CompiledClassesData data = new SDECompiler().compile();
		new ItemsTranslater().translate(data, resTarget, args[2]);
		data.model.build(srcTarget, (PrintStream) null);
	}

}
