package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.lelouet.tools.settings.yaml.CleanRepresenter;
import fr.lelouet.tools.settings.yaml.YAMLTools;

public class Blueprint extends TypeRef<fr.guiguilechat.jcelechat.model.sde.types.Blueprint> {

	// loading/dumping

	private static LinkedHashMap<Integer, Blueprint> cache = null;

	public static final String RESOURCE_PATH = "SDE/industry/blueprints.yaml";

	public static synchronized LinkedHashMap<Integer, Blueprint> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					Blueprint.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).blueprints;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	private static Map<Integer, Blueprint> cacheById = null;

	public static Map<Integer, Blueprint> loadById() {
		if (cacheById == null) {
			load();
			synchronized (cache) {
				if (cacheById == null) {
					cacheById = load().entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, e -> e.getValue()));
				}
			}
		}
		return cacheById;
	}

	public static void export(LinkedHashMap<Integer, Blueprint> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.blueprints = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<Integer, Blueprint> blueprints;
	}

	// structure

	/**
	 * used in the blueprints as requirement, or products
	 */
	public static class MaterialReq<T extends EveType> extends TypeRef<T> {
		public int quantity;

		@Override
		public String toString() {
			if (toString == null) {
				toString = "" + quantity + "×" + name() + "(" + id + ")";
			}
			return toString;
		}
	}

	public static class MaterialProd<T extends EveType> extends MaterialReq<T> {
		public float probability = 1.0f;

		@Override
		public String toString() {
			if (toString == null) {
				toString = "" + quantity + "×" + name() + "(" + id + ")(p=" + probability + ")";
			}
			return toString;
		}

	}

	@SuppressWarnings("rawtypes")
	public static class Activity {
		public ArrayList<MaterialReq> materials = new ArrayList<>();
		public ArrayList<MaterialProd> products = new ArrayList<>();
		public LinkedHashMap<String, Integer> skills = new LinkedHashMap<>();
		public int time;

		public Activity() {
		}

		@Override
		public String toString() {
			return "products" + products + " from " + materials;
		}
	}

	/** enum of possible activities with a BP */
	public static enum BP_ACTIVITIES {
		COPYING {

			@Override
			public Activity get(Blueprint bp) {
				return bp.copying;
			}

		},
		INVENTION {

			@Override
			public Activity get(Blueprint bp) {
				return bp.invention;
			}

		},
		MANUFACTURING {

			@Override
			public Activity get(Blueprint bp) {
				return bp.manufacturing;
			}

		},
		ME {

			@Override
			public Activity get(Blueprint bp) {
				return bp.research_material;
			}

		},
		TE {

			@Override
			public Activity get(Blueprint bp) {
				return bp.research_time;
			}

		},
		REACTION {

			@Override
			public Activity get(Blueprint bp) {
				return bp.reaction;
			}

		};

		public abstract Activity get(Blueprint bp);
	}

	public Activity copying, invention, manufacturing, research_material, research_time, reaction;

	public int maxCopyRuns;

	public boolean seeded;

	public double makeEIV(Function<Integer, Double> getAdjusted) {
		if (manufacturing == null || manufacturing.materials == null) {
			return 0.0;
		}
		return manufacturing.materials.parallelStream()
				.mapToDouble(mat -> mat == null ? 0 : mat.quantity * getAdjusted.apply(mat.id)).sum();
	}

	/**
	 * get the number of runs that can be started for this blueprint, with given
	 * ME mult.
	 *
	 * @param teMult
	 *          the time efficiency multiplier applied. depends on skill,
	 *          structure, research, implants.
	 * @return
	 */
	public int maxProdRuns(double teMult) {
		if (manufacturing == null) {
			return 0;
		}
		return (int) Math.ceil(30 * 24 * 3600 / teMult / manufacturing.time);
	}

	/**
	 * get the lowest TE mult achievable for this blueprint for a given structure
	 * mult
	 *
	 * @return the te mult achieved with best implants, skills, given structure,
	 *         and researched bp.
	 */
	public double bestTeMult(double structMult) {
		if (manufacturing == null) {
			return 0;
		}
		double temult =
				// 20 TE
				0.8 * structMult
				// industry and advanced industry give -32%
				* 0.78
				// bx-804 reduces by 4%
				* 0.96;
		// all engineering and physics skills have a 5% te bonus
		for (String s : manufacturing.skills.keySet()) {
			if (s.contains("Engineering") || s.contains("Physics")) {
				temult *= 0.95;
			}
		}
		return temult;
	}

	/**
	 * get the lowest TE mult achievable for this blueprint in NS
	 *
	 * @return the te mult achieved with best implants, skills, structure, and
	 *         researched bp.
	 */
	public double bestTeMultNS() {
		return bestTeMult(
				// sotiyo rig is -50.4
				0.496
				// sotiyo bonus is -30%
				* 0.7);
	}

	/**
	 * get the lowest TE mult achievable for this blueprint in LS
	 *
	 * @return the te mult achieved with best implants, skills, structure, and
	 *         researched bp.
	 */
	public double bestTeMultLS() {
		return bestTeMult(
				// sotiyo rig is -45.6
				0.544
				// sotiyo bonus is -30%
				* 0.7);
	}

	/**
	 * get the lowest TE mult achievable for this blueprint in HS
	 *
	 * @return the te mult achieved with best implants, skills, structure, and
	 *         researched bp.
	 */
	public double bestTeMultHS() {
		return bestTeMult(
				// sotiyo rig is -24
				0.76
				// sotiyo bonus is -30%
				* 0.7);
	}

	public int maxProdRuns() {
		return maxProdRuns(bestTeMultNS());
	}

	public boolean isMEUseless() {
		if (manufacturing == null) {
			return true;
		}
		int maxruns = maxProdRuns();
		return manufacturing.materials.stream()
				.filter(
						mat -> mat.quantity > 1 && Math.ceil(0.9 * 0.99 * 0.9496 * mat.quantity * maxruns) < mat.quantity * maxruns)
				.findAny().isEmpty();
	}

	@Override
	public String toString() {
		return name() + "(" + id + ") copy=" + copying + " manuf=" + manufacturing + " invent=" + invention + " ME="
				+ research_material + " TE=" + research_time + " reaction=" + reaction;
	}

}
