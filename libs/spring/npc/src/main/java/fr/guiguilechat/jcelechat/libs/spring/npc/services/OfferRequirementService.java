package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.OfferRequirementRepository;

@Service
public class OfferRequirementService {

	@Autowired
	private OfferRequirementRepository repo;

	public OfferRequirement save(OfferRequirement entity) {
		return repo.save(entity);
	}

}
