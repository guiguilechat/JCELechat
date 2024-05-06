package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.OfferRequirementRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferRequirementService {

	final private OfferRequirementRepository repo;

	public OfferRequirement save(OfferRequirement entity) {
		return repo.save(entity);
	}

	public void clearForCorporation(LPStoreCorporation lsc) {
		repo.deleteAllInBatch(repo.findAllByOfferCorporation(lsc));
	}

}
