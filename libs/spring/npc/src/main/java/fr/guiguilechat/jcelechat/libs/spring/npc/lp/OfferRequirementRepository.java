package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRequirementRepository extends JpaRepository<OfferRequirement, Long> {

	public List<OfferRequirement> findAllByOfferCorporation(LPStoreCorporation corporation);

}
