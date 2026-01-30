package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.TypeRegionHistory.TypeRegionKey;

public interface TypeRegionHistoryRepository extends JpaRepository<TypeRegionHistory, TypeRegionKey> {

	List<TypeRegionHistory> findByFetchActiveTrueAndFetchExpiresBeforeOrderByFetchPriorityDescFetchExpiresAsc(
			Instant now,
			Limit limit);

	@Modifying
	@Query("""
update #{#entityName}
set
	fetchPriority=:priority
where
	(region.id, type.id) in :ids
	and fetchActive
	and fetchPriority < :priority
""")
	void updateActivePriority(int priority, Iterable<TypeRegionKey> ids);

	long countByFetchActiveTrueAndFetchExpiresBefore(Instant now);

}
