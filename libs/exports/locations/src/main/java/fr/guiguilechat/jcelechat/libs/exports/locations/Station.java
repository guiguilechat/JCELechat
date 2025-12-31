package fr.guiguilechat.jcelechat.libs.exports.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.Getter;
import lombok.experimental.Accessors;

public class Station {

	// loading

	public static final String RESOURCE_PATH = "SDE/locations/stations.yaml";

	private static final class Container {
		public LinkedHashMap<String, Station> locations;
	}

	public static void export(LinkedHashMap<String, Station> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.locations = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting systems to " + output.getAbsolutePath(), e);
		}
	}

	protected static LinkedHashMap<String, Station> loadFile() {
		try (InputStreamReader reader = new InputStreamReader(
		    Region.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
			return new Yaml().loadAs(reader, Container.class).locations;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final LinkedHashMap<String, Station> load = loadFile();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Map<Integer, Station> loadById = load().values().stream()
	    .collect(Collectors.toMap(e -> e.id, e -> e));

	public static Station getStation(int stationID) {
		return loadById().get(stationID);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Map<String, Station> loadLowerCase = load().values().stream().collect(Collectors.toMap(
	    r -> r.name().toLowerCase(),
	    r -> r));

	public static Station getStation(String name) {
		if (name == null) {
			return null;
		}
		return loadLowerCase().get(name.toLowerCase());
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Map<Integer, List<Station>> loadBySystemId = load().values().stream()
	    .collect(Collectors.groupingBy(sta -> sta.system().id));

	public static List<Station> forSystemId(int solarSystemId) {
		if (solarSystemId < 1) {
			return List.of();
		}
		return loadBySystemId().get(solarSystemId);
	}

	// structure

	public ArrayList<String> services = new ArrayList<>();

	public int id;

	public String name;

	public int solarSystemId;

	public String solarSystem;

	public String name() {
		return name == null ? "station:" + id : name;
	}

	private transient SolarSystem solarsystem = null;

	public synchronized SolarSystem system() {
		if (solarsystem == null) {
			solarsystem = SolarSystem.getSystem(solarSystem);
		}
		return solarsystem;
	}

	@Override
	public String toString() {
		return name();
	}

}
