package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface ObservedCorporationRepository extends IRemoteEntityRepository<ObservedCorporation, Integer> {

	List<ObservedCorporation> findByNbOffersGreaterThanOrderByCorporationNameAsc(int minLP);

	ObservedCorporation findTop1ByCorporationNameLessThanOrderByCorporationNameDesc(String name);

	ObservedCorporation findTop1ByCorporationNameGreaterThanOrderByCorporationNameAsc(String name);

}
