package fr.guiguilechat.jcelechat.libs.spring.npc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;

public interface OfferRequirementRepository extends JpaRepository<OfferRequirement, Long> {

	public List<OfferRequirement> findAllByOfferCorporation(LPStoreCorporation corporation);

}
