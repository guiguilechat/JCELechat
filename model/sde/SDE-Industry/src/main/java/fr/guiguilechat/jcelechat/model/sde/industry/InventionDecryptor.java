package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.lelouet.tools.settings.yaml.CleanRepresenter;
import fr.lelouet.tools.settings.yaml.YAMLTools;

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
			try (InputStreamReader reader = new InputStreamReader(
					InventionDecryptor.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).decryptors;
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

	////
	// interaction with invention
	////

	/**
	 * get the effective ME of an invented blueprint from a copy of given one
	 *
	 * @param target
	 *          the blueprint copy
	 * @return the effective ME, if success
	 */
	public int getMe(Blueprint target) {
		return 2 + me;
	}

	/**
	 * get the effective TE of an invented blueprint from a copy of given one
	 *
	 * @param target
	 *          the blueprint copy
	 * @return the effective TE, if success
	 */
	public int getTe(Blueprint target) {
		return 4 + te;
	}

	/**
	 * get the effective number of runs of an invented blueprint from a copy of
	 * given one
	 *
	 * @param target
	 *          the blueprint copy
	 * @return the effective number of runs, if success
	 */
	public int getMaxRuns(Blueprint target) {
		return target.invention.products.get(0).quantity + maxrun;
	}

	/**
	 * get the probability of success of inventing a product bpc from a copy of a
	 * blueprint
	 *
	 * @param target
	 *          the blueprint copy
	 * @param invented
	 *          one of the products of the invention from target
	 * @param skills
	 *          the skills of the inventer
	 * @return the probability (base 1) to success based only on the parameters
	 *         given.
	 */
	public double getProbability(Blueprint target, MaterialProd invented, Map<String, Integer> skills) {
		if (skills == null) {
			System.err.println("skills are null, throwing exception");
			throw new NullPointerException("skills null");
		}
		int engSkills = target.invention.skills.keySet().stream().filter(s -> !s.contains("Encryption"))
				.mapToInt(n -> skills.getOrDefault(n, 0)).sum();
		int encSkill = target.invention.skills.keySet().stream().filter(s -> s.contains("Encryption"))
				.mapToInt(n -> skills.getOrDefault(n, 0)).sum();
		return Math.min(1.0, invented.probability * (1.0 + engSkills * 1.0 / 30 + encSkill * 1.0 / 40) * probmult);
	}

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
