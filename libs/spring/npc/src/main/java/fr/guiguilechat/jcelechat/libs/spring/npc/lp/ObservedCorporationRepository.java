package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface ObservedCorporationRepository extends IRemoteResourceRepository<ObservedCorporation, Integer> {

	List<ObservedCorporation> findByNbOffersGreaterThanOrderByCorporationNameAsc(int minLP);

	ObservedCorporation findTop1ByCorporationNameLessThanOrderByCorporationNameDesc(String name);

	ObservedCorporation findTop1ByCorporationNameGreaterThanOrderByCorporationNameAsc(String name);

}
