package fr.guiguilechat.jcelechat.libs.spring.update.entities.remote;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.DeducedEntityRepository;

@NoRepositoryBean
public interface RemoteEntityRepository<Entity extends RemoteEntity<Id, ?>, Id extends Number>
    extends DeducedEntityRepository<Entity, Id> {

	List<Entity> findByFetchActiveTrueAndExpiresBeforeOrderByFetchPriorityDescExpiresAsc(Instant now, Limit limit);

	@Modifying
	@Query("""
update #{#entityName}
set
	fetchPriority=:priority
where
	id in :ids
	and fetchActive
	and fetchPriority < :priority
	and expires <= :expiredBefore
""")
	void updateActivePriority(int priority, Iterable<Id> ids, Instant expiredBefore);

	long countByFetchActiveTrueAndExpiresBefore(Instant now);

}
