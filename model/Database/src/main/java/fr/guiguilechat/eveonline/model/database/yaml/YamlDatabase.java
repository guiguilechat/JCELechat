package fr.guiguilechat.eveonline.model.database.yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

/**
 * tools to get existing database
 */
public class YamlDatabase extends EveDatabase {

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

}
