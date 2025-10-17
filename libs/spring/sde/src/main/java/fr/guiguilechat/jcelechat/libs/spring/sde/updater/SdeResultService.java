package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.time.Instant;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeResult.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SdeResultService {

	final private SdeResultRepository repo;

	public SdeResult findLastSuccess() {
		return repo.findTop1ByStatusOrderByStartedDateDesc(Status.SUCCESS);
	}

	public SdeResult save(SdeResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.saveAndFlush(entity);
	}

}
