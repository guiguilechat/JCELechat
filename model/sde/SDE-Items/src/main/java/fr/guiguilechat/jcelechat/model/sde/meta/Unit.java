package fr.guiguilechat.jcelechat.model.sde.meta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.settings.yaml.CleanRepresenter;
import fr.lelouet.tools.settings.yaml.YAMLTools;

public class Unit {

	private static final Logger logger = LoggerFactory.getLogger(Unit.class);

	// loading/dumping

	private static LinkedHashMap<Integer, Unit> cache = null;

	public static final String RESOURCE_PATH = "SDE/meta/units.yaml";

	public static synchronized LinkedHashMap<Integer, Unit> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Unit.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).units;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<Integer, Unit> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.units = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<Integer, Unit> units;
	}

	// structure

	public String name;
	public String prefix;
	public String suffix;
	public int id;
	public String[] enums;

	public boolean isEnum() {
		return enums != null && enums.length > 0;
	}

	public String value(double value) {
		if (isEnum()) {
			if (value >= enums.length || value < 0) {
				logger.warn("out of bound for unit " + name + " value " + value);
				return (prefix == null || prefix.length() == 0 ? "" : prefix) + value
						+ (suffix == null || suffix.length() == 0 ? "" : suffix);
			} else {
				return enums[(int) value];
			}
		} else {
			return (prefix == null || prefix.length() == 0 ? "" : prefix) + value + " "
					+ (suffix == null || suffix.length() == 0 ? "" : suffix);
		}
	}

}
