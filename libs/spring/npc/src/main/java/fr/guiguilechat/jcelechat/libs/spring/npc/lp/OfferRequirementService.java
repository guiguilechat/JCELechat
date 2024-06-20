package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import org.springframework.stereotype.Service;

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
