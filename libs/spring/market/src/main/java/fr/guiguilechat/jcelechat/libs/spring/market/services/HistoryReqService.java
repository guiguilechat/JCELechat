package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.HistoryReqRepository;
import lombok.Getter;

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

	@Getter
	@Value("${market.history.fetchsize:200}")
	private int queriesPerFetch;

	/**
	 * @return the next batch of request we should submit. This is limited by
	 *           {@link #queriesPerFetch}. The requests are sorted by next fetch,
	 *           regionId, typeId ascending.
	 */
	public List<HistoryReq> listNextRequests() {
		List<HistoryReq> ret = null;
		if (queriesPerFetch < 100) {
			ret = repo.findTop100ByNextFetchLessThanOrderByNextFetchAscRegionIdAscTypeIdAsc(Instant.now());
		} else if (queriesPerFetch < 310) {
			ret = repo.findTop310ByNextFetchLessThanOrderByNextFetchAscRegionIdAscTypeIdAsc(Instant.now());
		} else {
			ret = repo.findTop1000ByNextFetchLessThanOrderByNextFetchAscRegionIdAscTypeIdAsc(Instant.now());
		}
		if (ret.size() > queriesPerFetch) {
			ret=ret.subList(0, queriesPerFetch);
		}
		return ret;
	}

	public long countRemainingRequests() {
		return repo.countByNextFetchLessThan(Instant.now());
	}

}
