package fr.guiguilechat.jcelechat.libs.spring.update.resolve.id;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.LocalEntityRepository;

public interface IdResolutionRepository extends LocalEntityRepository<IdResolution, Integer> {

	int countByNextFetchBefore(Instant max);

	List<IdResolution> findByNextFetchBeforeOrderByFetchPriorityDescNextFetchAsc(Instant now, Limit limit);

}
