package fr.guiguilechat.eveonline.model.sde.compile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCatchBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JVar;

import fr.guiguilechat.eveonline.model.sde.compile.SDECompiler.CompiledClassesData;
import fr.guiguilechat.eveonline.model.sde.compile.inmemory.DynamicClassLoader;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EtypeIDs;

/**
 * translates sde into yaml files using compiled data. Also modifies the
 * compilation unit to add load functions.
 *
 */
public class SDETranslater {

	public void translate(CompiledClassesData classes, File destFolder, String resFolder) {
		JCodeModel cm = classes.model;
		DynamicClassLoader cl = new DynamicClassLoader(getClass().getClassLoader()).withCode(cm);
		// filepath->item name -> object
		// eg mycategory/mygroup.yaml -> item1-> new MyGroup()
		HashMap<String, HashMap<String, Object>> exportItems = new HashMap<>();
		HashMap<Integer, Object> builtItems = new HashMap<>();

		LinkedHashMap<Integer, EtypeIDs> typeids = EtypeIDs.load();
		for (Entry<Integer, EtypeIDs> e : typeids.entrySet()) {
			EtypeIDs type = e.getValue();
			String className = classes.groupID2ClassName.get(type.groupID);
			Object item = makeObjectDefault(className, cl);
			String fileName = item.getClass().getSuperclass().getSimpleName().toLowerCase() + "/"
					+ item.getClass().getSimpleName()
					+ ".yaml";
			HashMap<String, Object> m = exportItems.get(fileName);
			if (m == null) {
				m = new HashMap<>();
				exportItems.put(fileName, m);
				// also add a static final field into the class.
				JDefinedClass clazz = cm._getClass(className);
				clazz.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, cm.ref(String.class), "RESOURCE_PATH")
				.init(JExpr.lit(resFolder + fileName));

				// create a Container class that contains only a field
				// LinkedHashMap<String, thisclass>
				// this allows to have snakeyaml parse a text file into a hahsmap
				try {
					clazz._class(JMod.PRIVATE | JMod.STATIC, "Container").field(JMod.PUBLIC,
							cm.ref(LinkedHashMap.class).narrow(cm.ref(String.class), clazz), "items");
				} catch (JClassAlreadyExistsException e1) {
					throw new UnsupportedOperationException("catch this", e1);
				}

				// create the load method
				JClass retType = cm.ref(LinkedHashMap.class).narrow(cm.ref(String.class), clazz);
				// the cache of the load
				JVar cache = clazz
						.field(JMod.PRIVATE | JMod.STATIC, retType, "cache")
						.init(JExpr.direct("null"));
				// body method for load
				JMethod load = clazz.method(JMod.PUBLIC | JMod.STATIC, retType, "load");
				JBlock ifblock = load.body()._if(JExpr.direct("cache==null"))._then();
				JTryBlock tryblock = ifblock._try();
				tryblock.body().assign(cache, JExpr._new(cm.ref(Yaml.class)).invoke("loadAs")
						.arg(JExpr._new(cm.ref(InputStreamReader.class)).arg(clazz.dotclass().invoke("getClassLoader")
								.invoke("getResourceAsStream").arg(JExpr.direct("RESOURCE_PATH"))))
						.arg(JExpr.direct("Container.class"))
						.ref("items"));
				JCatchBlock catchblk = tryblock._catch(cm.ref(Exception.class));
				catchblk.body()._throw(JExpr._new(cm.ref(UnsupportedOperationException.class)).arg(JExpr.lit("catch this"))
						.arg(catchblk.param("exception")));
				load.body()._return(JExpr.direct("cache"));

			}
			m.put(type.enName(), item);
			builtItems.put(e.getKey(), item);
		}
		for (Entry<Integer, HashMap<Integer, EdgmTypeAttributes>> e : EdgmTypeAttributes.loadByTypeIDAttributeID()
				.entrySet()) {
			try {
				Object built = builtItems.get(e.getKey());
				for (Entry<Integer, EdgmTypeAttributes> c : e.getValue().entrySet()) {
					String fieldName = classes.attID2FieldName.get(c.getKey());
					Field f = built.getClass().getField(fieldName);
					f.setAccessible(true);
					if (c.getValue().valueFloat != 0) {
						f.set(built, c.getValue().valueFloat);
					} else {
						f.set(built, c.getValue().valueInt);
					}
				}
			} catch (Exception ex) {
				throw new UnsupportedOperationException(ex);
			}
		}
		// now we write the map exportItems
		destFolder.mkdirs();
		for (Entry<String, HashMap<String, Object>> e : exportItems.entrySet()) {
			File out = new File(destFolder, e.getKey());
			out.mkdirs();
			out.delete();
			try {
				DumperOptions options = new DumperOptions();
				options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
				Yaml yaml = new Yaml(new CleanRepresenter(), options);
				yaml.dump(new Object() {
					@SuppressWarnings("unused")
					public HashMap<String, Object> items = e.getValue();
				}, new FileWriter(out));
			} catch (IOException e1) {
				throw new UnsupportedOperationException("catch this", e1);
			}
		}

	}

	protected Object makeObjectDefault(String string, DynamicClassLoader cl) {
		try {
			Class<?> clazz = cl.loadClass(string);
			Object ret = clazz.newInstance();

			ret.getClass().getMethod("loadDefault").invoke(ret);
			return ret;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public static class CleanRepresenter extends Representer {

		protected static Set<Object> ZEROS = new HashSet<>(
				Arrays.asList(Integer.valueOf(0), Long.valueOf(0), Float.valueOf(0), Double.valueOf(0)));

		/**
		 * skip a field when it is set to null or to an empty collection
		 */
		@Override
		protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue,
				Tag customTag) {
			if (propertyValue == null || propertyValue instanceof Collection && ((Collection<?>) propertyValue).isEmpty()
					|| propertyValue instanceof Map && ((Map<?, ?>) propertyValue).isEmpty() || ZEROS.contains(propertyValue)) {
				return null;
			} else {
				NodeTuple ret = super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
				if (ret.getValueNode() instanceof MappingNode) {
					MappingNode mn = (MappingNode) ret.getValueNode();
					if (mn.getValue().size() == 0) {
						return null;
					}
				}
				return ret;
			}
		}

		@Override
		protected MappingNode representJavaBean(Set<Property> properties, Object javaBean) {
			// remove the !!class
			if (!classTags.containsKey(javaBean.getClass())) {
				addClassTag(javaBean.getClass(), Tag.MAP);
			}
			MappingNode ret = super.representJavaBean(properties, javaBean);
			ret.setFlowStyle(false);
			return ret;
		}
	}

}
