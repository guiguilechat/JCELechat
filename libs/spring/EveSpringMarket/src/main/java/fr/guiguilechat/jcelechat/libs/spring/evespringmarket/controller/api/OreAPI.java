package fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.attributes.CompressionQuantityNeeded;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hedbergite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hemorphite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Jaspet;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Kernite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Omber;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Plagioclase;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Pyroxeres;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Scordite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Veldspar;
import fr.guiguilechat.jcelechat.model.sde.types.material.Mineral;

@RestController
@RequestMapping("/api/ore")
public class OreAPI {

	public OreAPI() {
		// load the forge
		var theforge = ESIAccess.INSTANCE.markets.getMarket(10000002);
		// load domain
		var domain = ESIAccess.INSTANCE.markets.getMarket(10000043);
		for (Mineral m : Mineral.METAGROUP.load().values()) {
			theforge.getHistory(m.id);
			domain.getHistory(m.id);
		}
	}

	public static enum Security {
		hs(Plagioclase.METAGROUP, Pyroxeres.METAGROUP, Scordite.METAGROUP, Veldspar.METAGROUP), ls(Hedbergite.METAGROUP,
				Hemorphite.METAGROUP, Jaspet.METAGROUP, Kernite.METAGROUP, Omber.METAGROUP, Plagioclase.METAGROUP,
				Pyroxeres.METAGROUP), ns, ws, ks, @SuppressWarnings("unchecked")
		all(Stream.of(hs.groups, ls.groups).flatMap(Stream::of).toArray(IMetaGroup[]::new));

		private IMetaGroup<? extends Asteroid>[] groups;

		public IMetaGroup<? extends Asteroid>[] groups() {
			return groups;
		}

		@SafeVarargs
		private Security(IMetaGroup<? extends Asteroid>... groups) {
			this.groups = groups;
		}
	}

	public static class Prices {
		public float bo, so, month, week;
		public long qtty;
		public float volume;

		public Prices() {
		}

		public Prices(float bo, float so, float month, float week, long qtty, float volume) {
			this.bo = bo;
			this.so = so;
			this.month = month;
			this.week = week;
			this.qtty = qtty;
			this.volume = volume;
		}

		public Prices(double bo, double so, double avg, double week, long qtty, float volume) {
			this((float) bo, (float) so, (float) avg, (float) week, qtty, volume);
		}

		public Prices(RegionalMarket market, int typeid, long qtty, float volume) {
			this(market.getBO(typeid, qtty).get() / volume, market.getSO(typeid, qtty).get() / volume,
					market.getHistory(typeid).monthly.getAverage().get() * qtty / volume,
					market.getHistory(typeid).weekly.getAverage().get() * qtty / volume, qtty, volume);
		}

		public Prices(RegionalMarket market, EveType type, long qtty) {
			this(market, type.id, qtty, (float) (type.volume * qtty));
		}
	}

	public static class OreEval {
		public String name;
		public int id;
		public Prices raw, compressed, reprocessed, highest;
	}

	public static enum Orderer implements Comparator<OreEval> {
		compr_so {
			@Override
			public int compare(OreEval o1, OreEval o2) {
				return o1.compressed != null && o2.compressed != null ? (int) (o2.compressed.so - o1.compressed.so) : 0;
			}
		},
		compr_bo {
			@Override
			public int compare(OreEval o1, OreEval o2) {
				return o1.compressed != null && o2.compressed != null ? (int) (o2.compressed.bo - o1.compressed.bo) : 0;
			}
		},
		raw_so {
			@Override
			public int compare(OreEval o1, OreEval o2) {
				return o1.raw != null && o2.raw != null ? (int) (o2.raw.so - o1.raw.so) : 0;
			}
		},
		raw_bo {
			@Override
			public int compare(OreEval o1, OreEval o2) {
				return o1.raw != null && o2.raw != null ? (int) (o2.raw.bo - o1.raw.bo) : 0;
			}
		},;

		@Override
		public abstract int compare(OreEval o1, OreEval o2);

	}

