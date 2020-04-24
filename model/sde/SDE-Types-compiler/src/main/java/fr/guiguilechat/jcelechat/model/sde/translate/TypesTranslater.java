package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.helger.jcodemodel.JTryResource;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.jcelechat.model.sde.compile.CompilationData;
import fr.guiguilechat.jcelechat.model.sde.compile.SDECompiler;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.lelouet.tools.compilation.inmemory.DynamicClassLoader;
import fr.lelouet.tools.settings.yaml.CleanRepresenter;
import fr.lelouet.tools.settings.yaml.YAMLTools;

/**
 * translates sde into yaml files using compiled data. Also modifies the
 * compilation unit to add load functions.
 *
 */
public class TypesTranslater {

	private static final Logger logger = LoggerFactory.getLogger(TypesTranslater.class);

	public void translate(TypeHierarchy hierarchy, CompilationData classes, File destFolder, String resFolder) {
		long startTime = System.currentTimeMillis();
		JCodeModel cm = classes.model;
		makeLoadMethod(null, classes.typeIndexClass, cm, "SDE/types/metainf.yaml", false);
		DynamicClassLoader cl = new DynamicClassLoader(TypesTranslater.class.getClassLoader()).withCode(cm);
		// filepath->item name -> object
		// eg mycategory/mygroup.yaml -> item1-> new MyGroup()
		HashMap<String, LinkedHashMap<String, Object>> exportItems = new HashMap<>();
		HashMap<Integer, Object> builtItems = new HashMap<>();
		// metainf
		LinkedHashMap<String, String> name2group = new LinkedHashMap<>();
		LinkedHashMap<String, String> group2class = new LinkedHashMap<>();

		for (Entry<Integer, TypeDetails> e : hierarchy.typeID2Details.entrySet()) {
			TypeDetails td = e.getValue();
			GroupDetails gd = hierarchy.groupID2Details.get(td.groupID);
			CatDetails cd = hierarchy.catID2Details.get(gd.catID);
			if (SDECompiler.ignoreType(cd.published, gd.published, td.published)) {
				logger.debug("skipped type " + td.name + "(" + e.getKey() + "), published loss (type=" + td.published
						+ ", group=" + gd.published + ", cat=" + cd.published + ")");
				continue;
			}
			String className = classes.groupID2ClassName.get(td.groupID);
			if (className == null) {
				logger.debug("type " + td.name + " has no class");
				continue;

			}
			Object item = makeObjectDefault(className, cl);
			String fileName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName() + ".yaml";
			// set the name by introspection
			try {
				Field nameField = item.getClass().getField("name");
				nameField.setAccessible(true);
				nameField.set(item, td.name);
				Field idField = item.getClass().getField("id");
				idField.setAccessible(true);
				idField.set(item, e.getKey());
				Field volumeField = item.getClass().getField("volume");
				volumeField.setAccessible(true);
				volumeField.set(item, td.volume);
				Field marketGroupField = item.getClass().getField("marketGroup");
				marketGroupField.setAccessible(true);
				marketGroupField.set(item, td.marketGroupID);
				Field publishedfield = item.getClass().getField("published");
				publishedfield.setAccessible(true);
				publishedfield.set(item, td.published);
				Field massField = item.getClass().getField("mass");
				massField.setAccessible(true);
				massField.set(item, td.mass);
				Field priceField = item.getClass().getField("price");
				priceField.setAccessible(true);
				priceField.set(item, td.basePrice);
				for (Entry<Integer, Float> c : td.definition.entrySet()) {
					if (c.getValue() == 0) {
						continue;
					}
					String fieldName = classes.attID2FieldName.get(c.getKey());
					if (fieldName == null) {
						throw new NullPointerException(
								"no name for field " + c.getKey() + " existing names are " + classes.attID2FieldName);
					}
					try {
						Field f = item.getClass().getField(fieldName);
						f.setAccessible(true);
						if (f.getType() == double.class) {
							double value = c.getValue();
							f.set(item, value);
						} else {
							int value = (int) (float) c.getValue();
							f.set(item, value);
						}
					} catch (Exception nsfe) {
						throw new UnsupportedOperationException("cant' find field " + fieldName + "(" + c.getKey() + ") in class "
								+ item.getClass().getName() + " to value " + c.getValue() + ", fields are "
								+ Arrays.asList(item.getClass().getFields()), nsfe);
					}
				}
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
			m.put(td.name, item);
			builtItems.put(e.getKey(), item);

			// add metainf

			String packageName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName();
			name2group.put(td.name, packageName);
			if (!group2class.containsKey(packageName)) {
				group2class.put(packageName, item.getClass().getName());
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
					public LinkedHashMap<String, Object> types = map;
				}, new FileWriter(out));
			} catch (IOException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// write metadata
		// meta informations. we need to be able to find an item name, and its
		// class, from its id.
		LinkedHashMap<Integer, String> id2Name = new LinkedHashMap<>();
		hierarchy.typeID2Details.keySet().stream().mapToInt(i -> i).sorted()
		.forEach(i -> id2Name.put(i, hierarchy.typeID2Details.get(i).name));

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

		logger.info("translated types in " + (System.currentTimeMillis() - startTime) / 1000 + "s");
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
						cm.ref(LinkedHashMap.class).narrow(cm.ref(String.class), loadedClass), "types");
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
		JTryResource jtr = new JTryResource(cm.ref(InputStreamReader.class), "reader",
				JExpr._new(cm.ref(InputStreamReader.class)).arg(metagroup.staticRef("class")
						.invoke("getClassLoader")
						.invoke("getResourceAsStream").arg(JExpr.direct("RESOURCE_PATH"))));
		tryblock.tryResources().add(jtr);
		IJExpression class2cast = container ? JExpr.direct("Container.class") : loadedClass.dotclass();
		IJExpression assign = JExpr._new(cm.ref(Yaml.class)).invoke("loadAs").arg(jtr.var()).arg(class2cast);
		if (container) {
			assign = assign.ref("types");
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
