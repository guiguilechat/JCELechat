package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PulseUpdatedRepository<Entity extends PulseUpdated<Id>, Id> extends JpaRepository<Entity, Id> {

	List<Entity> findByUpdateActiveTrueAndUpdateNextBeforeOrderByUpdatePriorityDescUpdateNextAsc(
			Instant now,
			Limit limit);

	long countByUpdateActiveTrueAndUpdateNextBefore(Instant now);

}