	@RequestMapping("/volumic")
	public OreEval[] volumic(Optional<Security> sec, Optional<Integer> regionid, Optional<Long> minvol,
			Optional<String> filter, Optional<Orderer> sort, Optional<Float> eff, Optional<String>allowNoOffer) {
		Security secu = sec.orElse(Security.all);
		Stream.of(secu.groups());
		String filt = filter.isEmpty() ? null : filter.get().toLowerCase();
		boolean allowNoSO = allowNoOffer.isPresent();
		List<Asteroid> ores = Stream.of(secu.groups()).flatMap(group -> group.load().values().stream())
				.filter(ast -> ast.published && ast.marketGroup != 0)
				.filter(ast -> IndustryUsage.of(ast.id).compressFrom == 0)
				.filter(ast -> filt == null || ast.name.toLowerCase().contains(filt))
				.sorted(Comparator.comparing(a -> a.name))
				.collect(Collectors.toList());
		RegionalMarket market = ESIAccess.INSTANCE.markets.getMarket(regionid.orElse(10000002));
		long minVol = minvol.orElse(1l);
		float efficiency = eff.orElse(0.5f);
		OreEval[] ret = ores.stream().map(ore -> eval(ore, market, minVol, efficiency))
				.filter(eval -> allowNoSO || eval.raw.so != Float.POSITIVE_INFINITY).toArray(OreEval[]::new);
		Orderer sorter = sort.orElse(Orderer.compr_bo);
		Arrays.sort(ret, sorter);
		return ret;
	}

	protected OreEval eval(Asteroid ore, RegionalMarket market, long minvol, float efficiency) {
		OreEval ret = new OreEval();
		ret.name = ore.name;
		ret.id = ore.id;
		long baseQtty = (long) Math.max(Math.ceil(minvol / ore.volume), 1);
		ret.raw = new Prices(market, ore, baseQtty);
		ret.highest = new Prices(market, ore, baseQtty);
		IndustryUsage usage = IndustryUsage.of(ore.id);
		if (usage.compressTo != 0) {
			Asteroid compressed = (Asteroid) TypeIndex.getType(usage.compressTo);
			if (compressed != null) {
				double chunkSize = ore.valueSet(CompressionQuantityNeeded.INSTANCE).doubleValue();
				long chunks = (long) Math.ceil(baseQtty / chunkSize);
				float modifVolume = (float) (chunks * chunkSize * ore.volume);
				ret.compressed = new Prices(market, compressed.id, chunks, modifVolume);
				ret.highest.bo = Math.max(ret.highest.bo, ret.compressed.bo);
				ret.highest.so = Math.max(ret.highest.so, ret.compressed.so);
				ret.highest.month = Math.max(ret.highest.month, ret.compressed.month);
				ret.highest.week = Math.max(ret.highest.week, ret.compressed.week);
			}
		}
		if (usage.reprocessInto != null && !usage.reprocessInto.isEmpty()) {
			long portions = (long) Math.ceil(1.0 * baseQtty / ore.portionSize);
			float modifVolume = (float) (portions * ore.portionSize * ore.volume);
			// System.err.println(ore.name + " baseQtty=" + baseQtty + " portions=" +
			// portions + " vol=" + modifVolume);
			Prices repr = new Prices();
			repr.volume = modifVolume;
			repr.qtty = portions * ore.portionSize;
			for (Entry<Integer, Double> e : usage.reprocessInto.entrySet()) {
				int rid = e.getKey();
				long rqtty = (long) Math.floor(efficiency * e.getValue() * ore.portionSize * portions);
				repr.bo += market.getBO(rid, rqtty).get() / modifVolume;
				repr.so += market.getSO(rid, rqtty).get() / modifVolume;
				repr.month += market.getHistory(rid).monthly.getAverage().get() * rqtty / modifVolume;
				repr.week += market.getHistory(rid).weekly.getAverage().get() * rqtty / modifVolume;
			}
			ret.reprocessed = repr;
			ret.highest.bo = Math.max(ret.highest.bo, ret.reprocessed.bo);
			ret.highest.so = Math.max(ret.highest.so, ret.reprocessed.so);
			ret.highest.month = Math.max(ret.highest.month, ret.reprocessed.month);
			ret.highest.week = Math.max(ret.highest.week, ret.reprocessed.week);
		}
		return ret;
	}

	@RequestMapping("/**")
	public String fallback() {
		return "syntax : volumic?[&amp;sec=" + Stream.of(Security.values()).collect(Collectors.toSet())
				+ "(all)][&amp;regionid=regionid(10000002)][&amp;minvol=vol(1)][&amp;filter=namefilter()][&amp;sort="
				+ Stream.of(Orderer.values()).collect(Collectors.toSet())
				+ "()][&amp;eff=reproceff(0.5)][&amp;allowNoOffer()]";
	}

}
