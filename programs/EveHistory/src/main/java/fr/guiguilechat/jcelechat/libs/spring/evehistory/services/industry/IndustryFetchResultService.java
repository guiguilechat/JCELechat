package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.industry;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.industry.IndustryFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.industry.IndustryFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.industry.IndustryFetchResultRepository;

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
