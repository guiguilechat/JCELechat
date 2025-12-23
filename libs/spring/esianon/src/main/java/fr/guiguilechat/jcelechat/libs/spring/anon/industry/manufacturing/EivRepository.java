package fr.guiguilechat.jcelechat.libs.spring.anon.industry.manufacturing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices.Price;

public interface EivRepository extends JpaRepository<Price, Integer> {

	/**
	 * @return list of (typeid::int, eiv::long) for the known blueprints
	 */
	@Query("""
select
	mat.activity.typeId,
	floor(sum(pr.adjustedPrice*mat.quantity))
from
	SdeIndustryBlueprintMaterial mat
	join EsiTradePrice pr on mat.typeId=pr.id
where
	mat.activity.activityType=:actType
group by mat.activity.typeId
""")
	List<Object[]> fetchEivs(ActivityType actType);

}
