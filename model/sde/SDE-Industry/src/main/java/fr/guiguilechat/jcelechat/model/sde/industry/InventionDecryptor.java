package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;

/**
 * decryptors used in invention.
 *
 */
public class InventionDecryptor {

	// loading/dumping

	private static LinkedHashMap<String, InventionDecryptor> cache = null;

	public static final String RESOURCE_PATH = "SDE/industry/decryptors.yaml";

	public static synchronized LinkedHashMap<String, InventionDecryptor> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Blueprint.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).decryptors;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<String, InventionDecryptor> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.decryptors = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, InventionDecryptor> decryptors;
	}

	// structure

	public int maxrun = 0;
	public double probmult = 1.0;
	public int id = 0;
	public int me = 0;
	public int te = 0;
	public String name;

	@Override
	public String toString() {
		return name + " id" + id + " me" + me + " te" + te + " mult" + probmult + " run" + maxrun;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			InventionDecryptor o = (InventionDecryptor) obj;
			return name.equals(o.name) && maxrun == o.maxrun && probmult == o.probmult && id == o.id && me == o.me
					&& te == o.te;
		}
		return false;
	}

}
