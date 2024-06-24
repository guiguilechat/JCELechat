package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {

	@Modifying
	@Query("delete from EsiNpcLPRequirement where offer.id in :offerIds")
	void deleteByOfferId(Iterable<Integer> offerIds);

}
