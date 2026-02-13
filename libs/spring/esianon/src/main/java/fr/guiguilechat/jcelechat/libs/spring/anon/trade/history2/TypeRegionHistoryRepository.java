package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionHistory.TypeRegionKey;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.PulseUpdatedRepository;

public interface TypeRegionHistoryRepository extends PulseUpdatedRepository<TypeRegionHistory, TypeRegionKey> {

	@Modifying
	@Query("""
update #{#entityName} e
set
	e.updatePriority=:priority
where
	(e.region.id, e.type.id) in :ids
	and e.updateActive
	and e.updatePriority < :priority
""")
	void setUpdatePriority(int priority, Iterable<TypeRegionKey> ids);
}
