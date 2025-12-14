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
	@Cacheable("rankBuyOffers")
	public CompletableFuture<List<RankedOffer>> rankBuyOffers(long locationId, int groupId) {
		return CompletableFuture.completedFuture(repo.rankBuyOffers(locationId, groupId));
	}

	@Async
	@Cacheable("rankSellOffers")
	public CompletableFuture<List<RankedOffer>> rankSellOffers(long locationId, int groupId) {
		return CompletableFuture.completedFuture(repo.rankSellOffers(locationId, groupId));
	}

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
			"rankBuyOffers",
			"rankSellOffers"
			);
}
