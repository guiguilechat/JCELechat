package fr.guiguilechat.jcelechat.libs.spring.npc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;

public interface CorporationOfferRepository extends JpaRepository<CorporationOffer, Long> {

	void deleteByCorporation(LPStoreCorporation lsc);

}
