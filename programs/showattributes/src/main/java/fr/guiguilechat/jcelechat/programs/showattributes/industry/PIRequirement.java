package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;

public class PIRequirement {

	public static class MonthEval {

		private final RegionalMarket rm;

		private final LocalDate start;

		public MonthEval(RegionalMarket rm, LocalDate start) {
			this.rm = rm;
			this.start = start;
		}

		public long volume(int id) {
			List<R_get_markets_region_id_history> history = rm.getHistory(id).limit(start, start.plusMonths(1));
			return history.stream().mapToLong(h -> h.volume).sum();
		}

		public double avgPrice(int id) {
			List<R_get_markets_region_id_history> history = rm.getHistory(id).limit(start, start.plusMonths(1));
			long vol = history.stream().mapToLong(h -> h.volume).sum();
			double isks = history.stream().mapToDouble(h -> h.average * h.volume).sum();
			return isks / vol;
		}

		public double value(int id, Map<Integer, Float> requirements) {
			return requirements.entrySet().parallelStream().mapToDouble(e -> e.getValue() * avgPrice(e.getKey())).sum()
					* volume(id);
		}

		public void load(int id) {
			rm.getHistory(id);
		}
	}

	public static void main(String[] args) {
		PlanetaryCommodities[] PIItems = PlanetaryCommodities.METACAT.load().values().stream()
				.toArray(PlanetaryCommodities[]::new);
		MonthEval monthVol = new MonthEval(ESIAccess.INSTANCE.markets.getMarket(10000002), LocalDate.now().minusMonths(1));
		MonthEval yearVol = new MonthEval(ESIAccess.INSTANCE.markets.getMarket(10000002),
				LocalDate.now().minusYears(1));

		System.out.print("\t\t\t\t\t\t");
		for (PlanetaryCommodities pc : PIItems) {
			System.out.print("\t" + pc.name);
		}
		System.out.println();
		System.out.print("id\tname\tmonthVol\t-1yVol\tiskMonth\tisk-1y");
		for (PlanetaryCommodities pc : PIItems) {
			System.out.print("\t" + pc.id);
			monthVol.load(pc.id);
			yearVol.load(pc.id);
		}
		System.out.println();

		Map<MaterialProd<?>, Map<Integer, Float>> requirements = new HashMap<>();
		for (Blueprint bp : Blueprint.load().values()) {
			if (bp.manufacturing != null && bp.manufacturing.materials != null && !bp.manufacturing.materials.isEmpty()) {
				for (MaterialProd<?> pr : bp.manufacturing.products) {
					if (IndustryUsage.of(pr.id).materialInManuf.isEmpty()) {
						Map<Integer, Float> reqs = requirement(pr.id);
						if (reqs.keySet().stream().map(id -> TypeIndex.getType(id))
								.filter(type -> type.getCategory() == PlanetaryCommodities.METACAT)
								.findAny().isPresent()) {
							requirements.put(pr, reqs);
							monthVol.load(pr.id);
							yearVol.load(pr.id);
						}
					}
				}
			}
		}

		List<Entry<MaterialProd<?>, Map<Integer, Float>>> entries = new ArrayList<>(requirements.entrySet());
		Collections.sort(entries, Comparator.comparing(e -> e.getKey().name()));

		for (Entry<MaterialProd<?>, Map<Integer, Float>> e : entries) {
			int id = e.getKey().id;
			Map<Integer, Float> reqs = e.getValue();
			System.out.print(
					"" + id + "\t" + e.getKey().name() + "\t" + monthVol.volume(id) + "\t" + yearVol.volume(id) + "\t"
							+ monthVol.value(id, reqs) + "\t" + yearVol.value(id, reqs));
			for (PlanetaryCommodities pc : PIItems) {
				System.out.print("\t" + String.format("%.2f", reqs.getOrDefault(pc.id, 0.0f)));
			}
			System.out.println();
		}
	}

	private static final Map<Integer, Map<Integer, Float>> cacheReqs = new HashMap<>();

	public static Map<Integer, Float> requirement(int typeId) {
		Map<Integer, Float> ret = cacheReqs.get(typeId);
		if (ret == null) {
			IndustryUsage usage = IndustryUsage.of(typeId);
			if (usage != null && usage.productOfManuf != null && !usage.productOfManuf.isEmpty()) {
				ret = new HashMap<>();
				EveType type = TypeIndex.getType(typeId);
				int meta = MetaLevelOld.INSTANCE.value(type);
				boolean isT2 = meta==5;
				Blueprint bp = Blueprint.of(usage.productOfManuf.iterator().next());
				if (bp != null && bp.manufacturing != null && bp.manufacturing.materials != null
						&& !bp.manufacturing.materials.isEmpty()) {
					int maxruns = bp.maxProdRuns(0.5);
					int products = bp.manufacturing.products.get(0).quantity * maxruns;
					float maltMut = isT2?0.95f:0.9f;
					for (MaterialReq<?> requiredItem : bp.manufacturing.materials) {
						int required = requiredItem.quantity == 1 ? maxruns
								: (int) Math.ceil(maltMut * maxruns * requiredItem.quantity);
						Map<Integer, Float> reqs = requirement(requiredItem.id);
						for (Entry<Integer, Float> entry : reqs.entrySet()) {
							ret.put(entry.getKey(), ret.getOrDefault(entry.getKey(), 0.0f) + entry.getValue() * required / products);
						}
					}
				}
			} else {
				ret = new HashMap<>();
				ret.put(typeId, 1.0f);
			}
			cacheReqs.put(typeId, ret);
		}
		return ret;
	}

}
