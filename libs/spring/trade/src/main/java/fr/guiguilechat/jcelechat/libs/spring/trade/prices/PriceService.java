package fr.guiguilechat.jcelechat.libs.spring.trade.prices;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.trade.prices.PriceUpdateService.PriceUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUpdateListener {

	final private PriceRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Price> saveAll(Iterable<Price> entities) {
		return repo.saveAll(entities);
	}

	public Price save(Price entity) {
		return repo.save(entity);
	}

	@Cacheable("pricesAdjusted")
	public Map<Type, Double> adjusted() {
		return repo.findAll().stream().collect(Collectors.toMap(Price::getId, Price::getAdjustedPrice));
	}

	@Cacheable("pricesAverage")
	public Map<Type, Double> average() {
		return repo.findAll().stream().collect(Collectors.toMap(Price::getId, Price::getAveragePrice));
	}

	public static final List<String> PRICE_VALUES_CACHES = List.of("pricesAdjusted", "pricesAverage");

	@Override
	public List<String> listPriceCaches() {
		return PRICE_VALUES_CACHES;
	}

}
