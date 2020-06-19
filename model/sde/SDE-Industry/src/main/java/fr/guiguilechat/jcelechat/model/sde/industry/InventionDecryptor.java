package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Science;
import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

/**
 * decryptors used in invention.
 *
 */
public class InventionDecryptor extends TypeRef<GenericDecryptor> {

	// loading/dumping

	private static LinkedHashMap<Integer, InventionDecryptor> cache = null;

	private static GenericDecryptor NULLDECRYPTOR = new GenericDecryptor();
	static {
		NULLDECRYPTOR.name = "no decryptor";
	}

	public static final String RESOURCE_PATH = "SDE/industry/decryptors.yaml";

	public static synchronized LinkedHashMap<Integer, InventionDecryptor> load() {
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

	public static void export(LinkedHashMap<Integer, InventionDecryptor> data, File folderout) {
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
		public LinkedHashMap<Integer, InventionDecryptor> decryptors;
	}

	// data

	@Override
	public GenericDecryptor type() {
		if (id == 0) {
			return NULLDECRYPTOR;
		}
		return super.type();
	}

	public int maxrun() {
		return (int) type().inventionmaxrunmodifier;
	}

	public double probmult() {
		return type().inventionpropabilitymultiplier;
	}

	public int me() {
		return (int) type().inventionmemodifier;
	}

	public int te() {
		return (int) type().inventiontemodifier;
	}

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
		return 2 + me();
	}

	/**
	 * get the effective TE of an invented blueprint from a copy of given one
	 *
	 * @param target
	 *          the blueprint copy
	 * @return the effective TE, if success
	 */
	public int getTe(Blueprint target) {
		return 4 + te();
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
		return target.invention.products.get(0).quantity + maxrun();
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
	 *          the skills of the inventor
	 * @return the probability (base 1) to success based only on the parameters
	 *         given.
	 */
	public double getProbability(Blueprint target, MaterialProd<?> invented, Map<String, Integer> skills) {
		if (skills == null) {
			System.err.println("skills are null, throwing exception");
			throw new NullPointerException("skills null");
		}
		int engSkills = target.invention.skills.keySet().stream().filter(s -> !s.contains("Encryption"))
				.mapToInt(n -> skills.getOrDefault(n, 0)).sum();
		int encSkill = target.invention.skills.keySet().stream().filter(s -> s.contains("Encryption"))
				.mapToInt(n -> skills.getOrDefault(n, 0)).sum();
		return Math.min(1.0, invented.probability * (1.0 + engSkills * 1.0 / 30 + encSkill * 1.0 / 40) * probmult());
	}

	private static HashMap<Science, Double> probabilityIncreaseSkills = null;

	private HashMap<Science, Double> probabilityIncreaseSkills() {
		if (probabilityIncreaseSkills == null) {
			HashMap<Science, Double> ret = new HashMap<>();
			for (Science e : Science.METAGROUP.load().values()) {
				if (e.name.contains("Encryption")) {
					ret.put(e, 2.5);
				} else if (e.name.contains("")) {

				}
			}
		}
		return probabilityIncreaseSkills;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			InventionDecryptor o = (InventionDecryptor) obj;
			return o.id == id;
		}
		return false;
	}

}
