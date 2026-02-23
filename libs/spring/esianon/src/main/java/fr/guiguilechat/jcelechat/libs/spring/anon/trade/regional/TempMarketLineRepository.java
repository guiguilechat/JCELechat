package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TempMarketLineRepository extends JpaRepository<TempMarketLine, Long> {

	/**
	 * delete all records from the table
	 */
	@Modifying
	@Query("""
delete
from
	#{#entityName} e
""")
	void clear();

}
