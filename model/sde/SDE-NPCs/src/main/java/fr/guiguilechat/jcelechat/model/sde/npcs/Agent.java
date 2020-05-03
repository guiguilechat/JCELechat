package fr.guiguilechat.jcelechat.model.sde.npcs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class Agent {

	// loading/dumping

	private static LinkedHashMap<String, Agent> cache = null;

	public static final String RESOURCE_PATH = "SDE/npcs/agents.yaml";

	public static synchronized LinkedHashMap<String, Agent> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					Agent.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).agents;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<String, Agent> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.agents = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting agents to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, Agent> agents;
	}

	// structure

	public int id;
	public String name;
	public int level;
	public String corporation;
	public boolean isLocator = false;
	public String system;
	public String station;
	public int stationId;

	public String type;
	public String division;

}
