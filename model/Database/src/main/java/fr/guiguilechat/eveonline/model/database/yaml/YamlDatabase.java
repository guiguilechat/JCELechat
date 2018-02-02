package fr.guiguilechat.eveonline.model.database.yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.retrieval.sde.SDEDumper;

/**
 * tools to get existing database
 */
public class YamlDatabase extends EveDatabase {

	private static final Logger logger = LoggerFactory.getLogger(YamlDatabase.class);

	public static DatabaseFile load(InputStream stream) {
		return makeYaml().loadAs(stream, DatabaseFile.class);
	}

	public static void write(DatabaseFile db, File file) throws IOException {
		file.getParentFile().mkdirs();
		makeYaml().dump(db, new FileWriter(file));
	}

	protected static Yaml makeYaml() {
		return new Yaml(makeConstructor(), makeRepresenter(), makeOptions());
	}

	protected static BaseConstructor makeConstructor() {
		Constructor ret = new Constructor(DatabaseFile.class);
		return ret;
	}

	public static Representer makeRepresenter() {
		return new CleanRepresenter();
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

	public static DumperOptions makeOptions() {
		DumperOptions opt = new DumperOptions();
		opt.setDefaultScalarStyle(ScalarStyle.PLAIN);
		opt.setDefaultFlowStyle(FlowStyle.BLOCK);
		return opt;
	}

	protected LinkedHashMap<String, Hull> hulls = null;

	@Override
	public synchronized LinkedHashMap<String, Hull> getHulls() {
		if (hulls == null) {
			logger.debug("loading hulls");
			InputStream hullsStream = DatabaseFile.class.getResourceAsStream("/" + SDEDumper.DB_HULLS_RES);
			DatabaseFile hullDB = hullsStream != null ? load(hullsStream) : null;
			if (hullDB != null) {
				hulls = hullDB.hulls;
			} else {
				System.err.println("can't load hulls");
				hulls = new LinkedHashMap<>();
			}
		}
		return hulls;
	}

	protected LinkedHashMap<String, Module> modules = null;

	@Override
	public synchronized LinkedHashMap<String, Module> getModules() {
		if (modules == null) {
			logger.debug("loading modules");
			InputStream modulesStream = DatabaseFile.class.getResourceAsStream("/" + SDEDumper.DB_MODULES_RES);
			DatabaseFile moduleDB = modulesStream != null ? load(modulesStream) : null;
			if (moduleDB != null) {
				modules = moduleDB.modules;
			} else {
				System.err.println("can't load modules");
				modules = new LinkedHashMap<>();
			}
		}
		return modules;
	}

}
