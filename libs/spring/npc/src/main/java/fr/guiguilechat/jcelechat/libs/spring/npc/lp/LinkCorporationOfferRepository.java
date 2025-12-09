package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;

public interface LinkCorporationOfferRepository extends JpaRepository<LinkCorporationOffer, Long> {

	@Modifying
	@Query("delete from #{#entityName} where lpCorp.id in :observedIds")
	void deleteByCorporationId(Iterable<Integer> observedIds);

	List<LinkCorporationOffer> findAllByLpCorpIdAndOfferId(int corporationId, int offerId);

	List<LinkCorporationOffer> findAllByOfferTypeOrderByLpCorpCorporationNameAscOfferIdAsc(Type type);

	List<LinkCorporationOffer> findAllByLpCorpIdAndOfferLpCostGreaterThanAndOfferLpCostLessThanEqualOrderByOfferIdAsc(
	    int corporationId, int minLpExcluded, int maxLpIncluded);

	@Query("""
select
	off.lpCorp,
	count(*)
from
	#{#entityName} off
where
	off.offer.lpCost>0
group by
	off.lpCorp
""")

	List<Object[]> listCorporationsWithLPOffers();

	@EntityGraph(attributePaths = { "lpCorp" })
	@Query("""
select
	lpl
from
	#{#entityName} lpl
	left join SdeIndustryBlueprintProduct prd on prd.activity.typeId=lpl.offer.type.id
where
	lpl.offer.lpCost>0
	and (lpl.offer.type.id=:productId
		or prd.typeId=:productId)
""")
	List<LinkCorporationOffer> listProducingWithLP(int productId);

	@Query("""
select
	lpl
from
	#{#entityName} lpl
where
	lpl.offer.lpCost>0
	and lpl.lpCorp.id in :corpIds
""")
	List<LinkCorporationOffer> listCorpOffersWithLP(Iterable<Integer> corpIds);
}
