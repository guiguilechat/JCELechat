package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;
import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

/**
 * decryptors used in invention.
 *
 */
public class InventionDecryptor extends TypeRef<GenericDecryptor> {

	private static final Logger logger = LoggerFactory.getLogger(InventionDecryptor.class);

	// loading/dumping

	private static LinkedHashMap<Integer, InventionDecryptor> cache = null;

	private static GenericDecryptor NULLDECRYPTOR = new GenericDecryptor();
	static {
		NULLDECRYPTOR.name = "no decryptor";
		NULLDECRYPTOR.inventionpropabilitymultiplier = 1.0f;
	}

	public static final String RESOURCE_PATH = "SDE/industry/decryptors.yaml";

	public static synchronized LinkedHashMap<Integer, InventionDecryptor> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					InventionDecryptor.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).decryptors;
			} catch (Exception exception) {
				throw new RuntimeException(exception);
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
	 *          the blueprint copy, can(should) be null
	 * @return the effective ME, if success
	 */
	public int getMe(Blueprint... target) {
		return 2 + me();
	}

	/**
	 * get the effective TE of an invented blueprint from a copy of given one
	 *
	 * @param target
	 *          the blueprint copy, can(should) be null
	 * @return the effective TE, if success
	 */
	public int getTe(Blueprint... target) {
		return 4 + te();
	}

	/**
	 * get the effective number of runs of an invented blueprint from a copy of
	 * given one
	 *
	 * @param target
	 *          the blueprint copy t1
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
	public double getProbability(Blueprint target, MaterialProd<?> invented, Map<Integer, Integer> skills) {
		if (skills == null) {
			skills = Collections.emptyMap();
		}
		int skillsProbaPoints_base120 = 120;

		for (TypeRef<Skill> skillRef : target.invention.skills.keySet()) {
			Skill sk = skillRef.type();
			int skillMult = inventionProbaIncr_base120(sk);
			if (skillMult != 0) {
				skillsProbaPoints_base120 += skillMult * skills.getOrDefault(sk.id, 0);
			}
		}

		float skillsProbaMult = 1.0f * skillsProbaPoints_base120 / 120;
		double ret = Math.min(1.0, invented.probability * skillsProbaMult * probmult());
		logger.trace(
		    " invent from " + target.name() + "with dec=" + name() + " gives success probability=" + ret + " bp value="
				+ invented.probability + " skills=" + skillsProbaMult + " decryptormult=" + probmult());
		return ret;
	}

	@SuppressWarnings("serial")
	public static final Map<Integer, Integer> ALL_5 = new HashMap<>() {
		public Integer get(Object key) {
			return 5;
		};
	};

	/**
	 * get the increase, in base 120 points and per skill level, of the invention
	 * probability when using a BP whose invention activity requires that skill.
	 *
	 * @param skill
	 *          the skill required
	 * @return the increase in points base 120
	 */
	public static int inventionProbaIncr_base120(Skill skill) {
		// encryption methods require hacking
		if (skill.requiredskill1 == 21718) {
			return 3;
		}
		// hardcoded sleeper encryption methods.
		if (skill.id == 3408) {
			return 3;
		}
		return 4;
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
