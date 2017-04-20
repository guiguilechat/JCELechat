package fr.guiguilechat.eveonline.database.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.retrieval.sde.SDEDumper;

/**
 * tools to get existing database
 */
public class YamlDatabase extends DataBase {

	public static DatabaseFile load(File file) throws FileNotFoundException {
		return makeYaml().loadAs(new FileReader(file), DatabaseFile.class);
	}

	public static DatabaseFile load(InputStream stream) {
		return makeYaml().loadAs(stream, DatabaseFile.class);
	}

	public static void write(DatabaseFile db, File file) throws IOException {
		file.getParentFile().mkdirs();
		makeYaml().dump(db, new FileWriter(file));
	}

	protected static Yaml makeYaml() {
		DumperOptions opt = new DumperOptions();
		opt.setDefaultScalarStyle(ScalarStyle.PLAIN);
		opt.setDefaultFlowStyle(FlowStyle.BLOCK);
		Yaml ret = new Yaml(makeConstructor(), makeRepresenter(), opt);
		return ret;
	}

	protected static BaseConstructor makeConstructor() {
		Constructor ret = new Constructor(DatabaseFile.class);
		TypeDescription td = new TypeDescription(DatabaseFile.class);
		td.putMapPropertyType("hulls", Integer.class, Hull.class);
		td.putMapPropertyType("asteroids", String.class, Asteroid.class);
		td.putMapPropertyType("modules", Integer.class, Module.class);
		td.putMapPropertyType("blueprints", String.class, Blueprint.class);
		ret.addTypeDescription(td);
		return ret;
	}

	protected static Representer makeRepresenter() {
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

		/**
		 * avoid putting the tags in the yaml output ; otherwise snakeyaml puts a
		 * !!blueprint in the document.
		 */
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

	protected LinkedHashMap<String, Hull> hulls = null;

	@Override
	public LinkedHashMap<String, Hull> getHulls() {
		if (hulls == null) {
			InputStream hullsStream = DatabaseFile.class.getResourceAsStream("/" + SDEDumper.DB_HULLS_RES);
			DatabaseFile hullDB = hullsStream != null ? load(hullsStream) : null;
			if (hullDB != null) {
				hulls = hullDB.hulls;
			} else {
				hulls = new LinkedHashMap<>();
			}
		}
		return hulls;
	}

	protected LinkedHashMap<String, Module> modules = null;

	@Override
	public LinkedHashMap<String, Module> getModules() {
		if (modules == null) {
			InputStream modulesStream = DatabaseFile.class.getResourceAsStream("/" + SDEDumper.DB_MODULES_RES);
			DatabaseFile moduleDB = modulesStream != null ? load(modulesStream) : null;
			if (moduleDB != null) {
				modules = moduleDB.modules;
			} else {
				modules = new LinkedHashMap<>();
			}
		}
		return modules;
	}

	protected LinkedHashMap<String, Asteroid> asteroids = null;

	@Override
	public LinkedHashMap<String, Asteroid> getAsteroids() {
		if (asteroids == null) {
			InputStream asteroidsStream = DatabaseFile.class.getResourceAsStream("/" + SDEDumper.DB_ASTEROIDS_RES);
			DatabaseFile asteroidsDB = asteroidsStream != null ? load(asteroidsStream) : null;
			if (asteroidsDB != null) {
				asteroids = asteroidsDB.asteroids;
			} else {
				asteroids = new LinkedHashMap<>();
			}
		}
		return asteroids;
	}

	protected LinkedHashMap<String, Blueprint> blueprints = null;

	@Override
	public LinkedHashMap<String, Blueprint> getBlueprints() {
		if (blueprints == null) {
			InputStream stream = DatabaseFile.class.getResourceAsStream("/" + SDEDumper.DB_BLUEPRINT_RES);
			DatabaseFile db = stream != null ? load(stream) : null;
			if (db != null) {
				blueprints = db.blueprints;
			} else {
				blueprints = new LinkedHashMap<>();
			}
		}
		return blueprints;
	}

}
