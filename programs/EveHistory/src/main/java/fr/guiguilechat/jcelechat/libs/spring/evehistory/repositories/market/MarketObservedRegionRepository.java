package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketObservedRegion;

public interface MarketObservedRegionRepository extends JpaRepository<MarketObservedRegion, Long> {

	boolean existsByRegionId(int region_id);

	@Query("select mor.regionId from MarketObservedRegion mor order by mor.regionId")
	List<Integer> listRegionIds();
}
