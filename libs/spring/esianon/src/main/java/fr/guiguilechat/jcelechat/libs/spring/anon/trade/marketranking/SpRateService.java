package fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking.MarketRankingRepository.RatedSPGain;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketRegionUpdater.MarketRegionListener;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SpRateService implements MarketRegionListener {

	private final MarketRankingRepository repo;

	@Lazy
	private final MarketLineService marketLineService;

	@Cacheable("rateSPGain")
	public List<RatedSPGain> rate(long locationId, Optional<Long> sps) {
		List<RatedSPGain> ret =
				new ArrayList<>(repo.rateAccelerators(locationId)
						.stream()
						.filter(ar -> ar.price() != null && !ar.isExpired())
						.toList());
		Double plexPrice = marketLineService.globalSOPrice(Type.PLEX_ID);
		if (plexPrice != null) {
			int primAtt = 32;
			int secAtt = 26;
			long spGainPerMonth = 30 * 24 * 30 * (primAtt * 2 + secAtt);

			// hardcoded offers
			ret.add(new RatedSPGain(Type.PLEX_ID, "1M omega with attributes " + primAtt + "/" + secAtt, null,
					spGainPerMonth,
					500 * plexPrice));
			ret.add(new RatedSPGain(Type.PLEX_ID, "3M omega with attributes " + primAtt + "/" + secAtt, null,
					3 * spGainPerMonth,
					1200 * plexPrice));
			ret.add(new RatedSPGain(Type.PLEX_ID, "6M omega with attributes " + primAtt + "/" + secAtt, null,
					6 * spGainPerMonth,
					2100 * plexPrice));
			ret.add(new RatedSPGain(Type.PLEX_ID, "12M omega with attributes " + primAtt + "/" + secAtt, null,
					12 * spGainPerMonth,
					3600 * plexPrice));
			ret.add(new RatedSPGain(Type.PLEX_ID, "24M omega with attributes " + primAtt + "/" + secAtt, null,
					24 * spGainPerMonth,
					6600 * plexPrice));
		}

		long sp = sps == null ? 0L : sps.orElse(0L);

		Double lsiPrice = marketLineService.globalSOPrice(Type.LARGE_SKILL_INJECTOR_ID);
		if (lsiPrice != null) {
			if (sp >= 80000000) {
				ret.add(new RatedSPGain(Type.LARGE_SKILL_INJECTOR_ID, "Large Skill Injector with >80MSP", null,
						150000,
						lsiPrice));
			}
			if (sp < 80000000 && sp >= 50000000) {
				ret.add(new RatedSPGain(Type.LARGE_SKILL_INJECTOR_ID, "Large Skill Injector with 50-80MSP", null,
						300000,
						lsiPrice));
			}
			if (sp < 50000000 && sp >= 5000000) {
				ret.add(new RatedSPGain(Type.LARGE_SKILL_INJECTOR_ID, "Large Skill Injector with 5-50MSP", null,
						400000,
						lsiPrice));
			}
			if (sp < 5000000) {
				ret.add(new RatedSPGain(Type.LARGE_SKILL_INJECTOR_ID, "Large Skill Injector with <5MSP", null,
						500000,
						lsiPrice));
			}
		}

		Double ssiPrice = marketLineService.globalSOPrice(Type.SMALL_SKILL_INJECTOR_ID);
		if (ssiPrice != null) {
			if (sp >= 80000000) {
				ret.add(new RatedSPGain(Type.SMALL_SKILL_INJECTOR_ID, "Small Skill Injector with >80MSP", null,
						30000,
						ssiPrice));
			}
			if (sp < 80000000 && sp >= 50000000) {
				ret.add(new RatedSPGain(Type.SMALL_SKILL_INJECTOR_ID, "Small Skill Injector with 50-80MSP", null,
						60000,
						ssiPrice));
			}
			if (sp < 50000000 && sp >= 5000000) {
				ret.add(new RatedSPGain(Type.SMALL_SKILL_INJECTOR_ID, "Small Skill Injector with 5-50MSP", null,
						80000,
						ssiPrice));
			}
			if (sp < 5000000) {
				ret.add(new RatedSPGain(Type.SMALL_SKILL_INJECTOR_ID, "Small Skill Injector with <5MSP", null,
						100000,
						ssiPrice));
			}
		}

		ret.sort(Comparator.comparing(ar -> -ar.spPIsk()));
		return ret;
	}

	@Getter(lazy = true)
	private final List<String> cacheList =
			List.of(
					"rateSPGain");

}
