package fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.EntityUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.LocalEntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.prices")
public class PriceUpdater
		extends LocalEntityUpdater<
			Price,
			Integer,
			PriceRepository,
			PriceService
		>{

	@Lazy
	private final TypeService typeService;

	private String lastEtag = null;

	@Override
	public long nbToUpdate() {
		return 0;
	}

	@Override
	protected boolean fetchUpdate() {
		int remainErrors = globalErrors().availErrors();
		if (remainErrors <= getUpdate().getErrorsMin()) {
			log.trace("{} skip updates as only {} remaining errors", serviceName(), remainErrors);
			return false;
		}
		setNextUpdate(null);
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_markets_prices[]> prices = ESIRawPublic.INSTANCE.get_markets_prices(properties);
		processEsiResponse(prices);
		if (prices.isOk()) {
			Map<Integer, Type> idToType = typeService.createIfAbsent(
					Stream.of(prices.getOK()).map(p -> p.type_id).distinct().toList());
			repo().deleteAllInBatch();
			saveAll(
					Stream.of(prices.getOK()).map(
							p -> Price.of(idToType.get(p.type_id), p.adjusted_price, p.average_price))
							.toList());
			lastEtag = prices.getETag();
			setNextUpdate(prices.getExpiresInstant());
			return true;
		} else {
			if (prices.getResponseCode() != 304) {
				log.warn("fetching prices, received reponse code {} and error {}", prices.getResponseCode(),
						prices.getError());
			}
			return false;
		}
	}

	// cache

	public interface PriceListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<PriceListener>> listeners;

}
