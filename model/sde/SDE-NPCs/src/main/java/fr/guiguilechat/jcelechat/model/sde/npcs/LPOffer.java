package fr.guiguilechat.jcelechat.model.sde.npcs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class LPOffer {

	// loading/dumping

	private static LinkedHashMap<Integer, LPOffer> cache = null;

	public static final String RESOURCE_PATH = "SDE/npcs/lpoffers.yaml";

	public static synchronized LinkedHashMap<Integer, LPOffer> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					LPOffer.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).offers;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<Integer, LPOffer> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.offers = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting corporations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<Integer, LPOffer> offers;
	}

	// structure

	public static class ItemRef {
		public String itemname;
		public int itemid;
		public int quantity;
	}

	public ItemRef product = new ItemRef();

	public static class Requirements {
		public ArrayList<ItemRef> items = new ArrayList<>();
		public int isk;
		public int lp;
	}

	public Requirements requirements = new Requirements();

	public String name;
	public int id;

	@Override
	public String toString() {
		return name;
	}

}
