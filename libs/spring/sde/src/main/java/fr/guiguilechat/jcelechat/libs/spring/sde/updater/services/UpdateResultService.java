package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.repositories.UpdateResultRepository;


@Service
public class UpdateResultService {

	@Autowired
	private UpdateResultRepository repo;

	public boolean alreadyInserted() {
		return repo.existsByStatus(STATUS.SUCCESS);
	}

	public UpdateResult save(UpdateResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.save(entity);
	}

}