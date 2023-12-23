package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketOrderRepository;

@Service
public class MarketOrderService {

	@Autowired
	MarketOrderRepository repo;

	public void createMissingOrders(MarketFetchResult result) {
		repo.createMissingOrders(result.getId());
	}

	public void saveAll(Iterable<MarketOrder> updatedOrders) {
		repo.saveAll(updatedOrders);
	}


}
