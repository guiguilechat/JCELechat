package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
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
	public double getProbability(Blueprint target, MaterialProd<?> invented, Map<Integer, Integer> skills) {
		if (skills == null) {
			skills = Collections.emptyMap();
		}
		double skillsProbaMult = 1.0;
		for (String skillName : target.invention.skills.keySet()) {
			EveType sk = TypeIndex.getType(skillName);
			if (sk instanceof Science) {
				Science sc = (Science) sk;
				double skillMult = skillInventionProbaMult(sc);
				if (skillMult != 0) {
					skillsProbaMult += skillMult * skills.getOrDefault(sk.id, 0);
				}
			}
		}
		return Math.min(1.0, invented.probability * skillsProbaMult * probmult());
	}

	public static double skillInventionProbaMult(Science skill) {
		// physics, engineering etc skills require two skills at V
		if (skill.requiredskill1level == 5 && skill.requiredskill2level == 5) {
			return 1.0 / 30;
		}
		// encryption methods require hacking
		if (skill.requiredskill1 == 21718) {
			return 1.0 / 40;
		}
		// hardcoded sleeper encryption methods.
		if (skill.id == 3408) {
			return 1.0 / 40;
		}
		return 0.0;
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
