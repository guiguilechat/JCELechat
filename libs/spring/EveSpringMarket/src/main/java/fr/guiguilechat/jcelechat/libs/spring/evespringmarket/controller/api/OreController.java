package fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.api;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
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

@RestController
@RequestMapping("/api")
public class OreController {

	public OreController() {
		// load the forge
		ESIAccess.INSTANCE.markets.getMarket(10000002);
		// load domain
		ESIAccess.INSTANCE.markets.getMarket(10000043);
	}

	public static enum Security {
		hs(Plagioclase.METAGROUP, Pyroxeres.METAGROUP, Scordite.METAGROUP, Veldspar.METAGROUP), ls(Hedbergite.METAGROUP,
				Hemorphite.METAGROUP, Jaspet.METAGROUP, Kernite.METAGROUP, Omber.METAGROUP, Plagioclase.METAGROUP,
				Pyroxeres.METAGROUP), ns, ws, ks, all;

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

		public Prices() {
		}

		public Prices(float bo, float so, float month, float week) {
			this.bo = bo;
			this.so = so;
			this.month = month;
			this.week = week;
		}

		public Prices(double bo, double so, double avg, double week) {
			this((float) bo, (float) so, (float) avg, (float) week);
		}

		public Prices(RegionalMarket market, int typeid, long qtty, float volume) {
			this(market.getBO(typeid, qtty).get() / volume, market.getSO(typeid, qtty).get() / volume,
					market.getHistory(typeid).monthly.getAverage().get() * qtty / volume,
					market.getHistory(typeid).weekly.getAverage().get() * qtty / volume);
		}
	}

	public static class OreEval {
		public Prices raw, reprocessed, compressed;
		public float volume;
		public String name;
		public int id;
		public long quantity;
	}

	@RequestMapping("/ore")
	public OreEval[] getOres(Optional<Security> security, Optional<Integer> regionid, Optional<Integer> minvol) {
		Security sec = security.orElse(Security.hs);
		Stream.of(sec.groups());
		List<Asteroid> ores = Stream.of(sec.groups()).flatMap(group -> group.load().values().stream())
				.filter(ast -> ast.published && ast.marketGroup != 0)
				.filter(ast -> IndustryUsage.of(ast.id).compressFrom == 0)
				.sorted(Comparator.comparing(a -> a.name))
				.collect(Collectors.toList());
		RegionalMarket market = ESIAccess.INSTANCE.markets.getMarket(regionid.orElse(10000002));
		int minVol = minvol.orElse(1);
		return ores.stream().map(ore -> eval(ore, market, minVol)).toArray(OreEval[]::new);
	}

	protected OreEval eval(Asteroid ore, RegionalMarket market, int minvol) {
		OreEval ret = new OreEval();
		ret.name = ore.name;
		ret.id = ore.id;
		ret.quantity = (long) Math.max(Math.ceil(minvol / ore.volume), 1);
		ret.volume = (float) (ret.quantity * ore.volume);
		ret.raw = new Prices(market, ore.id, ret.quantity, ret.volume);
		IndustryUsage usage = IndustryUsage.of(ore.id);
		if (usage.compressTo != 0) {
			Asteroid compressed = (Asteroid) TypeIndex.getType(usage.compressTo);
			if (compressed != null) {
				double chunkSize = ore.valueSet(CompressionQuantityNeeded.INSTANCE).doubleValue();
				long chunks = (long) Math.ceil(ret.quantity / chunkSize);
				float modifVolume = (float) (chunks * chunkSize * ore.volume);
				ret.compressed = new Prices(market, compressed.id, chunks, modifVolume);
			}
		}
		return ret;
	}

}
