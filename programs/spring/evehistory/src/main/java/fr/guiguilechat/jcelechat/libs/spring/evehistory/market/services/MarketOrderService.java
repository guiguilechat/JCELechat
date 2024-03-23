package fr.guiguilechat.jcelechat.libs.spring.evehistory.market.services;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.repositories.MarketOrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketOrderService {

	private final MarketOrderRepository repo;

	public void createMissingOrders(MarketFetchResult result) {
		repo.createMissingOrders(result.getId());
	}

	public void saveAll(Iterable<MarketOrder> updatedOrders) {
		repo.saveAll(updatedOrders);
	}


}
