package fr.guiguilechat.jcelechat.libs.spring.npc.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;

public interface LPStoreCorporationRepository extends JpaRepository<LPStoreCorporation, Integer>{

	public Optional<LPStoreCorporation> findByCorporationIdAndDisabled(int corporationId, boolean disabled);

	public List<LPStoreCorporation> findByCorporationIdNotInAndDisabled(Iterable<Integer> corporationIds,
			boolean disabled);

	public List<LPStoreCorporation> findByCorporationIdInAndDisabled(Iterable<Integer> corporationIds, boolean disabled);

	public List<LPStoreCorporation> findAllByDisabled(boolean disabled);

	public List<LPStoreCorporation> findAllByDisabledAndNextFetchLessThan(boolean disabled, Instant nextFetch);

}
