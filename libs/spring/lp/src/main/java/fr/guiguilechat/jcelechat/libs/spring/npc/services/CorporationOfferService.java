package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.CorporationOfferRepository;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CorporationOfferService {

	final private CorporationOfferRepository repo;

	public CorporationOffer save(CorporationOffer entity) {
		return repo.save(entity);
	}

	public List<CorporationOffer> saveAll(Iterable<CorporationOffer> entities) {
		return repo.saveAll(entities);
	}

	public void clearFor(LPStoreCorporation lsc) {
		repo.deleteAllInBatch(repo.findAllByCorporation(lsc));
	}

	public List<CorporationOffer> forCorporationOffer(int corporationId, int offerId) {
		return repo.findAllByCorporationCorporationIdAndOfferId(corporationId, offerId);
	}

	public List<CorporationOffer> byCorporationId(int corporationId) {
		return repo.findAllByCorporationCorporationId(corporationId);
	}

	public List<CorporationOffer> byCorporationIdRequiringLp(int corporationId, int maxLP) {
		return repo.findAllByCorporationCorporationIdAndLpCostGreaterThanAndLpCostLessThan(corporationId, 0, maxLP + 1);
	}

	public List<CorporationOffer> producing(Type type) {
		return repo.findAllByType(type);
	}

}
