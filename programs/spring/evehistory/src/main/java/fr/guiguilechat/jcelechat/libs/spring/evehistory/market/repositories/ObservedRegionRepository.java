package fr.guiguilechat.jcelechat.libs.spring.evehistory.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.ObservedRegion;

public interface ObservedRegionRepository extends JpaRepository<ObservedRegion, Integer> {

	boolean existsByRegionId(int region_id);

	ObservedRegion findByRegionId(int region_id);

	@Query("select regionId from ObservedRegion where active order by regionId")
	List<Integer> listObservedRegionIds();

	/**
	 * @return the couple observedregion, marketfetchresult for each region to
	 *           observe
	 */
	@Query("select reg, lfs from ObservedRegion reg left join reg.lastFetchSuccess lfs order by lfs.linesFetched desc")
	List<Object[]> listRequests();
}
