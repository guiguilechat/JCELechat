package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.repositories.UpdateResultRepository;
import jakarta.transaction.Transactional;


@Service
public class UpdateResultService {

	@Autowired
	private UpdateResultRepository repo;

	/**
	 * consider no previous fetch was performed
	 */
	@Transactional
	public void requireFetch() {
		repo.changeStatusFromTo(STATUS.SUCCESS, STATUS.SUCCESS_NEED_REFETCH);
	}

	public UpdateResult lastSuccess() {
		return repo.findTop1ByStatusOrderByStartedDateDesc(STATUS.SUCCESS);
	}

	public UpdateResult save(UpdateResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.save(entity);
	}

}
