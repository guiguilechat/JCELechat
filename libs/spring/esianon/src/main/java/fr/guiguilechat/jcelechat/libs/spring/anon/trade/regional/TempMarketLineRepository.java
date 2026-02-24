package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TempMarketLineRepository extends JpaRepository<TempMarketLine, Long> {

	/**
	 * delete all records from the table
	 */
	@Modifying
	@Query(value = """
truncate table "esi_trade_market_line_temp"
""", nativeQuery = true)
	void truncate();

	@Query("""
from
	#{#entityName} ins
	join EsiTradeMarketLine exi on ins.id=exi.id
select
	ins.id orderId,
	ins.regionId insertRegionId,
	exi.regionId existRegionId
""")
	List<Map<String, Object>> debugMergeConflicts();

}
