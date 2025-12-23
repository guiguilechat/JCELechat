package fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking.MarketRankingRepository.RankedOffer;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketRegionService.MarketRegionListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketRankingService implements MarketRegionListener {

	private final MarketRankingRepository repo;

	public enum GroupCategoryChoice {
		CATEGORY, GROUP
	}

	public enum BoSoChoice {
		BO, SO
	}

	@Async
	@Cacheable("rankCategoryOffers")
	public CompletableFuture<List<RankedOffer>> rankCategoryOffers(Iterable<Long> locationIds, int categoryId,
			BoSoChoice boso) {
		return CompletableFuture.completedFuture(
				boso == BoSoChoice.BO
						? repo.rankCategoryBuyOffers(locationIds, categoryId)
						: repo.rankCategorySellOffers(locationIds, categoryId));
	}

	@Async
	@Cacheable("rankGroupOffers")
	public CompletableFuture<List<RankedOffer>> rankGroupOffers(Iterable<Long> locationIds, int groupId,
			BoSoChoice boso) {
		return CompletableFuture.completedFuture(
				boso == BoSoChoice.BO
						? repo.rankGroupBuyOffers(locationIds, groupId)
						: repo.rankGroupSellOffers(locationIds, groupId));
	}

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
			"rankCategoryOffers",
			"rankGroupOffers"
			);
}
