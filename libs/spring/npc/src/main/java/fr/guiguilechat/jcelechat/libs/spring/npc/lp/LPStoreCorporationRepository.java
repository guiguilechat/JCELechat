package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LPStoreCorporationRepository extends JpaRepository<LPStoreCorporation, Integer>{

	public Optional<LPStoreCorporation> findByIdAndDisabled(int corporationId, boolean disabled);

	public List<LPStoreCorporation> findByIdNotInAndDisabled(Iterable<Integer> corporationIds,
			boolean disabled);

	public List<LPStoreCorporation> findByIdInAndDisabled(Iterable<Integer> corporationIds, boolean disabled);

	public List<LPStoreCorporation> findAllByDisabled(boolean disabled);

	@Query("""
select
	corp,
	count(*)
from
	EsiLPCorporation corp
	join EsiLPOffer offer on offer.corporation=corp
where
	not corp.disabled
group by corp
""")
	public List<Object[]> countOffersForActive();

	public List<LPStoreCorporation> findAllByDisabledFalseAndNextFetchLessThan(Instant nextFetch);

	public LPStoreCorporation findTop1ByNameLessThanOrderByNameDesc(String name);

	public LPStoreCorporation findTop1ByNameGreaterThanOrderByNameAsc(String name);

}
