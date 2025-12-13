package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import com.helger.jcodemodel.*;
import com.helger.jcodemodel.exceptions.JClassAlreadyExistsException;
import com.helger.jcodemodel.exceptions.JCodeModelException;

import fr.guiguilechat.jcelechat.model.sde.compile.CompileData;
import fr.guiguilechat.jcelechat.model.sde.compile.SDECompiler;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.CatDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.GroupDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import fr.lelouet.tools.compilation.inmemory.DynamicClassLoader;

/**
 * translates sde into yaml files using compiled data. Also modifies the
 * compilation unit to add load functions.
 */
public class TypesTranslater {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TypesTranslater.class);

	public void translate(TypeHierarchy hierarchy, CompileData classes, File destFolder, String resFolder)
			throws JCodeModelException {
		// System.err.println("abaddon mass in translate is " +
		// hierarchy.typeID2Details.get(24692).mass);
		JCodeModel cm = classes.model;
		makeLoadMethod(null, classes.typeIndexClass, cm, "SDE/types/metainf.yaml", false);
		DynamicClassLoader cl = new DynamicClassLoader(TypesTranslater.class.getClassLoader()).withCode(cm);
		// filepath->item name -> object
		// eg mycategory/mygroup.yaml -> item1-> new MyGroup()
		HashMap<String, LinkedHashMap<Integer, Object>> exportItems = new HashMap<>();
		HashMap<Integer, Object> builtItems = new HashMap<>();
		// metainf
		LinkedHashMap<Integer, String> id2group = new LinkedHashMap<>();
		LinkedHashMap<String, String> group2class = new LinkedHashMap<>();

		for (Entry<Integer, TypeDetails> e : hierarchy.typeID2Details.entrySet()) {
			TypeDetails td = e.getValue();
			GroupDetails gd = hierarchy.groupID2Details.get(td.groupID);
			CatDetails cd = hierarchy.catID2Details.get(gd.catID);
			if (SDECompiler.ignoreType(cd.published, gd.published, td.published)) {
				// logger.debug("skipped type " + td.name + "(" + e.getKey() + "),
				// published loss (type=" + td.published
				// + ", group=" + gd.published + ", cat=" + cd.published + ")");
				continue;
			}
			String className = classes.groupID2ClassName.get(td.groupID);
			if (className == null) {
				// logger.debug("type " + td.name + " has no class");
				continue;

			}
			Object item = makeObjectDefault(className, cl);
			String fileName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName() + ".yaml";
			// set the name by introspection
			try {
				Field idField = item.getClass().getField("id");
				idField.setAccessible(true);
				idField.set(item, e.getKey());
				Field marketGroupField = item.getClass().getField("marketGroup");
				marketGroupField.setAccessible(true);
				marketGroupField.set(item, td.marketGroupID);
				Field massField = item.getClass().getField("mass");
				massField.setAccessible(true);
				massField.set(item, td.mass);
				Field nameField = item.getClass().getField("name");
				nameField.setAccessible(true);
				nameField.set(item, td.name);
				Field packagedVolumeField = item.getClass().getField("packagedVolume");
				packagedVolumeField.setAccessible(true);
				packagedVolumeField.set(item, td.packagedVolume);
				Field portionSizeField = item.getClass().getField("portionSize");
				portionSizeField.setAccessible(true);
				portionSizeField.set(item, td.portionSize);
				Field priceField = item.getClass().getField("price");
				priceField.setAccessible(true);
				priceField.set(item, td.basePrice);
				Field publishedfield = item.getClass().getField("published");
				publishedfield.setAccessible(true);
				publishedfield.set(item, td.published);
				Field radiusField = item.getClass().getField("radius");
				radiusField.setAccessible(true);
				radiusField.set(item, td.radius);
				Field volumeField = item.getClass().getField("volume");
				volumeField.setAccessible(true);
				volumeField.set(item, td.volume);
				// if (td.id == 24692) {
				// System.err.println("type " + td.name + " mass is " +
				// massField.getDouble(item) + " pre dynamic");
				// }
				for (Entry<Integer, BigDecimal> c : td.definition.entrySet()) {
					if (c.getValue().doubleValue() == 0.0) {
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
							f.set(item, c.getValue().doubleValue());
						} else if (f.getType() == int.class) {
							f.set(item, c.getValue().intValue());
						} else {
							f.set(item, c.getValue());
						}
					} catch (Exception nsfe) {
						throw new UnsupportedOperationException(
								"can't find field " + fieldName + "(" + c.getKey() + ") in class "
										+ item.getClass().getName() + " (gid=" + td.groupID + ") to value "
										+ c.getValue() + " for type "
										+ td.name
										+ "(" + td.id
										+ "), fields are "
										+ Arrays.asList(item.getClass().getFields()).stream().map(Field::getName)
												.sorted()
												.collect(Collectors.toList()),
								nsfe);
					}
				}
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				throw new UnsupportedOperationException("for class " + item.getClass(), e1);
			}
			LinkedHashMap<Integer, Object> m = exportItems.get(fileName);
			if (m == null) {
				m = new LinkedHashMap<>();
				exportItems.put(fileName, m);
				JDefinedClass groupclass = cm._getClass(className);
				JDefinedClass metagroup = groupclass.classes().stream().filter(jc -> jc.name().equals("MetaGroup"))
						.findFirst()
						.get();
				makeLoadMethod(metagroup, groupclass, cm, resFolder + fileName, true);
			}
			m.put(td.id, item);
			builtItems.put(e.getKey(), item);

			// add metainf

			String packageName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName();
			id2group.put(td.id, packageName);
			if (!group2class.containsKey(packageName)) {
				group2class.put(packageName, item.getClass().getName());
			}
		}

		// write the items

		destFolder.mkdirs();
		for (Entry<String, LinkedHashMap<Integer, Object>> groupItems : exportItems.entrySet()) {
			LinkedHashMap<Integer, Object> map = groupItems.getValue();
			ArrayList<Entry<Integer, Object>> sortingList = new ArrayList<>(map.entrySet());
			Collections.sort(sortingList, Comparator.comparing(Entry::getKey));
			map.clear();
			for (Entry<Integer, Object> e2 : sortingList) {
				map.put(e2.getKey(), e2.getValue());
			}
			File out = new File(destFolder, groupItems.getKey());
			out.mkdirs();
			out.delete();
			try {
				Yaml yaml = new Yaml(new CleanRepresenter(), YAMLTools.blockDumper());
				yaml.dump(new Object() {
					@SuppressWarnings("unused")
					public LinkedHashMap<Integer, Object> types = map;
				}, new FileWriter(out));
			} catch (IOException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// write metadata
		// meta informations. we need to be able to find an item class, from its id.
		LinkedHashMap<String, ArrayList<Integer>> sortedName2Ids = new LinkedHashMap<>();
		hierarchy.typeID2Details.values().stream().sorted(Comparator.comparing(t -> t.name))
				.forEach(t -> sortedName2Ids.getOrDefault(t.name, new ArrayList<>()).add(t.id));

		ArrayList<Integer> sortedIds = new ArrayList<>(id2group.keySet());
		Collections.sort(sortedIds);
		LinkedHashMap<Integer, String> sortedId2Group = new LinkedHashMap<>();
		for (Integer id : sortedIds) {
			sortedId2Group.put(id, id2group.get(id));
		}

		try {
			Yaml yaml = new Yaml(new CleanRepresenter(), YAMLTools.blockDumper());
			yaml.dump(new Object() {
				@SuppressWarnings("unused")
				LinkedHashMap<String, ArrayList<Integer>> name2Ids = sortedName2Ids;
				@SuppressWarnings("unused")
				public LinkedHashMap<Integer, String> id2group = sortedId2Group;
			}, new FileWriter(new File(destFolder, "metainf.yaml")));
		} catch (IOException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}
	}

	protected Object makeObjectDefault(String string, DynamicClassLoader cl) {
		try {
			Class<?> clazz = cl.loadClass(string);
			Object ret = clazz.getDeclaredConstructor().newInstance();
			ret.getClass().getMethod("loadDefault").invoke(ret);
			return ret;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	protected void makeLoadMethod(JDefinedClass metagroup, JDefinedClass loadedClass, JCodeModel cm, String resPath,
			boolean container) throws JCodeModelException {
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
						cm.ref(LinkedHashMap.class).narrow(cm.ref(Integer.class), loadedClass), "types");
			} catch (JClassAlreadyExistsException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

		// create the load method
		AbstractJClass retType = container ? cm.ref(Map.class).narrow(cm.ref(Integer.class), loadedClass) : loadedClass;
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
		JVar options = tryblock.body().decl(cm.ref(LoaderOptions.class), "options", cm.ref(LoaderOptions.class)._new());
		tryblock.body().add(options.invoke("setCodePointLimit").arg(cm.ref(Integer.class).staticRef("MAX_VALUE")));
		IJExpression class2cast = container ? JExpr.direct("Container.class") : loadedClass.dotclass();
		IJExpression assign = JExpr._new(cm.ref(Yaml.class)).arg(options).invoke("loadAs").arg(jtr.var())
				.arg(class2cast);
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
