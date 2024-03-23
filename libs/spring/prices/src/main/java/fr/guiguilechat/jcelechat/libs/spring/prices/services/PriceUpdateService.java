package fr.guiguilechat.jcelechat.libs.spring.prices.services;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.prices.model.Price;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceUpdateService {

	final private PriceService priceService;

	final private CacheManager cacheManager;

	@Value("${prices.updater.skip:false}")
	private boolean skip;

	private String lastEtag = null;

	@Transactional
	@Scheduled(fixedRateString = "${prices.updater.fetchperiod:3600000}", initialDelayString = "${prices.updater.fetchdelay:25000}")
	public void update() {
		if (skip) {
			return;
		}
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_markets_prices[]> prices = ESIStatic.INSTANCE.get_markets_prices(properties);
		if (prices.isOk()) {
			priceService.clear();
			priceService.saveAll(
					Stream.of(prices.getOK()).map(
							p -> Price.builder()
									.typeId(p.type_id)
									.adjustedPrice(p.adjusted_price)
									.averagePrice(p.average_price)
									.build())
							.toList());
			for (String cacheName : PriceService.PRICE_VALUES_CACHES) {
				cacheManager.getCache(cacheName).clear();
			}
			log.info("updated prices");
		} else if (prices.getResponseCode() == 304) {
			// nothing to do
		} else {
			log.info("failed to get prices : " + prices.getError());
		}
	}
}
