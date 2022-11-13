package fr.guiguilechat.jcelechat.model.sde.npcs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	@Override
	public String toString() {
		return "" + name + "(" + id + ")";
	}

	public static enum AGENT_TYPE {
		Basic(2), Tutorial(3), RnD(4), Concord(5), Storyline(6), StorylineMission(7), Event(8), FactionalWarfare(
				9), EpicArc(10), Aura(11), Career(12), Paragon(13);

		public final int typeID;

		private AGENT_TYPE(int typeID) {
			this.typeID = typeID;
		}

		private static Map<Integer, AGENT_TYPE> by_typeID = null;

		public static AGENT_TYPE of(int typeID) {
			if (by_typeID == null) {
				by_typeID = Stream.of(values()).collect(Collectors.toMap(t -> t.typeID, t -> t));
			}
			return by_typeID.get(typeID);
		}

	}

	public AGENT_TYPE type;

	public static enum AGENT_DIVISION {
		RnD(18), Distribution(22), Mining(23), Security(24), BusinessCareerPAth(25), ExplorationCareerPath(
				26), IndustryCareerPath(27), MilitaryCareerPath(
						28), AdvancedMilitaryPath(29), Interbus(37)
		;

		public final int divisionID;

		private AGENT_DIVISION(int divisionID) {
			this.divisionID = divisionID;
		}

		private static Map<Integer, AGENT_DIVISION> by_divisionID = null;

		public static AGENT_DIVISION of(int divisionID) {
			if (by_divisionID == null) {
				by_divisionID = Stream.of(values()).collect(Collectors.toMap(t -> t.divisionID, t -> t));
			}
			return by_divisionID.get(divisionID);
		}
	}

	public AGENT_DIVISION division;

}
