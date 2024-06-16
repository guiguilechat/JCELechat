package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.time.Instant;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.UpdateResult.STATUS;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateResultService {

	final private UpdateResultRepository repo;

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
