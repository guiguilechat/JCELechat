package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EveRefDayHistoryRepository extends JpaRepository<EveRefDayHistory, Integer> {

	@Query("""
from
	#{#entityName} dh
select
	max(id)
""")
	CompletableFuture<Integer> maxId(String extsource);

}
