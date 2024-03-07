package fr.guiguilechat.jcelechat.libs.spring.npc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;

public interface CorporationOfferRepository extends JpaRepository<CorporationOffer, Long> {

	public void deleteByCorporation(LPStoreCorporation lsc);

	public List<CorporationOffer> findAllByCorporation(LPStoreCorporation lsc);

	public List<CorporationOffer> findAllByCorporationCorporationIdAndOfferId(int corporationId, int offerId);

	public List<CorporationOffer> findAllByCorporationCorporationId(int corporationId);

	public List<CorporationOffer> findAllByCorporationCorporationIdAndLpCostGreaterThan(int corporationId, int minLPCost);

	public List<CorporationOffer> findAllByType(Type type);

}
