package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;

public interface LinkCorporationOfferRepository extends JpaRepository<LinkCorporationOffer, Long> {

	@Modifying
	@Query("delete from EsiNpcLPCorporationOffer where observed.id in :observedIds")
	void deleteByObservedId(Iterable<Integer> observedIds);

	List<LinkCorporationOffer> findAllByCorporationIdAndOfferId(int corporationId, int offerId);

	List<LinkCorporationOffer> findAllByOfferTypeOrderByCorporationNameAscOfferIdAsc(Type type);

	List<LinkCorporationOffer> findAllByObservedIdAndOfferLpCostLessThanEqualOrderByOfferIdAsc(
	    int observedId, int nbLp);

	@Query("""
select
	off.observed,
	count(*)
from
	EsiNpcLPCorporationOffer off
where
	off.offer.lpCost>0
group by
	off.observed
""")

	List<Object[]> listCorporationsWithLPOffers();
}
