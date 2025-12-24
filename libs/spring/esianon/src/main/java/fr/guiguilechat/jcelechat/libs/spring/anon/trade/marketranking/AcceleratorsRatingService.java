package fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking;

import java.util.Comparator;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking.MarketRankingRepository.RatedAccelerator;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketRegionService.MarketRegionListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcceleratorsRatingService implements MarketRegionListener {

	private final MarketRankingRepository repo;

	@Cacheable("rateAccelerators")
	public List<RatedAccelerator> rate(long locationId) {
		return repo.rateAccelerators(locationId)
				.stream()
				.sorted(Comparator.comparing(ar -> -ar.spPMIsk().doubleValue()))
				.toList();
	}

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
			"rateAccelerators");

}
