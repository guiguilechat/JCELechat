package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;

public interface CorporationOfferRepository extends JpaRepository<CorporationOffer, Long> {

	public void deleteByCorporation(LPStoreCorporation lsc);

	public List<CorporationOffer> findAllByCorporation(LPStoreCorporation lsc);

	public List<CorporationOffer> findAllByCorporationIdAndOfferId(int corporationId, int offerId);

	public List<CorporationOffer> findAllByCorporationId(int corporationId);

	public List<CorporationOffer> findAllByCorporationIdAndLpCostGreaterThanAndLpCostLessThan(
			int corporationId, int minLPCost, int maxLPCostExc);

	public List<CorporationOffer> findAllByType(Type type);

}
