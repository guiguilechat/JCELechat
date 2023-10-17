package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchResult;

public interface MarketFetchResultRepository extends JpaRepository<MarketFetchResult, Long> {

	@Query("select mfr from MarketFetchResult mfr where mfr.id = (select max(id) from MarketFetchResult mfr2 group by mfr2.regionId) order by mfr.id")
	List<MarketFetchResult> findLastResults();

	boolean existsByRegionId(int region_id);

}
