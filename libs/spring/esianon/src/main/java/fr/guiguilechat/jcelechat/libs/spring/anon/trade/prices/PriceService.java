package fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices.PriceUpdater.PriceListener;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.NumberEntityService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PriceService extends NumberEntityService<Price, Integer, PriceRepository>
		implements PriceListener {

	@Lazy
	private final TypeService typeService;

	@Override
	protected Price create(Integer entityId) {
		Price ret = new Price();
		ret.setId(entityId);
		ret.setType(typeService.createIfAbsent(entityId));
		return ret;
	}

	// usage

	@Cacheable("pricesAdjusted")
	public Map<Integer, Double> adjusted() {
		return repo().findAll().stream().collect(Collectors.toMap(Price::getId, Price::getAdjustedPrice));
	}

	public double adjusted(int typeId) {
		Optional<Price> fetched = repo().findById(typeId);
		return fetched.isPresent() ? fetched.get().getAdjustedPrice() : 0.0;
	}

	@Cacheable("pricesAverage")
	public Map<Integer, Double> average() {
		return repo().findAll().stream().collect(Collectors.toMap(Price::getId, Price::getAveragePrice));
	}

	public double average(int typeId) {
		Optional<Price> fetched = repo().findById(typeId);
		return fetched.isPresent() ? fetched.get().getAveragePrice() : 0.0;
	}

	// cache

	@Getter
	private final List<String> cacheList = List.of(
			"pricesAdjusted",
			"pricesAverage");

}
