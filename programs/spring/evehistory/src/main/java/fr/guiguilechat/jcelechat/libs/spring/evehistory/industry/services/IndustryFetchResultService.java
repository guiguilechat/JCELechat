package fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustryFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustryFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.repositories.IndustryFetchResultRepository;

@Service
public class IndustryFetchResultService {

	@Autowired
	private IndustryFetchResultRepository repo;

	public IndustryFetchResult save(IndustryFetchResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.save(entity);
	}

	/** get the etag from the last fetch successfully saved, or null */
	public String getLastEtag() {
		IndustryFetchResult last = repo.findFirstByStatusOrderByCreatedDateDesc(STATUS.FETCHED);
		return last == null ? null : last.getEtag();
	}

}
