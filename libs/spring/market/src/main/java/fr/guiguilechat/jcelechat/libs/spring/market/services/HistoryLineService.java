package fr.guiguilechat.jcelechat.libs.spring.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryLine;
import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.HistoryLineRepository;

@Service
public class HistoryLineService {

	@Autowired
	private HistoryLineRepository repo;

	public void saveAll(Iterable<HistoryLine> entities) {
		repo.saveAll(entities);
	}

	public HistoryLine save(HistoryLine entity) {
		return repo.save(entity);
	}

	public void clearFor(HistoryReq req) {
		repo.deleteByReq(req);
	}

}
