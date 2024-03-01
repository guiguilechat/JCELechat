package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.CorporationOfferRepository;

@Service
public class CorporationOfferService {

	@Autowired
	private CorporationOfferRepository repo;

	public CorporationOffer save(CorporationOffer entity) {
		return repo.save(entity);
	}

	public List<CorporationOffer> saveAll(Iterable<CorporationOffer> entities) {
		return repo.saveAll(entities);
	}

	public void clearFor(LPStoreCorporation lsc) {
		repo.deleteByCorporation(lsc);
	}

	public List<CorporationOffer> forCorporationOffer(int corporationId, int offerId) {
		return repo.findAllByCorporationCorporationIdAndOfferId(corporationId, offerId);
	}

	public List<CorporationOffer> byCorporationId(int corporationId) {
		return repo.findAllByCorporationCorporationId(corporationId);
	}

	public List<CorporationOffer> byCorporationIdRequiringLp(int corporationId) {
		return repo.findAllByCorporationCorporationIdAndLpCostGreaterThan(corporationId, 0);
	}

}
