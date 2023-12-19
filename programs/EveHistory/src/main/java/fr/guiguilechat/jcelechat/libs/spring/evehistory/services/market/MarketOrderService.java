package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketOrderRepository;

@Service
public class MarketOrderService {

	@Autowired
	MarketOrderRepository repo;

	@Autowired
	MarketFetchResultService mfrService;

	public void createMissingOrders(MarketFetchResult result) {
		repo.createMissingOrders(result.getId());
	}

// public void updateLastLine(MarketFetchResult result) {
// repo.updateLastLine(result.getId());
// }

}
