package fr.guiguilechat.jcelechat.libs.spring.trade.marketranking;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.marketranking.MarketRankingRepository.RankedOffer;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketRegionService.MarketRegionListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketRankingService implements MarketRegionListener {

	private final MarketRankingRepository repo;

	@Async
	@Cacheable("rankCategoryBuyOffers")
	public CompletableFuture<List<RankedOffer>> rankCategoryBuyOffers(long locationId, int categoryId) {
		return CompletableFuture.completedFuture(repo.rankCategoryBuyOffers(locationId, categoryId));
	}

	@Async
	@Cacheable("rankCategorySellOffers")
	public CompletableFuture<List<RankedOffer>> rankCategorySellOffers(long locationId, int categoryId) {
		return CompletableFuture.completedFuture(repo.rankCategorySellOffers(locationId, categoryId));
	}

	@Async
	@Cacheable("rankGroupBuyOffers")
	public CompletableFuture<List<RankedOffer>> rankGroupBuyOffers(long locationId, int groupId) {
		return CompletableFuture.completedFuture(repo.rankGroupBuyOffers(locationId, groupId));
	}

	@Async
	@Cacheable("rankGroupSellOffers")
	public CompletableFuture<List<RankedOffer>> rankGroupSellOffers(long locationId, int groupId) {
		return CompletableFuture.completedFuture(repo.rankGroupSellOffers(locationId, groupId));
	}

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
			"rankCategoryBuyOffers",
			"rankCategorySellOffers",
			"rankGroupBuyOffers",
			"rankGroupSellOffers"
			);
}
