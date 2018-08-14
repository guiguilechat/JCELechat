package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JCatchBlock;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.jcelechat.model.sde.compile.SDECompiler.CompiledClassesData;
import fr.guiguilechat.jcelechat.model.sde.compile.inmemory.DynamicClassLoader;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EdgmTypeAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;

/**
 * translates sde into yaml files using compiled data. Also modifies the
 * compilation unit to add load functions.
 *
 */
public class ItemsTranslater {

	private static final Logger logger = LoggerFactory.getLogger(ItemsTranslater.class);

	public void translate(CompiledClassesData classes, File destFolder, String resFolder) {
		long startTime = System.currentTimeMillis();
		JCodeModel cm = classes.model;
		makeLoadMethod(null, classes.metaInfClass, cm, "SDE/items/metainf.yaml", false);
		DynamicClassLoader cl = new DynamicClassLoader(ItemsTranslater.class.getClassLoader()).withCode(cm);
		// filepath->item name -> object
		// eg mycategory/mygroup.yaml -> item1-> new MyGroup()
		HashMap<String, LinkedHashMap<String, Object>> exportItems = new HashMap<>();
		HashMap<Integer, Object> builtItems = new HashMap<>();
		// metainf
		LinkedHashMap<String, String> name2group = new LinkedHashMap<>();
		LinkedHashMap<String, String> group2class = new LinkedHashMap<>();

		LinkedHashMap<Integer, EtypeIDs> typeids = EtypeIDs.load();
		for (Entry<Integer, EtypeIDs> e : typeids.entrySet()) {
			EtypeIDs type = e.getValue();
			if (!type.published) {
				logger.debug("type " + type.enName() + "(" + e.getKey() + ") is not published");
				continue;
			}
			String className = classes.groupID2ClassName.get(type.groupID);
			if (className == null) {
				logger.debug("type " + type.enName() + " has unpublished group class");
				continue;

			}
			Object item = makeObjectDefault(className, cl);
			String fileName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName() + ".yaml";
			// set the name by introspection
			try {
				Field nameField = item.getClass().getField("name");
				nameField.setAccessible(true);
				nameField.set(item, type.enName());
				Field idField = item.getClass().getField("id");
				idField.setAccessible(true);
				idField.set(item, e.getKey());
				Field volumeField = item.getClass().getField("volume");
				volumeField.setAccessible(true);
				volumeField.set(item, type.volume);
				Field marketGroupField = item.getClass().getField("marketGroup");
				marketGroupField.setAccessible(true);
				marketGroupField.set(item, type.marketGroupID);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				throw new UnsupportedOperationException("for class " + item.getClass(), e1);
			}
			LinkedHashMap<String, Object> m = exportItems.get(fileName);
			if (m == null) {
				m = new LinkedHashMap<>();
				exportItems.put(fileName, m);
				JDefinedClass groupclass = cm._getClass(className);
				JDefinedClass metagroup = groupclass.classes().stream().filter(jc -> jc.name().equals("MetaGroup")).findFirst()
						.get();
				makeLoadMethod(metagroup, groupclass, cm, resFolder + fileName, true);
			}
			m.put(type.enName(), item);
			builtItems.put(e.getKey(), item);

			// add metainf

			String packageName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName();
			name2group.put(type.enName(), packageName);
			if (!group2class.containsKey(packageName)) {
				group2class.put(packageName, item.getClass().getName());
			}
		}
		for (Entry<Integer, HashMap<Integer, EdgmTypeAttributes>> e : EdgmTypeAttributes.loadByTypeIDAttributeID()
				.entrySet()) {
			try {
				Object built = builtItems.get(e.getKey());
				if (built == null) {
					logger.debug("cannot find item built for id " + e.getKey());
					continue;
				}
				for (Entry<Integer, EdgmTypeAttributes> c : e.getValue().entrySet()) {
					String fieldName = classes.attID2FieldName.get(c.getKey());
					Field f = built.getClass().getField(fieldName);
					f.setAccessible(true);
					if (f.getType() == double.class) {
						f.set(built, c.getValue().valueFloat);
					} else {
						if (c.getValue().valueFloat != 0) {
							f.set(built, (int) c.getValue().valueFloat);
						} else {
							f.set(built, c.getValue().valueInt);
						}
					}
				}
			} catch (Exception ex) {
				throw new UnsupportedOperationException("while loading " + e, ex);
			}
		}

		// write the items

		destFolder.mkdirs();
		for (Entry<String, LinkedHashMap<String, Object>> e : exportItems.entrySet()) {
			LinkedHashMap<String, Object> map = e.getValue();
			ArrayList<Entry<String, Object>> sortingList = new ArrayList<>(map.entrySet());
			Collections.sort(sortingList, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			map.clear();
			for (Entry<String, Object> e2 : sortingList) {
				map.put(e2.getKey(), e2.getValue());
			}
			File out = new File(destFolder, e.getKey());
			out.mkdirs();
			out.delete();
			try {
				Yaml yaml = new Yaml(new CleanRepresenter(), YAMLTools.blockDumper());
				yaml.dump(new Object() {
					@SuppressWarnings("unused")
					public LinkedHashMap<String, Object> items = map;
				}, new FileWriter(out));
			} catch (IOException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// write metadata
		// meta informations. we need to be able to find an item name, and its
		// class, from its id.
		LinkedHashMap<Integer, String> id2Name = new LinkedHashMap<>();
		typeids.keySet().stream().mapToInt(i -> i).sorted().forEach(i -> id2Name.put(i, typeids.get(i).enName()));

		ArrayList<String> sortedNames = new ArrayList<>(name2group.keySet());
		Collections.sort(sortedNames);
		LinkedHashMap<String, String> sortedName2Group = new LinkedHashMap<>();
		for (String s : sortedNames) {
			sortedName2Group.put(s, name2group.get(s));
		}

		ArrayList<String> sortedGroups = new ArrayList<>(group2class.keySet());
		Collections.sort(sortedGroups);
		LinkedHashMap<String, String> sortedGroup2Class = new LinkedHashMap<>();
		for (String s : sortedGroups) {
			sortedGroup2Class.put(s, group2class.get(s));
		}

		try {
			Yaml yaml = new Yaml(new CleanRepresenter(), YAMLTools.blockDumper());
			yaml.dump(new Object() {
				@SuppressWarnings("unused")
				public LinkedHashMap<Integer, String> id2name = id2Name;
				@SuppressWarnings("unused")
				public LinkedHashMap<String, String> name2group = sortedName2Group;
				@SuppressWarnings("unused")
				public LinkedHashMap<String, String> group2class = sortedGroup2Class;
			}, new FileWriter(new File(destFolder, "metainf.yaml")));
		} catch (IOException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}

		logger.info("translated items in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
	}

	protected Object makeObjectDefault(String string, DynamicClassLoader cl) {
		try {
			Class<?> clazz = cl.loadClass(string);
			Object ret = clazz.getDeclaredConstructor().newInstance();
			ret.getClass().getMethod("loadDefault").invoke(ret);
			return ret;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	protected void makeLoadMethod(JDefinedClass metagroup, JDefinedClass loadedClass, JCodeModel cm, String resPath,
			boolean container) {
		boolean _static = metagroup == null;
		if (metagroup == null) {
			metagroup = loadedClass;
		}
		metagroup.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String.class), "RESOURCE_PATH")
		.init(JExpr.lit(resPath));

		if (container) {
			// create a Container class that contains only a field
			// LinkedHashMap<String, thisclass>
			// this allows to have snakeyaml parse a text file into a hahsmap
			try {
				metagroup._class(JMod.PRIVATE | JMod.STATIC, "Container").field(JMod.PUBLIC,
						cm.ref(LinkedHashMap.class).narrow(cm.ref(String.class), loadedClass), "items");
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// create the load method
		AbstractJClass retType = container ? cm.ref(Map.class).narrow(cm.ref(String.class), loadedClass) : loadedClass;
		// the cache of the load
		JVar cache = metagroup.field(JMod.PRIVATE | (_static ? JMod.STATIC : 0), retType, "cache")
				.init(JExpr.direct("null"));
		// body method for load
		JMethod load = metagroup.method(JMod.PUBLIC | (_static ? JMod.STATIC : 0) | JMod.SYNCHRONIZED, retType, "load");
		if (!_static) {
			load.annotate(Override.class);
		}
		JBlock ifblock = load.body()._if(cache.eq(JExpr._null()))._then();
		JTryBlock tryblock = ifblock._try();
		IJExpression class2cast = container ? JExpr.direct("Container.class") : loadedClass.dotclass();
		IJExpression assign = JExpr._new(cm.ref(Yaml.class)).invoke("loadAs")
				.arg(JExpr._new(cm.ref(InputStreamReader.class)).arg(
						loadedClass.dotclass().invoke("getClassLoader").invoke("getResourceAsStream")
						.arg(JExpr.direct("RESOURCE_PATH"))))
				.arg(class2cast);
		if (container) {
			assign = assign.ref("items");
		}
		tryblock.body().assign(cache, assign);
		JCatchBlock catchblk = tryblock._catch(cm.ref(Exception.class));
		catchblk.body()._throw(JExpr._new(cm.ref(UnsupportedOperationException.class)).arg(JExpr.lit("catch this"))
				.arg(catchblk.param("exception")));

		if (container) {
			load.body()._return(cm.ref(Collections.class).staticInvoke("unmodifiableMap").arg(cache));
		} else {
			load.body()._return(cache);
		}


	}
}
