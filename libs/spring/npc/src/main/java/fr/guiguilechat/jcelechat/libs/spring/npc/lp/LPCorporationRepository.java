package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface LPCorporationRepository extends IRemoteEntityRepository<LPCorporation, Integer> {

	List<LPCorporation> findByNbOffersGreaterThanOrderByCorporationNameAsc(int minLP);

	LPCorporation findTop1ByCorporationNameLessThanOrderByCorporationNameDesc(String name);

	LPCorporation findTop1ByCorporationNameGreaterThanOrderByCorporationNameAsc(String name);

}
