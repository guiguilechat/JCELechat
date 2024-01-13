package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.HistoryReqRepository;

@Service
public class HistoryReqService {

	@Autowired
	private HistoryReqRepository repo;

	public List<Integer> findMissingTypeIds(ObservedRegion or) {
		return repo.findMissingTypesForRegion(or.getRegionId());
	}

	public void saveAll(Iterable<HistoryReq> entities) {
		repo.saveAll(entities);
	}

	public HistoryReq save(HistoryReq entity) {
		return repo.save(entity);
	}

	public List<HistoryReq> listNextRequests() {
		return repo.findTop200ByNextFetchLessThanOrderByNextFetch(Instant.now());
	}

	public long countRemainingRequests() {
		return repo.countByNextFetchLessThan(Instant.now());
	}

}
