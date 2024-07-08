package fr.guiguilechat.jcelechat.libs.spring.trade.prices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PriceUpdateService {

	final private CacheManager cacheManager;

	@Lazy
	final private PriceService priceService;

	@Lazy
	private final TypeService typeService;

	public static interface PriceUpdateListener {

		public default List<String> listPriceCaches() {
			return List.of();
		}

		public default void onPriceUpdate() {

		}

	}

	private final Optional<List<PriceUpdateListener>> pricesUpdateListeners;

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
		Requested<R_get_markets_prices[]> prices = ESIRawPublic.INSTANCE.get_markets_prices(properties);
		if (prices.isOk()) {
			Map<Integer, Type> idToType = typeService.createIfAbsent(
			    Stream.of(prices.getOK()).map(p -> p.type_id).distinct().toList());
			priceService.clear();
			priceService.saveAll(
					Stream.of(prices.getOK()).map(
							p -> Price.builder()
			            .id(idToType.get(p.type_id))
									.adjustedPrice(p.adjusted_price)
									.averagePrice(p.average_price)
									.build())
							.toList());

			if (pricesUpdateListeners.isPresent()) {
				pricesUpdateListeners.get().stream().flatMap(l -> l.listPriceCaches().stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
				pricesUpdateListeners.get().stream().forEach(PriceUpdateListener::onPriceUpdate);
			}

			log.info("updated prices");
		} else if (prices.getResponseCode() == 304) {
			// nothing to do
		} else {
			log.info("failed to get prices : " + prices.getError());
		}
	}
}
