package fr.guiguilechat.jcelechat.libs.exports.npcs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class Corporation {

	// loading/dumping

	private static LinkedHashMap<String, Corporation> cache = null;

	public static final String RESOURCE_PATH = "SDE/npcs/corporations.yaml";

	public static LinkedHashMap<String, Corporation> load() {
		if (cache == null) {
			synchronized (Corporation.class) {
				if (cache == null) {
					try (InputStreamReader reader = new InputStreamReader(
							Corporation.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
						cache = new Yaml().loadAs(reader, Container.class).corporations;
					} catch (Exception exception) {
						throw new UnsupportedOperationException("catch this", exception);
					}
				}
			}
		}
		return cache;
	}

	public static Corporation of(String name) {
		return load().get(name);
	}

	public static Corporation of(int id) {
		return loadById().get(id);
	}

	private static Map<Integer, Corporation> loadById = null;

	public static Map<Integer, Corporation> loadById() {
		if (loadById == null) {
			synchronized (load()) {
				if (loadById == null) {
					loadById = load().entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, Entry::getValue));
				}
			}
		}
		return loadById;
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

//	public String warfare;

	public List<LPOffer> lpOffers() {
		LinkedHashMap<Integer, LPOffer> offers = LPOffer.load();
		return lpoffers.stream()
				.map(oid -> {
					LPOffer offer = offers.get(oid);
					if (offer == null) {
						System.err.println("offer id " + oid + " can't be resolved");
					}
					return offer;
				})
				.filter(lpo -> lpo != null)
				.filter(lpo -> lpo.requirements.lp != 0)
				.toList();
	}

}
