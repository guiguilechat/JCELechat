package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class Blueprint extends TypeRef<fr.guiguilechat.jcelechat.model.sde.types.Blueprint> {

	private static final Logger logger = LoggerFactory.getLogger(Blueprint.class);

	// loading/dumping

	private static LinkedHashMap<Integer, Blueprint> cache = null;

	public static final String RESOURCE_PATH = "SDE/industry/blueprints.yaml";

	public static synchronized LinkedHashMap<Integer, Blueprint> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					Blueprint.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				LoaderOptions options = new LoaderOptions();
				options.setCodePointLimit(Integer.MAX_VALUE);
				cache = new Yaml(options).loadAs(reader, Container.class).blueprints;
			} catch (Exception exception) {
				throw new RuntimeException(exception);
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
					cacheById = load().entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, Entry::getValue));
				}
			}
		}
		return cacheById;
	}

	private static Set<Integer> missingBPIds = Collections.synchronizedSet(new HashSet<>());

	public static Blueprint of(int bpid) {
		Blueprint ret = loadById().get(bpid);
		if (ret == null && missingBPIds.add(bpid)) {
			logger.warn("unknown bp " + bpid);
		}
		return ret;
	}

	public static File export(LinkedHashMap<Integer, Blueprint> data, File folderout) {
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
		return output;
	}

	private static final class Container {
		public LinkedHashMap<Integer, Blueprint> blueprints;
	}

	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	//
	// archive management
	//

	public static String instant2ArchiveName(Instant instant) {
		return DTF.format(instant.atOffset(ZoneOffset.UTC)) + ".yaml";
	}

	private static final Pattern FILENAME_PATTERN = Pattern.compile("(.*)\\.yaml");

	public static Instant archiveName2Instant(String archiveName) {
		Matcher m = FILENAME_PATTERN.matcher(archiveName);
		if (!m.matches()) {
			return null;
		}
		try {
			LocalDateTime ld = LocalDateTime.parse(m.group(1), DTF);
			return ld.atOffset(ZoneOffset.UTC).toInstant();
		} catch (Exception e) {
			return null;
		}

	}

	//
	// structure
	//

	/**
	 * used in the blueprints as requirement
	 */
	public static class MaterialReq<T extends EveType> extends TypeRef<T> {
		public int quantity;

		@Override
		protected String makeString() {
			return name() + (quantity != 1 ? " ×" + quantity : "") + " (id=" + id + ")";
		}
	}

	public static class MaterialProd<T extends EveType> extends MaterialReq<T> {
		public float probability = 1.0f;

		@Override
		protected String makeString() {
			return name() + (quantity != 1 ? " ×" + quantity : "") + " (id=" + id + ")" + (probability < 1.0f
					? "(p=" + probability + ")"
							: "");
		}

	}

	@SuppressWarnings("rawtypes")
	public static class Activity {
		public ArrayList<MaterialReq> materials = new ArrayList<>();
		public ArrayList<MaterialProd> products = new ArrayList<>();
		public LinkedHashMap<TypeRef<Skill>, Integer> skills = new LinkedHashMap<>();
		/**
		 * time in seconds to execute one run of that activity.
		 */
		public int time;

		public Activity() {
		}

		public Map<Integer, Long> materialsMap() {
			return materials.stream().collect(Collectors.toMap(mt -> mt.id, mt -> (long) mt.quantity));
		}

		@Override
		public String toString() {
			return "products" + products + " from " + materials;
		}
	}

	/** enum of possible activities with a BP */
	public enum BP_ACTIVITIES {
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

	public double makeEIV(Function<Integer, Double> adjustedPrices) {
		if (manufacturing == null || manufacturing.materials == null) {
			logger.warn("no manufacturing for BP " + name());
			return 0.0;
		}
		double ret = 0;
		StringBuilder sb = new StringBuilder();
		for (MaterialReq<?> mat : manufacturing.materials) {
			if (mat == null) {
				sb.append("null:0");
			} else {
				double adjustedPrice = adjustedPrices.apply(mat.id);
				double eivPart = adjustedPrice * mat.quantity;
				ret += eivPart;
				sb.append(mat.name()).append(":").append((long) adjustedPrice).append("[×").append(mat.quantity).append("=")
				.append((long) eivPart).append("]");
			}
		}
		if (ret == Double.POSITIVE_INFINITY) {
			System.err.println("infinite estimation for " + id + "(" + name() + ") because :");
			for (MaterialReq<?> mat : manufacturing.materials) {
				double adj = adjustedPrices.apply(mat.id);
				if (adj == Double.POSITIVE_INFINITY) {
					System.out
					.println("  value for id=" + mat.id + "(" + mat.name() + ") = " + adj);
				}
			}
		}
		logger.debug("EIV of BP " + name() + "=" + (long) ret + " " + sb.toString());
		return ret;
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
		for (TypeRef<Skill> s : manufacturing.skills.keySet()) {
			if (s.name().contains("Engineering") || s.name().contains("Physics")) {
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
	protected String makeString() {
		return name() + "(" + id + ") copy=" + copying + " manuf=" + manufacturing + " invent=" + invention + " ME="
				+ research_material + " TE=" + research_time + " reaction=" + reaction;
	}

	/**
	 * get the amount of item that are produced, per run of the bp.
	 *
	 * @param bpId
	 *          the id of the bp that is being used for manufacturing.
	 * @return the number of items produced by one run of the bp.
	 */
	public static int productQtty(int bpId) {
		Blueprint bp = of(bpId);
		if (bp == null || bp.manufacturing == null || bp.manufacturing.products == null
				|| bp.manufacturing.products.isEmpty()) {
			return 0;
		}
		return bp.manufacturing.products.get(0).quantity;
	}

	private transient MaterialProd<?> product = null;

	private MaterialProd<?> product() {
		if (product == null) {
			product = manufacturing != null && !manufacturing.products.isEmpty() ? manufacturing.products.get(0) : null;
			if (product == null) {
				product = reaction != null && !reaction.products.isEmpty() ? reaction.products.get(0) : null;
			}
			if (product == null) {
				System.err.println("no product for " + name() + "(" + id + ")");
			}
		}
		return product;
	}


	private transient String productCategory;

	public String productCategory() {
		if (productCategory == null) {
			productCategory = product() != null ? product().category() : null;
		}
		return productCategory;
	}

	private transient String productGroup;

	public String productGroup() {
		if (productGroup == null) {
			productGroup = product() != null ? product().group() : null;
		}
		return productGroup;
	}

}
