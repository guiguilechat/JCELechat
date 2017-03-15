package fr.guiguilechat.eveonline.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.retrieval.ChrukerDumper;

public class Parser {

	public static Database load(File file) throws FileNotFoundException {
		return makeYaml().loadAs(new FileReader(file), Database.class);
	}

	public static Database load(InputStream stream) {
		return makeYaml().loadAs(stream, Database.class);
	}

	public static void write(Database db, File file) throws IOException {
		file.getParentFile().mkdirs();
		makeYaml().dump(db, new FileWriter(file));
	}

	public static Database getChrukerDB() {
		String resName = "/" + ChrukerDumper.CHRUKER_HULLS_RES;
		InputStream chrukerStream = Database.class.getResourceAsStream(resName);
		return chrukerStream != null ? load(chrukerStream) : null;
	}

	protected static Yaml makeYaml() {
		DumperOptions opt = new DumperOptions();
		opt.setDefaultScalarStyle(ScalarStyle.PLAIN);
		opt.setDefaultFlowStyle(FlowStyle.BLOCK);
		Yaml ret = new Yaml(makeConstructor(), makeRepresenter(), opt);
		return ret;
	}

	protected static BaseConstructor makeConstructor() {
		Constructor ret = new Constructor(Database.class);
		TypeDescription td = new TypeDescription(Database.class);
		td.putMapPropertyType("hulls", Integer.class, Hull.class);
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

}
