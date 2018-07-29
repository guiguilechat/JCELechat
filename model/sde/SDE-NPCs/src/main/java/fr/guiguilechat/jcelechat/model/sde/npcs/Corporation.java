package fr.guiguilechat.jcelechat.model.sde.npcs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;

public class Corporation {

	// loading/dumping

	private static LinkedHashMap<String, Corporation> cache = null;

	public static final String RESOURCE_PATH = "SDE/npcs/corporations.yaml";

	public static synchronized LinkedHashMap<String, Corporation> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Corporation.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).corporations;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<String, Corporation> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.corporations = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting corporations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, Corporation> corporations;
	}

	// structure

	public int id;
	public String name;
	public String faction;
	public ArrayList<Integer> lpoffers = new ArrayList<>();
	public double concordRate = 0.0;

	public String warfare;

}
