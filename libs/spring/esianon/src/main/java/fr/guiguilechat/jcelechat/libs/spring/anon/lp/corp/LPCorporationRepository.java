package fr.guiguilechat.jcelechat.libs.spring.anon.lp.corp;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityRepository;

public interface LPCorporationRepository extends RemoteNumberEntityRepository<LPCorporation, Integer> {

	List<LPCorporation> findByNbOffersGreaterThanOrderByCorporationNameAsc(int minLP);

	LPCorporation findTop1ByCorporationNameLessThanOrderByCorporationNameDesc(String name);

	LPCorporation findTop1ByCorporationNameGreaterThanOrderByCorporationNameAsc(String name);

}
