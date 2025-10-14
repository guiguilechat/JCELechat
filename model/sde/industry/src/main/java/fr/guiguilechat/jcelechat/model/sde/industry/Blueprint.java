package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.exports.common.ArchiveManager;
import fr.guiguilechat.jcelechat.libs.exports.common.MapIntSerializer;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Blueprint extends TypeRef<fr.guiguilechat.jcelechat.model.sde.types.Blueprint> {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<Blueprint> storage = new MapIntSerializer<>("SDE/industry/blueprints.yaml",
			Blueprint.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, Blueprint>> archives = new ArchiveManager<>(
			"SDE/industry/blueprints/",
			storage()::load);

	/**
	 * load the archived list for given date.
	 */
	public static Map<Integer, Blueprint> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing ids once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static Blueprint of(int id, Instant date) {
		Blueprint ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && missingIds.add(id)) {
			log.warn("unknown id " + id);
		}
		return ret;
	}

	public static Blueprint of(int id) {
		return of(id, null);
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
			return (products == null || products.size() == 0 ? "" : "produces=" + products + " ") + "consumes="
					+ materials;
		}
	}

	public Activity copying, invention, manufacturing, research_material, research_time, reaction;

	public int maxCopyRuns;

	public boolean seeded;

	//
	// usage
	//

	public double makeEIV(Function<Integer, Double> adjustedPrices) {
		if (manufacturing == null || manufacturing.materials == null) {
			log.warn("no manufacturing for BP " + name());
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
		log.debug("EIV of BP " + name() + "=" + (long) ret + " " + sb.toString());
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
